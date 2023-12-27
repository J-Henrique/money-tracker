package com.jhbb.tracker_presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jhbb.tracker_presentation.ui.home.HomeScreen
import com.jhbb.tracker_presentation.ui.home.HomeScreenActions
import com.jhbb.tracker_presentation.ui.home.HomeScreenViewModel
import com.jhbb.tracker_presentation.ui.register.RegisterScreen
import com.jhbb.tracker_presentation.ui.register.RegisterScreenActions
import com.jhbb.tracker_presentation.ui.register.RegisterScreenViewModel

fun NavGraphBuilder.trackerGraph(
    navController: NavController
) {
    composable(route = TrackerDestinations.HOME_TRACKER_ROUTE) {
        val viewModel = hiltViewModel<HomeScreenViewModel>()
        val actions = HomeScreenActions(
            onRegister = { navController.navigate(TrackerDestinations.REGISTER_ROUTE) }
        )
        HomeScreen(
            state = viewModel.state,
            actions = actions
        )
    }
    composable(route = TrackerDestinations.REGISTER_ROUTE) {
        val viewModel = hiltViewModel<RegisterScreenViewModel>()
        val actions = RegisterScreenActions(
            onComplete = {
                navController.popBackStack()
            },
            uiEvent = viewModel.uiEvent,
            onEvent = viewModel::onEvent
        )
        RegisterScreen(
            state = viewModel.state,
            actions = actions
        )
    }
}