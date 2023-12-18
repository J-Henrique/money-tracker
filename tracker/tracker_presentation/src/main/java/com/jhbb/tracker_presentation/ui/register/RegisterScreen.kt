package com.jhbb.tracker_presentation.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jhbb.core_ui.ui.components.MoneyTrackerTopBar
import com.jhbb.core_ui.ui.theme.MoneyTrackerTheme
import com.jhbb.core_ui.utils.MultiThemePreview
import com.jhbb.tracker_presentation.R

@Composable
fun RegisterScreen() {
    Scaffold(
        topBar = {
            MoneyTrackerTopBar(
                title = stringResource(id = R.string.tracker_register),
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .background(Color.Red),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalAlignment = Alignment.Bottom
            ) {
                Text("")
            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f)
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp
                        )
                    )
                    .background(color = Color.White),
            ) {
                Text("")
            }
        }
    }
}

@MultiThemePreview
@Composable
fun PreviewRegisterScreen() {
    MoneyTrackerTheme {
        RegisterScreen()
    }
}