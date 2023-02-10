package com.jhbb.onboarding_presentation.ui.categories

import com.jhbb.core_ui.ui.components.category_card.CategoryUiModel

data class CategoriesScreenState(
    val categories: List<CategoryUiModel> = emptyList(),
    val showError: Boolean = false
)
