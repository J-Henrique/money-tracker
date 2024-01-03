package com.jhbb.tracker_domain.di

import com.jhbb.tracker_domain.use_case.insert_register.InsertRegisterUseCase
import com.jhbb.tracker_domain.use_case.insert_register.InsertRegisterUseCaseImpl
import com.jhbb.tracker_domain.use_case.synchronize_register.SynchronizeRegisterUseCase
import com.jhbb.tracker_domain.use_case.synchronize_register.SynchronizeRegisterUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class TrackerDomainModule {

    @Binds
    @ViewModelScoped
    abstract fun bindInsertRegisterUseCase(
        insertRegisterUseCase: InsertRegisterUseCaseImpl
    ): InsertRegisterUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSynchronizeRegisterUseCase(
        synchronizeRegisterUseCase: SynchronizeRegisterUseCaseImpl
    ): SynchronizeRegisterUseCase
}