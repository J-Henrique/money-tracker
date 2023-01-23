package com.jhbb.onboarding_presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jhbb.onboarding_presentation.ui.username_field.UserNameFieldScreen
import com.jhbb.onboarding_presentation.ui.username_message.UserNameMessageScreen

fun NavGraphBuilder.onboardingGraph(navController: NavController) {
    composable(OnboardingDestinations.USERNAME_MESSAGE_ROUTE) {
        UserNameMessageScreen { navController.navigate(OnboardingDestinations.USERNAME_FIELD_ROUTE) }
    }
    composable(OnboardingDestinations.USERNAME_FIELD_ROUTE) {
        UserNameFieldScreen(
            onNext = { navController.navigate(OnboardingDestinations.CATEGORIES_ROUTE) }
        )
    }
    composable(OnboardingDestinations.CATEGORIES_ROUTE) {

    }
}