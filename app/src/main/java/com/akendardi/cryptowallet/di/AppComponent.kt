package com.akendardi.cryptowallet.di

import com.akendardi.cryptowallet.presentation.MainActivity
import dagger.Component

@Component(modules = [
    DataModule::class
])
interface AppComponent {
    fun inject(activity: MainActivity)
}