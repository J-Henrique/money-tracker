package com.jhbb.tracker_presentation.ui.register

sealed interface RegisterScreenEvent {
    data class OnToggleSwitcher(val isActive: Boolean): RegisterScreenEvent
    data class OnEnterValue(val value: String): RegisterScreenEvent
    data class OnEnterTitle(val title: String): RegisterScreenEvent
    data class OnEnterDescription(val description: String): RegisterScreenEvent
    data class OnSelectCategory(val categoryDescription: String): RegisterScreenEvent
    object OnRegister: RegisterScreenEvent
}