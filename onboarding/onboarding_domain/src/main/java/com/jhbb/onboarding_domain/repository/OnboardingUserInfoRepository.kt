package com.jhbb.onboarding_domain.repository

interface OnboardingUserInfoRepository {
    fun saveUserName(name: String)
    suspend fun getCategory()
}