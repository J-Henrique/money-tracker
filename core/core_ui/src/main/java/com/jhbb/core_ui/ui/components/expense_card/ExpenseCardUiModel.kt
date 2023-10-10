package com.jhbb.core_ui.ui.components.expense_card

import com.jhbb.core_ui.ui.components.category_card.CategoryUiType
import java.text.NumberFormat
import java.time.LocalTime

data class ExpenseCardUiModel(
    val title: String,
    val description: String,
    val value: Double,
    val time: LocalTime,
    val categoryType: CategoryUiType,
    val isExpense: Boolean
) {
    val monetaryValue: String
        get() {
            val value = NumberFormat.getCurrencyInstance().format(value)
            return if (isExpense) "- ".plus(value) else value
        }
}
