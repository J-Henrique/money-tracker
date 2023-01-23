package com.jhbb.onboarding_domain.di

import com.jhbb.onboarding_domain.use_case.FilterLettersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OnboardingDomainModule {

    @ViewModelScoped
    @Provides
    fun providesFilterLettersUseCase(): FilterLettersUseCase {
        return FilterLettersUseCase()
    }
}