package com.jhbb.onboarding_data.repository

import com.jhbb.core_domain.model.Category
import com.jhbb.core_domain.preferences.Preferences
import com.jhbb.core_domain.repository.CategoryRepository
import com.jhbb.onboarding_domain.repository.OnboardingUserInfoRepository
import javax.inject.Inject

class OnboardingUserInfoRepositoryImpl @Inject constructor(
    private val preferences: Preferences,
    private val categoryRepository: CategoryRepository
): OnboardingUserInfoRepository {
    override fun saveUserName(name: String) {
        preferences.putUserName(name)
    }
    override suspend fun getCategories(): List<Category> {
        return categoryRepository.getCategories()
    }

    override suspend fun updateCategories(categories: List<Category>): Result<Unit> {
        return categoryRepository.updateCategories(categories)
    }
}