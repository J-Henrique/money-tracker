package com.jhbb.tracker_presentation.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jhbb.core_ui.ui.components.category_card.CategoryUiType
import com.jhbb.core_ui.ui.components.expense_card.ExpenseCardUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(HomeScreenState())
        private set
    val list = listOf(
        ExpenseCardUiModel(
            title = "Titulo",
            description = "Descrição",
            value = 145.31,
            time = LocalTime.now(),
            categoryType = CategoryUiType.EDUCATION,
            isExpense = true
        ), ExpenseCardUiModel(
            title = "Titulo",
            description = "Descrição",
            value = 145.31,
            time = LocalTime.now(),
            categoryType = CategoryUiType.SPORTS,
            isExpense = false
        ), ExpenseCardUiModel(
            title = "Titulo",
            description = "Descrição",
            value = 145.31,
            time = LocalTime.now(),
            categoryType = CategoryUiType.TRAVEL,
            isExpense = true
        )
    )


    init {
        state = state.copy(expenses = list)
    }
}