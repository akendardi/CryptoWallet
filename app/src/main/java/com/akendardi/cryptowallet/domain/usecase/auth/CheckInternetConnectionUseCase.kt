package com.akendardi.cryptowallet.domain.usecase.auth

import com.akendardi.cryptowallet.domain.repository.InternetConnectionRepository
import javax.inject.Inject

class CheckInternetConnectionUseCase @Inject constructor(
    private val repository: InternetConnectionRepository
) {
    operator fun invoke(): Boolean = repository.checkInternetConnection()
}