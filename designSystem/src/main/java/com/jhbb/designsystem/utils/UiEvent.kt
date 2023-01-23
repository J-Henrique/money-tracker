package com.jhbb.designsystem.utils

sealed interface UiEvent {
    object NavigateForward : UiEvent
}