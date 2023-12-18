package com.jhbb.tracker_presentation.ui.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(RegisterScreenState())
        private set

    fun onEvent(event: RegisterScreenEvent) {
        state = when (event) {
            is RegisterScreenEvent.OnEnterValue -> state.copy(value = event.value)
            is RegisterScreenEvent.OnEnterTitle -> state.copy(title = event.title)
            is RegisterScreenEvent.OnEnterDescription -> {
                state.copy(description = event.description)
            }
        }
    }
}