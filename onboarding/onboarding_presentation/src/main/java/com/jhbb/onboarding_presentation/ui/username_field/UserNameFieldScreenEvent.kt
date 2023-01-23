package com.jhbb.onboarding_presentation.ui.username_field

sealed interface UserNameFieldScreenEvent {
    data class OnEnterText(val text: String): UserNameFieldScreenEvent
    object OnNextButtonClick : UserNameFieldScreenEvent
}