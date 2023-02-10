package com.jhbb.onboarding_presentation.ui.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhbb.core_ui.ui.components.category_card.toDomain
import com.jhbb.core_ui.ui.components.category_card.toUiModel
import com.jhbb.core_ui.utils.UiEvent
import com.jhbb.onboarding_domain.repository.OnboardingUserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val onboardingUserInfoRepository: OnboardingUserInfoRepository
): ViewModel() {

    var state by mutableStateOf(CategoriesScreenState())
        private set

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            val categories = onboardingUserInfoRepository.getCategories().map {
                it.toUiModel()
            }
            state = state.copy(categories = categories)
        }
    }

    fun onEvent(event: CategoriesScreenEvent) {
        when (event) {
            is CategoriesScreenEvent.OnItemClick -> {
                state = state.copy(
                    categories = state.categories.map {
                        if (it == event.item) {
                            it.copy(isEnabled = !event.item.isEnabled)
                        } else it
                    }
                )
            }
            CategoriesScreenEvent.OnNextButtonClick -> {
                viewModelScope.launch {
                    onboardingUserInfoRepository.updateCategories(
                        state.categories.map { it.toDomain() }
                    )
                        .onSuccess { _uiEvent.emit(UiEvent.NavigateForward) }
                        .onFailure { state = state.copy(showError = true) }
                }
            }
            CategoriesScreenEvent.OnErrorDialogDismiss -> state = state.copy(showError = false)
        }
    }
}