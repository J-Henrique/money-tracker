package com.jhbb.onboarding_presentation.ui.username_field

import com.jhbb.core_ui.utils.UiEvent
import kotlinx.coroutines.flow.SharedFlow

data class UserNameFieldScreenActions(
    val onNext: () -> Unit,
    val onBack: () -> Unit,
    val uiEvent: SharedFlow<UiEvent>,
    val onEvent: (event: UserNameFieldScreenEvent) -> Unit
)