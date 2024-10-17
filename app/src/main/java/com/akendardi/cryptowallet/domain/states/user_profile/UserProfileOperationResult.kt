package com.akendardi.cryptowallet.domain.states.user_profile

sealed class UserProfileOperationResult {

    data object Initial : UserProfileOperationResult()

    data object Loading : UserProfileOperationResult()

    data object SuccessChangeName : UserProfileOperationResult()

    data object SuccessChangePassword : UserProfileOperationResult()

    data object SuccessChangeEmail : UserProfileOperationResult()

    data object SuccessChangeProfilePhoto: UserProfileOperationResult()

    data object Error : UserProfileOperationResult()

    data object AuthError: UserProfileOperationResult()

    data object InternetError: UserProfileOperationResult()

}