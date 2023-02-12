package com.jhbb.moneytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jhbb.core_ui.ui.theme.MoneyTrackerTheme
import com.jhbb.onboarding_presentation.navigation.OnboardingDestinations
import com.jhbb.onboarding_presentation.navigation.onboardingGraph
import com.jhbb.tracker_presentation.navigation.TrackerDestinations
import com.jhbb.tracker_presentation.navigation.trackerGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoneyTrackerTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = OnboardingDestinations.SPLASH_ROUTE
                ) {
                    onboardingGraph(navController) {
                        navController.navigate(TrackerDestinations.HOME_TRACKER_ROUTE)
                    }
                    trackerGraph(navController)
                }
            }
        }
    }
}