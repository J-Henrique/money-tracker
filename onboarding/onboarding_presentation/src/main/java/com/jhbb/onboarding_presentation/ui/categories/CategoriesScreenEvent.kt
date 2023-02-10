package com.jhbb.onboarding_presentation.ui.categories

import com.jhbb.core_ui.ui.components.category_card.CategoryUiModel

sealed interface CategoriesScreenEvent {
    data class OnItemClick(val item: CategoryUiModel): CategoriesScreenEvent
    object OnNextButtonClick : CategoriesScreenEvent
    object OnErrorDialogDismiss : CategoriesScreenEvent
}