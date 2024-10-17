package com.akendardi.cryptowallet.data.repositories.user

import android.content.Context
import android.net.Uri
import android.util.Log
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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


    override suspend fun uploadProfileImage(uri: Uri): UserProfileOperationResult {
        if (checkInternetConnectionUseCase()) return UserProfileOperationResult.InternetError
        val currentUser =
            getCurrentUser() ?: return UserProfileOperationResult.AuthError
        val currentUserId = currentUser.uid
        val storageRef = storage.reference.child("images/$currentUserId")
        return try {
            val fileList = Tasks.await(storageRef.listAll()).items
            fileList.forEach { fileRef ->
                fileRef.delete().await()
            }

            val fileName = uri.lastPathSegment ?: "profile_image"
            val userFileRef = storageRef.child(fileName)

            userFileRef.putFile(uri).await()

            loadProfileInfo()
            UserProfileOperationResult.SuccessChangeProfilePhoto
        } catch (_: Exception) {
            UserProfileOperationResult.Error
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

    override suspend fun changeUserName(name: String): UserProfileOperationResult {
        return try {
            val currentUser = getCurrentUser() ?: return UserProfileOperationResult.AuthError
            withContext(Dispatchers.IO) {
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()

                currentUser.updateProfile(profileUpdates).await()
                loadProfileInfo()
                UserProfileOperationResult.SuccessChangeName
            }
        } catch (e: Exception) {
            UserProfileOperationResult.Error
        }
    }

    override suspend fun changeEmail(
        email: String,
        currentPassword: String
    ): UserProfileOperationResult {
        return try {
            withContext(Dispatchers.IO) {
                val user = getCurrentUser()
                val cred = EmailAuthProvider.getCredential(user?.email.toString(), currentPassword)

                user?.reauthenticate(cred)?.await()
                user?.verifyBeforeUpdateEmail(email)?.await()
                Log.d("EMAIL_TEST", "Email updated successfully.")
                Log.d("EMAIL_TEST", getCurrentUser().toString())
                UserProfileOperationResult.SuccessChangeEmail
            }
        } catch (e: Exception) {
            Log.e("EMAIL_TEST", "Error updating email: ${e.message}")
            UserProfileOperationResult.Error
        }
    }

    override suspend fun changePassword(password: String): UserProfileOperationResult {
        return try {
            withContext(Dispatchers.IO) {
                val currentUser = getCurrentUser()
                currentUser?.updatePassword(password)
                loadProfileInfo()
                UserProfileOperationResult.SuccessChangePassword
            }
        } catch (_: Exception) {
            UserProfileOperationResult.Error
        }


    }

    private suspend fun getCurrentUser(): FirebaseUser? {
        try {
            auth.currentUser?.let { user ->
                user.reload().await()  // Дождаться завершения асинхронного вызова reload
                return auth.currentUser ?: throw RuntimeException("No user found after reload")
            }
        } catch (e: Exception) {
            return null
        }
        return auth.currentUser
    }


}
