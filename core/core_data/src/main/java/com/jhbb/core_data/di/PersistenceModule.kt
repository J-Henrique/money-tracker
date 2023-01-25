package com.jhbb.core_data.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.jhbb.core_data.preferences.PreferencesImpl
import com.jhbb.core_domain.preferences.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    private const val SHARED_PREFERENCES_FILE_NAME = "com_jhbb_moneytracker_preferences"

    @Provides
    @Singleton
    fun providesSharedPreference(app: Application): SharedPreferences {
        return app.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providesPreferences(sharedPreferences: SharedPreferences): Preferences {
        return PreferencesImpl(sharedPreferences)
    }
}