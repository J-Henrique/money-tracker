package com.jhbb.onboarding_data.repository

import com.jhbb.core_domain.preferences.Preferences
import com.jhbb.onboarding_domain.repository.OnboardingUserInfoRepository
import javax.inject.Inject

class OnboardingUserInfoRepositoryImpl @Inject constructor(
    private val preferences: Preferences
): OnboardingUserInfoRepository {

    override fun saveUserName(name: String) {
        preferences.putUserName(name)
    }
}