package com.jhbb.onboarding_presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jhbb.onboarding_presentation.ui.username.UserNameScreen

fun NavGraphBuilder.onboardingGraph(navController: NavController) {
    composable(OnboardingRoute.USERNAME) {
        UserNameScreen()
    }
    composable(OnboardingRoute.CATEGORIES) {

    }
}