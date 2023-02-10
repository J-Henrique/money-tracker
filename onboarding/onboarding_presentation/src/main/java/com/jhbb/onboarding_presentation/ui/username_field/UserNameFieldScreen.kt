package com.jhbb.onboarding_presentation.ui.username_field

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jhbb.core_ui.ui.components.MoneyTrackerTopBar
import com.jhbb.core_ui.ui.theme.MoneyTrackerTheme
import com.jhbb.core_ui.utils.MultiThemePreview
import com.jhbb.core_ui.utils.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
fun UserNameFieldScreen(
    username: String,
    actions: UserNameFieldScreenActions
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(true) {
        focusRequester.requestFocus()
        actions.uiEvent.collect {
            when (it) {
                UiEvent.NavigateForward -> actions.onNext()
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MoneyTrackerTopBar()
        },
        floatingActionButton = {
            if (username.length >= 3) {
                FloatingActionButton(
                    onClick = {
                        actions.onEvent(UserNameFieldScreenEvent.OnNextButtonClick)
                    }
                ) {
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BasicTextField(
                value = username,
                onValueChange = {
                    actions.onEvent(UserNameFieldScreenEvent.OnEnterText(it))
                },
                textStyle = MaterialTheme.typography.h1.copy(
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center
                ),
                cursorBrush = SolidColor(MaterialTheme.colors.onBackground),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.focusRequester(focusRequester)
            )
        }
    }
}

@MultiThemePreview
@Composable
fun PreviewUserNameFieldScreen() {
    MoneyTrackerTheme {
        UserNameFieldScreen(
            username = "User Name",
            actions = UserNameFieldScreenActions(
                onNext = {},
                uiEvent = MutableSharedFlow(),
                onEvent = {}
            )
        )
    }
}