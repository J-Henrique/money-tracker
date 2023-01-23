package com.jhbb.onboarding_presentation.ui.username_field

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhbb.designsystem.utils.UiEvent
import com.jhbb.onboarding_domain.use_case.FilterLettersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserNameFieldViewModel @Inject constructor(
    private val filterLettersUseCase: FilterLettersUseCase
): ViewModel() {
    var username by mutableStateOf("")
        private set

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onEvent(event: UserNameFieldScreenEvent) {
        when (event) {
            is UserNameFieldScreenEvent.OnEnterText -> {
                username = filterLettersUseCase(event.text)
            }
            UserNameFieldScreenEvent.OnNextButtonClick -> {
                viewModelScope.launch {
                    _uiEvent.emit(UiEvent.NavigateForward)
                }
            }
        }
    }
}