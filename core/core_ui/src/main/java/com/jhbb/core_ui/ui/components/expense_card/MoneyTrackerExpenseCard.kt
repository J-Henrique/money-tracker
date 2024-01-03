package com.jhbb.core_ui.ui.components.expense_card

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jhbb.core_domain.model.CategoryType
import com.jhbb.core_domain.model.Register
import com.jhbb.core_domain.model.SynchronizationStatus
import com.jhbb.core_ui.ui.components.category_card.CategoryUiType
import com.jhbb.core_ui.ui.theme.MoneyTrackerTheme
import com.jhbb.core_ui.utils.extensions.toFormattedDate
import java.util.Date

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MoneyTrackerExpenseCard(
    register: Register,
    cardHeight: Dp = Dp.Unspecified,
    onRefresh: (Register) -> Unit
) {
    val categoryUiType = CategoryUiType.valueOf(register.categoryType.name)

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Surface(
            color = MaterialTheme.colors.surface.copy(alpha = 0.2f),
            modifier = Modifier
                .padding(2.dp)
                .clip(shape = MaterialTheme.shapes.small)
                .weight(1f)
                .animateContentSize()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .heightIn(min = 90.dp, max = 96.dp)
                    .height(cardHeight)
                    .padding(14.dp)
            ) {
                Icon(
                    painter = painterResource(id = categoryUiType.icon),
                    contentDescription = null,
                    tint = categoryUiType.tint,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .background(
                            color = categoryUiType.tint.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(10.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                ) {
                    Text(
                        text = register.title,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h6
                    )
                    Text(
                        text = register.description,
                        style = MaterialTheme.typography.subtitle1,
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(
                        text = register.monetaryValue,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h6.copy(
                            color = if (register.isIncome) Color.Green else Color.Red
                        )
                    )
                    Text(
                        text = register.time.toFormattedDate(LocalContext.current),
                        style = MaterialTheme.typography.subtitle1,
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = register.syncStatus == SynchronizationStatus.PENDING,
            enter = scaleIn(),
            exit = scaleOut(),
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp)
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primaryVariant
            )
        }

        AnimatedVisibility(
            visible = register.syncStatus == SynchronizationStatus.ERROR,
            enter = scaleIn(),
            exit = scaleOut(),
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp)
        ) {
            IconButton(onClick = { onRefresh(register) }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMoneyTrackerExpenseCard() {
    MoneyTrackerTheme {
        val card1 = Register(
            title = "Titulo",
            description = "Descrição",
            value = 145.31,
            time = Date(),
            categoryType = CategoryType.SPORTS,
            isIncome = false,
            syncStatus = SynchronizationStatus.PENDING,
        )
        val card2 = Register(
            title = "Titulo",
            description = "Descrição",
            value = 145.31,
            time = Date(),
            categoryType = CategoryType.FOOD,
            isIncome = false,
            syncStatus = SynchronizationStatus.SUCCESS,
        )
        val card3 = Register(
            title = "Titulo",
            description = "Descrição",
            value = 145.31,
            time = Date(),
            categoryType = CategoryType.TRANSPORT,
            isIncome = false,
            syncStatus = SynchronizationStatus.ERROR,
        )
        Column {
            MoneyTrackerExpenseCard(cardHeight = 0.dp, register = card1, onRefresh = {})
            Divider(modifier = Modifier.height(16.dp))
            MoneyTrackerExpenseCard(cardHeight = 0.dp, register = card2, onRefresh = {})
            Divider(modifier = Modifier.height(16.dp))
            MoneyTrackerExpenseCard(cardHeight = 0.dp, register = card3, onRefresh = {})
        }
    }
}