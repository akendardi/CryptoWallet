package com.akendardi.cryptowallet.domain.entity.user_info.transactions

sealed class TransactionType {

    data object Buy : TransactionType()

    data object Sell : TransactionType()

}