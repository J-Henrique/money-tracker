package com.jhbb.onboarding_presentation.ui.splash

sealed interface SplashScreenUiEvent {
    object NavigateToOnboarding: SplashScreenUiEvent
    object NavigateToHome: SplashScreenUiEvent
}