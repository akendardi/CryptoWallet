package com.akendardi.cryptowallet.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FirebaseModule {
    companion object {

        @Provides
        fun provideFirebaseAuth(): FirebaseAuth {
            return FirebaseAuth.getInstance().apply {
                setLanguageCode("ru")
            }
        }

        @Provides
        fun provideFirebaseStorage(): FirebaseStorage {
            return Firebase.storage
        }
    }
}