package com.jhbb.core_ui.utils

import androidx.annotation.StringRes

sealed interface UiEvent {
    object NavigateForward : UiEvent
    data class ShowToast(@StringRes val messageResId: Int) : UiEvent
}