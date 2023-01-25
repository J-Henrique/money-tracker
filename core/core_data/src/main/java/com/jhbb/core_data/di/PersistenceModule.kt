package com.jhbb.core_data.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.jhbb.core_data.preferences.PreferencesImpl
import com.jhbb.core_domain.preferences.Preferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [PreferenceModule::class])
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    private const val SHARED_PREFERENCES_FILE_NAME = "com_jhbb_moneytracker_preferences"

    @Provides
    @Singleton
    fun provideSharedPreference(app: Application): SharedPreferences {
        return app.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PreferenceModule {
    @Binds
    @Singleton
    abstract fun bindPreferences(pref: PreferencesImpl): Preferences
}