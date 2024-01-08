package com.jhbb.core_data.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.jhbb.core_data.database.MoneyTrackerDatabase
import com.jhbb.core_data.database.dao.CategoryDao
import com.jhbb.core_data.database.dao.RegisterDao
import com.jhbb.core_data.preferences.PreferencesImpl
import com.jhbb.core_data.repository.CategoryRepositoryImpl
import com.jhbb.core_domain.preferences.Preferences
import com.jhbb.core_domain.repository.CategoryRepository
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

    @Provides
    @Singleton
    fun provideDatabase(app: Application): MoneyTrackerDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            MoneyTrackerDatabase::class.java,
            "MoneyTracker.db"
        ).createFromAsset("database/money_tracker.db").build()
    }

    @Provides
    fun provideCategoryDao(db: MoneyTrackerDatabase): CategoryDao {
        return db.categoryDao
    }

    @Provides
    fun provideRegisterDao(db: MoneyTrackerDatabase): RegisterDao {
        return db.registerDao
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PreferenceModule {
    @Binds
    @Singleton
    abstract fun bindPreferences(pref: PreferencesImpl): Preferences

    @Binds
    abstract fun bindCategoryRepository(
        repository: CategoryRepositoryImpl
    ): CategoryRepository
}