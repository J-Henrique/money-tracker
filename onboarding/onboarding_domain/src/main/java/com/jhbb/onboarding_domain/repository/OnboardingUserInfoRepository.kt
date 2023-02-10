package com.jhbb.onboarding_domain.repository

import com.jhbb.core_domain.model.Category

interface OnboardingUserInfoRepository {
    fun saveUserName(name: String)
    suspend fun getCategories(): List<Category>
    suspend fun updateCategories(categories: List<Category>): Result<Unit>
}