package com.jhbb.onboarding_presentation.ui.splash

import kotlinx.coroutines.flow.Flow

data class SplashScreenActions(
    val startOnboarding: () -> Unit,
    val startHome: () -> Unit,
    val getUserName: () -> Unit,
    val uiEvent: Flow<SplashScreenUiEvent>,
)