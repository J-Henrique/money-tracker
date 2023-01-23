package com.jhbb.onboarding_presentation.ui.username_field

interface UserNameFieldViewModel {
    val username: String
    fun onEnterText(text: String)
}