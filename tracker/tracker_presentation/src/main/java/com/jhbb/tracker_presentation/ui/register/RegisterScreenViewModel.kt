package com.jhbb.tracker_presentation.ui.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhbb.core_domain.repository.CategoryRepository
import com.jhbb.core_ui.ui.components.category_card.CategoryUiModel
import com.jhbb.core_ui.ui.components.category_card.toUiModel
import com.jhbb.core_ui.utils.UiEvent
import com.jhbb.tracker_domain.use_case.insert_register.InsertRegisterUseCase
import com.jhbb.tracker_presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val insertRegisterUseCase: InsertRegisterUseCase,
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    var state by mutableStateOf(RegisterScreenState())
        private set

    private lateinit var categories: List<CategoryUiModel>

    init {
        viewModelScope.launch {
            categories =
                categoryRepository.getCategories(isEnabled = true).map { it.toUiModel() }
            state = state.copy(categories = categories)
        }
    }

    fun onEvent(event: RegisterScreenEvent) {
        state = when (event) {
            is RegisterScreenEvent.OnToggleSwitcher -> state.copy(isIncome = !event.isActive)
            is RegisterScreenEvent.OnEnterValue -> state.copy(value = event.value)
            is RegisterScreenEvent.OnEnterTitle -> state.copy(title = event.title)
            is RegisterScreenEvent.OnEnterDescription -> {
                state.copy(description = event.description)
            }

            is RegisterScreenEvent.OnSelectCategory -> {
                state.copy(selectedCategory = categories.find {
                        it.type.description == event.categoryDescription
                    }
                )
            }

            RegisterScreenEvent.OnRegister -> {
                viewModelScope.launch {
                    insertRegisterUseCase(
                        value = state.value,
                        title = state.title,
                        description = state.description,
                        categoryName = state.selectedCategory?.type?.name,
                        isIncome = state.isIncome
                    ).onSuccess {
                        _uiEvent.emit(UiEvent.NavigateForward)
                    }.onFailure {
                        _uiEvent.emit(UiEvent.ShowToast(R.string.tracker_register_error_message))
                    }
                }
                return
            }
        }
    }
}