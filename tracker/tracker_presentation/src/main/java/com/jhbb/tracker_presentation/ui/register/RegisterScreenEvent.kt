package com.jhbb.tracker_presentation.ui.register

sealed interface RegisterScreenEvent {
    data class OnEnterValue(val value: String): RegisterScreenEvent
    data class OnEnterTitle(val title: String): RegisterScreenEvent
    data class OnEnterDescription(val description: String): RegisterScreenEvent
}