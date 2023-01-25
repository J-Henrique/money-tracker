package com.jhbb.onboarding_data.di

import com.jhbb.core_domain.preferences.Preferences
import com.jhbb.onboarding_data.repository.OnboardingUserInfoRepositoryImpl
import com.jhbb.onboarding_domain.repository.OnboardingUserInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OnboardingDataModule {

    @Provides
    @ViewModelScoped
    fun providesOnboardingUserInfoRepository(preferences: Preferences): OnboardingUserInfoRepository {
        return OnboardingUserInfoRepositoryImpl(preferences)
    }
}