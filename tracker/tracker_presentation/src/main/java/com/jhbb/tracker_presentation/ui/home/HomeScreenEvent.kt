package com.jhbb.tracker_presentation.ui.home

import com.jhbb.core_ui.ui.components.category_card.CategoryUiModel

sealed interface HomeScreenEvent {
    data class OnSelectFilter(val category: CategoryUiModel): HomeScreenEvent
    object OnClearFilter: HomeScreenEvent
    object OnFilter: HomeScreenEvent
}