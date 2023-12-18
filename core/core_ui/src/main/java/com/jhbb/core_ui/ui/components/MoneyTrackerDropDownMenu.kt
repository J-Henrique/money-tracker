package com.jhbb.core_ui.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jhbb.core_ui.ui.theme.MoneyTrackerTheme
import com.jhbb.core_ui.utils.MultiThemePreview
import com.jhbb.core_ui.utils.extensions.noRippleClickable

@Composable
fun MoneyTrackerDropDownMenu(
    defaultText: String,
    items: List<String>,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(defaultText) }

    val arrowIcon = if (isExpanded) {
        Icons.Default.KeyboardArrowUp
    } else {
        Icons.Default.KeyboardArrowDown
    }

    Surface(
        modifier = modifier
            .clip(shape = MaterialTheme.shapes.small)
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colors.primary
                ),
                shape = MaterialTheme.shapes.small
            )
            .defaultMinSize(minHeight = 48.dp)
            .animateContentSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(shape = MaterialTheme.shapes.small)
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colors.primary
                    ),
                    shape = MaterialTheme.shapes.small
                )
                .wrapContentWidth()
                .noRippleClickable {
                    isExpanded = true
                }
                .padding(8.dp)
        ) {
            Icon(
                imageVector = arrowIcon,
                contentDescription = null,
                tint = MaterialTheme.colors.secondaryVariant
            )
            Text(
                text = selectedItem,
                color = MaterialTheme.colors.onPrimary,
                fontWeight = FontWeight.Bold
            )
            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            selectedItem = item
                            isExpanded = false
                        }
                    ) {
                        Text(text = item)
                    }
                }
            }
        }
    }
    AnimatedVisibility(
        visible = selectedItem != defaultText,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        IconButton(onClick = { selectedItem = defaultText }) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = MaterialTheme.colors.onSecondary,
                modifier = Modifier.background(
                    color = MaterialTheme.colors.secondary,
                    shape = CircleShape
                )
            )
        }
    }
}

@MultiThemePreview
@Composable
fun PreviewMoneyTrackerDropDownMenu() {
    MoneyTrackerTheme {
        MoneyTrackerDropDownMenu(
            defaultText = "Dropdown Menu",
            items = listOf(
                "Item 1",
                "Item 2",
                "Item 3",
                "Item 4",
                "Item 5"
            )
        )
    }
}