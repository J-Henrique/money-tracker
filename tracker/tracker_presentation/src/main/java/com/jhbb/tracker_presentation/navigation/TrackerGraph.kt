package com.jhbb.tracker_presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jhbb.tracker_presentation.ui.home.HomeScreen

fun NavGraphBuilder.trackerGraph(navController: NavController) {
    composable(route = TrackerDestinations.HOME_ROUTE) {
        HomeScreen()
    }
}