package com.jhbb.tracker_presentation.ui.register

import com.jhbb.core_ui.utils.UiEvent
import kotlinx.coroutines.flow.Flow

data class RegisterScreenActions(
    val onComplete: () -> Unit,
    val uiEvent: Flow<UiEvent>,
    val onEvent: (event: RegisterScreenEvent) -> Unit
)
