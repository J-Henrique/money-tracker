package com.jhbb.core_ui.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jhbb.core_ui.R
import com.jhbb.core_ui.ui.theme.MoneyTrackerTheme
import com.jhbb.core_ui.utils.MultiThemePreview

@Composable
fun MoneyTrackerTopBar(
    modifier: Modifier = Modifier,
    title: String? = null,
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
            text = title ?: stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h2.copy(
                fontFamily = FontFamily.Default,
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