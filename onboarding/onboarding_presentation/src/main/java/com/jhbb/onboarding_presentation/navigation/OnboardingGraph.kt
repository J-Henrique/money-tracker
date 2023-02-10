package com.jhbb.onboarding_presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jhbb.onboarding_presentation.ui.categories.CategoriesScreen
import com.jhbb.onboarding_presentation.ui.categories.CategoriesScreenActions
import com.jhbb.onboarding_presentation.ui.categories.CategoriesViewModel
import com.jhbb.onboarding_presentation.ui.splash.SplashScreen
import com.jhbb.onboarding_presentation.ui.username_field.UserNameFieldScreen
import com.jhbb.onboarding_presentation.ui.username_field.UserNameFieldScreenActions
import com.jhbb.onboarding_presentation.ui.username_field.UserNameFieldViewModel
import com.jhbb.onboarding_presentation.ui.username_message.UserNameMessageScreen

fun NavGraphBuilder.onboardingGraph(navController: NavController, goToTracker: String) {
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
        val actions = UserNameFieldScreenActions(
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
        val viewModel = hiltViewModel<CategoriesViewModel>()
        val actions = CategoriesScreenActions(
            onNext = { navController.navigate(goToTracker) },
            uiEvent = viewModel.uiEvent,
            onEvent = viewModel::onEvent
        )
        CategoriesScreen(
            state = viewModel.state,
            actions = actions,
        )
    }
}