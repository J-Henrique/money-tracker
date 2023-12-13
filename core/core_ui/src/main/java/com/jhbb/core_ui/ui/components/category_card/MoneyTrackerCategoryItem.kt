package com.jhbb.core_ui.ui.components.category_card

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jhbb.core_ui.ui.theme.MoneyTrackerTheme
import com.jhbb.core_ui.utils.MultiThemePreview

@Composable
fun MoneyTrackerCategoryItem(
    category: CategoryUiModel,
    onClick: (CategoryUiModel) -> Unit
) {
    val transition = updateItemTransition(status = category.isEnabled)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .shadow(6.dp, MaterialTheme.shapes.small.copy(CornerSize(transition.cornerSize)))
            .clip(MaterialTheme.shapes.small.copy(CornerSize(transition.cornerSize)))
            .background(
                color = transition.background,
                shape = MaterialTheme.shapes.small.copy(CornerSize(transition.cornerSize))
            )
            .clickable { onClick(category) }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .alpha(transition.alpha)
        ) {
            Icon(
                painter = painterResource(id = category.type.icon),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = category.type.description,
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (category.isEnabled) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .scale(transition.checkScale)
                )
            }
        }
    }
}

private class TransitionData(
    background: State<Color>,
    checkScale: State<Float>,
    alpha: State<Float>,
    cornerSize: State<Int>,
) {
    val background by background
    val checkScale by checkScale
    val alpha by alpha
    val cornerSize by cornerSize
}

@Composable
private fun updateItemTransition(status: Boolean): TransitionData {
    val transition = updateTransition(targetState = status, label = "")
    val background = transition.animateColor(label = "") { isSelected ->
        if (isSelected) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primary
    }
    val checkScale = transition.animateFloat(label = "") { isSelected ->
        if (isSelected) 1f else 0.1f
    }
    val alpha = transition.animateFloat(label = "") { isSelected ->
        if (isSelected) 1f else 0.4f
    }
    val cornerSize = transition.animateInt(label = "") { isSelected ->
        if (isSelected) 80 else 20
    }
    return remember(transition) {
        TransitionData(background, checkScale, alpha, cornerSize)
    }
}

@MultiThemePreview
@Composable
fun PreviewMoneyTrackerCategoryCard() {
    MoneyTrackerTheme {
        MoneyTrackerCategoryItem(
            CategoryUiModel(1, CategoryUiType.BAR, false)
        ) {}
    }
}