package com.jhbb.onboarding_presentation.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhbb.onboarding_domain.repository.OnboardingUserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SplashViewModel @Inject constructor(
    private val onboardingUserInfoRepository: OnboardingUserInfoRepository
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<SplashScreenUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun getUserName() {
        viewModelScope.launch {
            val userName = onboardingUserInfoRepository.getUserName()
            _uiEvent.emit(
                if (userName.isNullOrBlank()) {
                    SplashScreenUiEvent.NavigateToOnboarding
                } else {
                    SplashScreenUiEvent.NavigateToHome
                }
            )
        }
    }
}

