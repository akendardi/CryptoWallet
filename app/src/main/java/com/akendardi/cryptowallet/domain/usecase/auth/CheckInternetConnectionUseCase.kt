package com.akendardi.cryptowallet.domain.usecase.auth

import com.akendardi.cryptowallet.data.repositories.internet_usecase.CheckInternetConnectionUseCase
import javax.inject.Inject

class CheckInternetConnectionUseCase @Inject constructor(
    private val checkInternetConnectionUseCase: CheckInternetConnectionUseCase
) {
    operator fun invoke(): Boolean = checkInternetConnectionUseCase()
}