package com.akendardi.cryptowallet.data.repositories.user

import android.content.Context
import android.net.Uri
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.data.repositories.internet_usecase.CheckInternetConnectionUseCase
import com.akendardi.cryptowallet.domain.entity.UserInfo
import com.akendardi.cryptowallet.domain.repository.UserInfoRepository
import com.akendardi.cryptowallet.domain.states.user_profile.UserProfileOperationResult
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage,
    private val auth: FirebaseAuth,
    private val checkInternetConnectionUseCase: CheckInternetConnectionUseCase,
    @ApplicationContext private val context: Context
) : UserInfoRepository {


    private val _userInfoFlow = MutableStateFlow(UserInfo())
    override val userInfoFlow: StateFlow<UserInfo> = _userInfoFlow.asStateFlow()

    private val _requestAnswer: MutableSharedFlow<UserProfileOperationResult> =
        MutableSharedFlow(replay = 1)
    override val requestAnswer: SharedFlow<UserProfileOperationResult> = _requestAnswer.asSharedFlow()


    override suspend fun uploadProfileImage(uri: Uri) {
        if (!checkInternetConnectionUseCase()) {
            _requestAnswer.emit(UserProfileOperationResult.InternetError)
            return
        }
        val currentUser =
            getCurrentUser()

        if (currentUser == null) {
            _requestAnswer.emit(UserProfileOperationResult.AuthError)
            return
        }
        _requestAnswer.emit(UserProfileOperationResult.Loading)

        val currentUserId = currentUser.uid
        val storageRef = storage.reference.child("images/$currentUserId")
        try {
            val fileList = Tasks.await(storageRef.listAll()).items
            fileList.forEach { fileRef ->
                fileRef.delete().await()
            }

            val fileName = uri.lastPathSegment ?: "profile_image"
            val userFileRef = storageRef.child(fileName)

            userFileRef.putFile(uri).await()

            loadProfileInfo()
            _requestAnswer.emit(UserProfileOperationResult.SuccessChangeProfilePhoto)
        } catch (_: Exception) {
            _requestAnswer.emit(UserProfileOperationResult.Error)
        }
    }


    override suspend fun loadProfileInfo() {
        val currentUser = getCurrentUser() ?: return

        val name = currentUser.displayName ?: ""
        val profilePhotoUri = getProfilePhotoUri(currentUser)
        val email = currentUser.email ?: ""

        val userInfo = UserInfo(
            userName = name,
            profileUri = profilePhotoUri,
            email = email
        )

        _userInfoFlow.emit(userInfo)
    }

    private fun getProfilePhotoUri(
        currentUser: FirebaseUser
    ): Uri {
        val storageRef = storage.reference.child("images/${currentUser.uid}")
        val fileList = Tasks.await(storageRef.listAll()).items

        return if (fileList.isNotEmpty()) {
            Tasks.await(fileList.first().downloadUrl)
        } else {
            Uri.parse("android.resource://${context.packageName}/${R.drawable.default_profile_image}")
        }
    }

    override suspend fun changeUserName(name: String) {
        try {
            if (!checkInternetConnectionUseCase()) {
                _requestAnswer.emit(UserProfileOperationResult.InternetError)
                return
            }
            val currentUser = getCurrentUser()
            if (currentUser == null) {
                _requestAnswer.emit(UserProfileOperationResult.AuthError)
                return
            }
            _requestAnswer.emit(UserProfileOperationResult.Loading)
            withContext(Dispatchers.IO) {
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()

                currentUser.updateProfile(profileUpdates).await()
                loadProfileInfo()
                _requestAnswer.emit(UserProfileOperationResult.SuccessChangeName)
            }
        } catch (e: Exception) {
            _requestAnswer.emit(UserProfileOperationResult.Error)
        }
    }

    override suspend fun changeEmail(
        email: String,
        currentPassword: String
    ) {
        try {
            withContext(Dispatchers.IO) {
                val user = getCurrentUser()
                val cred = EmailAuthProvider.getCredential(user?.email.toString(), currentPassword)

                user?.reauthenticate(cred)?.await()
                user?.verifyBeforeUpdateEmail(email)?.await()
                _requestAnswer.emit(UserProfileOperationResult.SuccessChangeEmail)

            }
        } catch (e: Exception) {
            _requestAnswer.emit(UserProfileOperationResult.Error)
        }
    }

    override suspend fun changePassword(password: String) {
        try {
            if (!checkInternetConnectionUseCase()) {
                _requestAnswer.emit(UserProfileOperationResult.InternetError)
                return
            }
            val currentUser = getCurrentUser()
            if (currentUser == null) {
                _requestAnswer.emit(UserProfileOperationResult.AuthError)
                return
            }
            withContext(Dispatchers.IO) {
                _requestAnswer.emit(UserProfileOperationResult.Loading)
                currentUser.updatePassword(password)
                loadProfileInfo()
                _requestAnswer.emit(UserProfileOperationResult.SuccessChangePassword)

            }
        } catch (_: Exception) {
            _requestAnswer.emit(UserProfileOperationResult.Error)
        }
    }

    override suspend fun resetRequestAnswer() {
        _requestAnswer.emit(UserProfileOperationResult.Initial)
    }

    private suspend fun getCurrentUser(): FirebaseUser? {
        try {
            auth.currentUser?.let { user ->
                user.reload().await()
                if (auth.currentUser == null)
                    return auth.currentUser
            }
        } catch (e: Exception) {
            return null
        }
        return auth.currentUser
    }


}
