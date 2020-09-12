package com.beok.domain.di

import com.beok.domain.auth.AuthDataSource
import com.beok.domain.auth.AuthRepository
import com.beok.domain.auth.AuthService
import com.beok.domain.content.ContentDataSource
import com.beok.domain.content.ContentRepository
import com.beok.domain.content.ContentService
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

    @Provides
    @Singleton
    fun providesContentRepository(contentService: ContentService): ContentDataSource =
        ContentRepository(contentService)
}
