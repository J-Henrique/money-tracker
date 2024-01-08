package com.jhbb.tracker_data.di

import com.jhbb.tracker_data.remote.RegisterService
import com.jhbb.tracker_data.repository.RegisterRepositoryImpl
import com.jhbb.tracker_domain.repository.RegisterRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [TrackerRepositoryModule::class])
@InstallIn(SingletonComponent::class)
object TrackerDataModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RegisterService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideRegisterService(
        retrofit: Retrofit
    ): RegisterService {
        return retrofit.create(RegisterService::class.java)
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class TrackerRepositoryModule {
    @Binds
    abstract fun bindRegisterRepository(
        repository: RegisterRepositoryImpl
    ): RegisterRepository
}