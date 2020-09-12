package com.beok.common.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.beok.common.util.Prefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class CommonModule {

    @Provides
    @Singleton
    fun providesSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun providesPrefs(sharedPreferences: SharedPreferences): Prefs = Prefs(sharedPreferences)
}
