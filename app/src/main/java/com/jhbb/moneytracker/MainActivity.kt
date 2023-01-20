package com.jhbb.moneytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jhbb.designsystem.ui.theme.MoneyTrackerTheme
import com.jhbb.onboarding_presentation.navigation.OnboardingRoute
import com.jhbb.onboarding_presentation.navigation.onboardingGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoneyTrackerTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = OnboardingRoute.USERNAME
                ) {
                    onboardingGraph(navController)
                }
            }
        }
    }
}