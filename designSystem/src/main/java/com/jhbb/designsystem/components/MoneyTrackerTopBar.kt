package com.jhbb.designsystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jhbb.core_ui.utils.MultiThemePreview
import com.jhbb.designsystem.R
import com.jhbb.designsystem.ui.theme.MoneyTrackerTheme

@Composable
fun MoneyTrackerTopBar(
    modifier: Modifier = Modifier,
    onNavigateBack: (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primaryVariant,
    ) {
        if (onNavigateBack != null) {
            IconButton(onClick = { onNavigateBack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        }
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h2.copy(
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onPrimary
            ),
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@MultiThemePreview
@Composable
fun PreviewMoneyTrackerTopBar() {
    MoneyTrackerTheme {
        MoneyTrackerTopBar()
    }
}