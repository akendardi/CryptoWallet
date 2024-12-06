package com.akendardi.cryptowallet.di

import com.akendardi.cryptowallet.presentation.MainActivity
import dagger.Component

@AppScope
@Component(
    modules = [
        DataModule::class,
        RepositoryModule::class,
        FirebaseModule::class
    ]
)
interface AppComponent {
    fun inject(activity: MainActivity)
}