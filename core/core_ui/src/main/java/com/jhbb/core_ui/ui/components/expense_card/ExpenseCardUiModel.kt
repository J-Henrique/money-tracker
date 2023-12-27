package com.jhbb.core_ui.ui.components.expense_card

import com.jhbb.core_domain.model.Register
import com.jhbb.core_ui.ui.components.category_card.CategoryUiType
import java.text.NumberFormat
import java.util.Date

data class ExpenseCardUiModel(
    val title: String,
    val description: String,
    val value: Double,
    val time: Date,
    val categoryType: CategoryUiType,
    val isIncome: Boolean
) {
    val monetaryValue: String
        get() {
            val value = if (isIncome) value else (value * -1)
            return NumberFormat.getCurrencyInstance().format(value)
        }
}

fun Register.toUiModel() = ExpenseCardUiModel(
    title = title,
    description = title,
    value = value,
    time = time,
    categoryType = CategoryUiType.valueOf(categoryType.name),
    isIncome = isIncome
)