package com.jhbb.tracker_presentation.ui.home

import com.jhbb.core_ui.ui.components.expense_card.ExpenseCardUiModel

data class HomeScreenState(
    val expenses: List<ExpenseCardUiModel> = emptyList()
)

fun HomeScreenState.getMonths() = listOf(
    "Janeiro",
    "Fevereiro",
    "Março",
    "Abril",
    "Maio",
    "Junho",
    "Julho",
    "Agosto",
    "Setembro",
    "Outubro",
    "Novembro",
    "Dezembro",
)