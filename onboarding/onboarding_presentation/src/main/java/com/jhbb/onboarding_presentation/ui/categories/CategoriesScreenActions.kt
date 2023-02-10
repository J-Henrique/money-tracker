package com.jhbb.onboarding_presentation.ui.categories

import com.jhbb.core_ui.utils.UiEvent
import kotlinx.coroutines.flow.Flow

data class CategoriesScreenActions(
    val onNext: () -> Unit,
    val uiEvent: Flow<UiEvent>,
    val onEvent: (event: CategoriesScreenEvent) -> Unit
)