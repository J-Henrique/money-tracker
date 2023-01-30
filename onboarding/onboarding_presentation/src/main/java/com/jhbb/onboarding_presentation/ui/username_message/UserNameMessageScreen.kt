package com.jhbb.onboarding_presentation.ui.username_message

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jhbb.core_ui.ui.components.MoneyTrackerTopBar
import com.jhbb.core_ui.ui.theme.MoneyTrackerTheme
import com.jhbb.core_ui.utils.MultiThemePreview
import com.jhbb.onboarding_presentation.R
import kotlinx.coroutines.delay

@Composable
fun UserNameMessageScreen(
    onNext: () -> Unit
) {
    var animateComponents by remember { mutableStateOf(false) }
    LaunchedEffect(true ) {
        delay(800)
        animateComponents = true
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MoneyTrackerTopBar()
        },
        floatingActionButton = {
            AnimatedVisibility(visible = animateComponents) {
                FloatingActionButton(onClick = { onNext() }) {
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = animateComponents,
                enter = fadeIn() + slideInHorizontally()
            ) {
                Text(
                    text = stringResource(id = R.string.onboarding_username),
                    style = MaterialTheme.typography.h1,
                    textAlign = TextAlign.Right,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 56.dp)
                )
            }
        }
    }
}

@MultiThemePreview
@Composable
fun PreviewUserNameScreen() {
    MoneyTrackerTheme {
        UserNameMessageScreen {}
    }
}