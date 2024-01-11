package com.jhbb.onboarding_presentation.ui.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jhbb.core_ui.R
import com.jhbb.core_ui.ui.theme.MoneyTrackerTheme
import com.jhbb.core_ui.utils.MultiThemePreview
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
internal fun SplashScreen(
    actions: SplashScreenActions
) {
    val animationDuration = 1500
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(animationDuration),
        finishedListener = { actions.getUserName() },
        label = ""
    )
    LaunchedEffect(true) {
        startAnimation = true
        actions.uiEvent.collect { event ->
            when (event) {
                SplashScreenUiEvent.NavigateToOnboarding -> actions.startOnboarding()
                SplashScreenUiEvent.NavigateToHome -> actions.startHome()
            }
        }
    }
    Surface {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_money_tracker),
                contentDescription = null,
                modifier = Modifier
                    .size(130.dp)
                    .alpha(alphaAnimation.value),
            )
        }
    }
}

@MultiThemePreview
@Composable
fun PreviewSplashScreen() {
    MoneyTrackerTheme {
        SplashScreen(SplashScreenActions({}, {}, {}, MutableSharedFlow()))
    }
}