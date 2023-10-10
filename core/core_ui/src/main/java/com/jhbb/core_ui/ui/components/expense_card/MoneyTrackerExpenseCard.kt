package com.jhbb.core_ui.ui.components.expense_card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jhbb.core_ui.ui.components.category_card.CategoryUiType
import com.jhbb.core_ui.ui.theme.MoneyTrackerTheme
import java.text.NumberFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun MoneyTrackerExpenseCard(
    cardUiModel: ExpenseCardUiModel, cardHeight: Dp = Dp.Unspecified
) {
    Surface(
        modifier = Modifier
            .padding(2.dp)
            .clip(shape = MaterialTheme.shapes.small)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .heightIn(min = 90.dp, max = 96.dp)
                .height(cardHeight)
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = cardUiModel.categoryType.icon),
                contentDescription = null,
                tint = cardUiModel.categoryType.tint,
                modifier = Modifier
                    .aspectRatio(1f)
                    .background(
                        color = cardUiModel.categoryType.tint.copy(alpha = 0.1f),
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
                    text = cardUiModel.title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = cardUiModel.description,
                    style = MaterialTheme.typography.subtitle1,
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = NumberFormat.getCurrencyInstance().format(cardUiModel.value),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6.copy(
                        color = Color.Red
                    )
                )
                Text(
                    text = cardUiModel.time.format(DateTimeFormatter.ofPattern("HH:mm")),
                    style = MaterialTheme.typography.subtitle1,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMoneyTrackerExpenseCard() {
    MoneyTrackerTheme {
        val card = ExpenseCardUiModel(
            title = "Titulo",
            description = "Descrição",
            value = 145.31,
            time = LocalTime.now(),
            categoryType = CategoryUiType.BAR
        )
        Column {
            MoneyTrackerExpenseCard(cardHeight = 0.dp, cardUiModel = card)
            Divider(modifier = Modifier.height(16.dp))
            MoneyTrackerExpenseCard(cardHeight = 156.dp, cardUiModel = card)
        }
    }
}