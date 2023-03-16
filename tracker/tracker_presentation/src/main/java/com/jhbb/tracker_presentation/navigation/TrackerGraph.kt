package com.jhbb.tracker_presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jhbb.tracker_presentation.ui.home.HomeScreen
import com.jhbb.tracker_presentation.ui.home.HomeScreenViewModel

fun NavGraphBuilder.trackerGraph(navController: NavController) {
    composable(route = TrackerDestinations.HOME_TRACKER_ROUTE) {
        val viewModel = hiltViewModel<HomeScreenViewModel>()
        HomeScreen(
            state = viewModel.state
        )
    }
}