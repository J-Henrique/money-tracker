package com.jhbb.core_ui.utils

sealed interface UiEvent {
    object NavigateForward : UiEvent
}