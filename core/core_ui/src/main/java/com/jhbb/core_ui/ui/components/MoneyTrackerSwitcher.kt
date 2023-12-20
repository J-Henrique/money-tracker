package com.jhbb.core_ui.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jhbb.core_ui.R
import com.jhbb.core_ui.ui.theme.MoneyTrackerTheme
import com.jhbb.core_ui.utils.MultiThemePreview

@Composable
fun MoneyTrackerSwitcher(
    isActive: Boolean = false,
    onToggle: () -> Unit,
    size: Dp = 46.dp
) {
    val offset by animateDpAsState(
        targetValue = if (isActive) size else 0.dp,
        animationSpec = tween(durationMillis = 300),
        label = ""
    )

    Box(modifier = Modifier
        .width(size * 2)
        .height(size)
        .clip(shape = CircleShape)
        .clickable { onToggle() }
        .background(Color.White)
        .background(if (isActive) Color.Blue.copy(alpha = 0.5f) else Color.Red.copy(alpha = 0.5f))
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .offset(x = offset)
                .padding(all = 8.dp)
                .clip(shape = CircleShape)
                .background(if (isActive) Color.Blue else Color.Red)
        ) {}
        Row(
            modifier = Modifier
                .border(
                    border = BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colors.primary
                    ),
                    shape = CircleShape
                )
        ) {
            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(size / 2),
                    painter = painterResource(id = R.drawable.ic_remove),
                    contentDescription = null,
                    tint = if (isActive) Color.White else Color.White
                )
            }
            Box(
                modifier = Modifier.size(size),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(size / 2),
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                    tint = if (isActive) Color.White else Color.White
                )
            }
        }
    }
}

@MultiThemePreview
@Composable
fun PreviewTrackerSwitcher() {
    MoneyTrackerTheme {
        Column {
            MoneyTrackerSwitcher(isActive = false, onToggle = {})
            MoneyTrackerSwitcher(isActive = true, onToggle = {})
        }
    }
}