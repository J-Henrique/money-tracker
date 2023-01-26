package com.jhbb.onboarding_presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jhbb.onboarding_presentation.ui.splash.SplashScreen
import com.jhbb.onboarding_presentation.ui.username_field.UserNameFieldActions
import com.jhbb.onboarding_presentation.ui.username_field.UserNameFieldScreen
import com.jhbb.onboarding_presentation.ui.username_field.UserNameFieldViewModel
import com.jhbb.onboarding_presentation.ui.username_message.UserNameMessageScreen

fun NavGraphBuilder.onboardingGraph(navController: NavController) {
    composable(OnboardingDestinations.SPLASH_ROUTE) {
        SplashScreen {
            navController.navigate(OnboardingDestinations.USERNAME_MESSAGE_ROUTE) {
                popUpTo(OnboardingDestinations.SPLASH_ROUTE) { inclusive = true }
            }
        }
    }
    composable(OnboardingDestinations.USERNAME_MESSAGE_ROUTE) {
        UserNameMessageScreen { navController.navigate(OnboardingDestinations.USERNAME_FIELD_ROUTE) }
    }
    composable(OnboardingDestinations.USERNAME_FIELD_ROUTE) {
        val viewModel = hiltViewModel<UserNameFieldViewModel>()
        val actions = UserNameFieldActions(
            onNext = { navController.navigate(OnboardingDestinations.CATEGORIES_ROUTE) },
            uiEvent = viewModel.uiEvent,
            onEvent = viewModel::onEvent
        )
        UserNameFieldScreen(
            username = viewModel.username,
            actions = actions
        )
    }
    composable(OnboardingDestinations.CATEGORIES_ROUTE) {

    }
}