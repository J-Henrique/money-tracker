package com.jhbb.onboarding_presentation.ui.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.jhbb.core_ui.ui.theme.MoneyTrackerTheme
import com.jhbb.core_ui.utils.MultiThemePreview

@Composable
fun SplashScreen(
    onFinishAnimation: () -> Unit
) {
    val animationDuration = 1500
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnimation = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(animationDuration),
        finishedListener = {
            onFinishAnimation()
        }
    )
    LaunchedEffect(true) {
        startAnimation = true
    }
    Surface {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
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
        SplashScreen {}
    }
}