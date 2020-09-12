package com.beok.domain.di

import com.beok.domain.auth.AuthDataSource
import com.beok.domain.auth.AuthRepository
import com.beok.domain.auth.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesAuthRepository(authService: AuthService): AuthDataSource =
        AuthRepository(authService)
}
