package com.jhbb.tracker_presentation.ui.home

import com.jhbb.core_ui.ui.components.expense_card.ExpenseCardUiModel

data class HomeScreenState(
    val expenses: List<ExpenseCardUiModel> = emptyList()
)
