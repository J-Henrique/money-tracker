package com.jhbb.onboarding_data.di

import com.jhbb.onboarding_data.repository.OnboardingUserInfoRepositoryImpl
import com.jhbb.onboarding_domain.repository.OnboardingUserInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class OnboardingDataModule {

    @Binds
    @ViewModelScoped
    abstract fun bindOnboardingUserInfoRepository(
        repository: OnboardingUserInfoRepositoryImpl
    ): OnboardingUserInfoRepository
}