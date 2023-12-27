package com.jhbb.tracker_presentation.ui.register

import com.jhbb.core_ui.ui.components.category_card.CategoryUiModel

data class RegisterScreenState(
    val isIncome: Boolean = false,
    val value: String = "",
    val title: String = "",
    val description: String = "",
    val categories: List<CategoryUiModel> = emptyList(),
    val selectedCategory: CategoryUiModel? = null,
    val hasError: Boolean = false,
)