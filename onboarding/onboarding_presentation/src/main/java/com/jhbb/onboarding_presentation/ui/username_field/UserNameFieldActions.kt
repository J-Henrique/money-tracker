package com.jhbb.onboarding_presentation.ui.username_field

import com.jhbb.core_ui.utils.UiEvent
import kotlinx.coroutines.flow.SharedFlow

data class UserNameFieldActions(
    val onNext: () -> Unit,
    val uiEvent: SharedFlow<UiEvent>,
    val onEvent: (event: UserNameFieldScreenEvent) -> Unit
)