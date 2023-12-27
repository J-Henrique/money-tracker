package com.jhbb.tracker_domain.di

import com.jhbb.core_domain.repository.RegisterRepository
import com.jhbb.tracker_domain.use_case.insert_register.InsertRegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @Provides
    @ViewModelScoped
    fun provideInsertRegisterUseCase(
        registerRepository: RegisterRepository
    ): InsertRegisterUseCase {
        return InsertRegisterUseCase(registerRepository)
    }
}