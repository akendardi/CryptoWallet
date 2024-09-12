package com.akendardi.cryptowallet.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides

@Module
interface FirebaseModule {
    companion object{

        @Provides
        fun provideFirebaseAuth(): FirebaseAuth{
            return FirebaseAuth.getInstance().apply {
                setLanguageCode("ru")
            }
        }
    }
}