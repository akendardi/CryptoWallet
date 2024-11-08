package com.akendardi.cryptowallet.data.repositories.user

import android.content.Context
import android.net.Uri
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.domain.entity.UserInfo
import com.akendardi.cryptowallet.domain.repository.UserInfoRepository
import com.akendardi.cryptowallet.domain.states.user_profile.UserProfileOperationResult
import com.akendardi.cryptowallet.domain.usecase.auth.CheckInternetConnectionUseCase
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
    override val requestAnswer: SharedFlow<UserProfileOperationResult> =
        _requestAnswer.asSharedFlow()


    private suspend fun startLoading() {
        _requestAnswer.emit(UserProfileOperationResult.Loading)
    }

    private suspend fun isInternetAvailable(): Boolean {
        return if (!checkInternetConnectionUseCase()) {
            _requestAnswer.emit(UserProfileOperationResult.InternetError)
            false
        } else {
            true
        }
    }

    private suspend fun getValidatedCurrentUser(): FirebaseUser? {
        val currentUser = getCurrentUser()
        if (currentUser == null) {
            _requestAnswer.emit(UserProfileOperationResult.AuthError)
        }
        return currentUser
    }


    override suspend fun uploadProfileImage(uri: Uri) {
        if (!isInternetAvailable()) return
        val currentUser = getValidatedCurrentUser() ?: return

        startLoading()

        val currentUserId = currentUser.uid
        val storageRef = storage.reference.child("images/$currentUserId")
        try {
            withContext(Dispatchers.IO) {
                val fileList = Tasks.await(storageRef.listAll()).items
                fileList.forEach { fileRef ->
                    fileRef.delete().await()
                }

                val fileName = uri.lastPathSegment ?: "profile_image"
                val userFileRef = storageRef.child(fileName)

                userFileRef.putFile(uri).await()

                loadProfileInfo()
                _requestAnswer.emit(UserProfileOperationResult.SuccessChangeProfilePhoto)
            }

        } catch (_: Exception) {
            _requestAnswer.emit(UserProfileOperationResult.Error)
        }
    }


    override suspend fun loadProfileInfo() {

        if (!isInternetAvailable()) return
        val currentUser = getValidatedCurrentUser() ?: return

        val name = currentUser.displayName ?: ""
        val profilePhotoUri = getProfilePhotoUri(currentUser)
        val email = currentUser.email ?: ""
        val isVerification = currentUser.isEmailVerified

        val userInfo = UserInfo(
            userName = name,
            profileUri = profilePhotoUri,
            email = email,
            isVerificationAccount = isVerification
        )

        _userInfoFlow.emit(userInfo)
    }

    private suspend fun getProfilePhotoUri(
        currentUser: FirebaseUser
    ): Uri {
        return withContext(Dispatchers.IO) {
            val storageRef = storage.reference.child("images/${currentUser.uid}")
            val fileList = Tasks.await(storageRef.listAll()).items

            return@withContext if (fileList.isNotEmpty()) {
                Tasks.await(fileList.first().downloadUrl)
            } else {
                Uri.parse("android.resource://${context.packageName}/${R.drawable.default_profile_image}")
            }
        }

    }

    override suspend fun changeUserName(name: String) {
        try {
            if (!isInternetAvailable()) return
            val currentUser = getValidatedCurrentUser() ?: return
            startLoading()
            changeUserNameOnFirebase(
                currentUser = currentUser,
                name = name
            )
            _requestAnswer.emit(UserProfileOperationResult.SuccessChangeName)
        } catch (e: Exception) {
            _requestAnswer.emit(UserProfileOperationResult.Error)
        }
    }

    private suspend fun changeUserNameOnFirebase(
        currentUser: FirebaseUser,
        name: String
    ) {
        withContext(Dispatchers.IO) {
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build()

            currentUser.updateProfile(profileUpdates).await()
            loadProfileInfo()
        }
    }

    override suspend fun changeEmail(
        email: String,
        currentPassword: String
    ) {
        try {
            if (!isInternetAvailable()) return
            val currentUser = getValidatedCurrentUser() ?: return
            startLoading()
            changeEmailOnFirebase(
                currentUser = currentUser,
                email = email,
                currentPassword = currentPassword
            )
            _requestAnswer.emit(UserProfileOperationResult.SuccessChangeEmail)
        } catch (e: Exception) {
            _requestAnswer.emit(UserProfileOperationResult.Error)
        }
    }

    private suspend fun changeEmailOnFirebase(
        currentUser: FirebaseUser,
        email: String,
        currentPassword: String
    ) {
        withContext(Dispatchers.IO) {
            val cred =
                EmailAuthProvider.getCredential(currentUser.email.toString(), currentPassword)

            currentUser.reauthenticate(cred).await()
            currentUser.verifyBeforeUpdateEmail(email).await()

        }
    }

    override suspend fun changePassword(
        currentPassword: String,
        newPassword: String
    ) {
        try {
            if (!isInternetAvailable()) return
            val currentUser = getValidatedCurrentUser() ?: return
            startLoading()
            val email = currentUser.email ?: return
            changePasswordOnFirebase(
                currentUser = currentUser,
                email = email,
                currentPassword = currentPassword,
                newPassword = newPassword
            )
        } catch (e: Exception) {
            _requestAnswer.emit(UserProfileOperationResult.Error)
        }
    }

    private suspend fun changePasswordOnFirebase(
        currentUser: FirebaseUser,
        email: String,
        currentPassword: String,
        newPassword: String
    ) {
        withContext(Dispatchers.IO) {

            val credential = EmailAuthProvider.getCredential(email, currentPassword)
            currentUser.reauthenticate(credential).await()

            currentUser.updatePassword(newPassword).await()

            _requestAnswer.emit(UserProfileOperationResult.SuccessChangePassword)

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

    override suspend fun verificateEmail() {
        if (!isInternetAvailable()) return

        val currentUser = getValidatedCurrentUser() ?: return
        startLoading()

        try {
            currentUser.sendEmailVerification().await()
            _requestAnswer.emit(UserProfileOperationResult.SuccessVerificationEmail)
        } catch (e: Exception) {
            _requestAnswer.emit(UserProfileOperationResult.Error)
        }
    }

}