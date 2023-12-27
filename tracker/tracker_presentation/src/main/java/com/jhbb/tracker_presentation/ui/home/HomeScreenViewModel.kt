package com.jhbb.tracker_presentation.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhbb.core_domain.repository.RegisterRepository
import com.jhbb.core_ui.ui.components.expense_card.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val registerRepository: RegisterRepository
) : ViewModel() {

    var state by mutableStateOf(HomeScreenState())
        private set

    init {
        viewModelScope.launch {
            registerRepository.getRegisters()
                .map { registers ->
                    registers.map { it.toUiModel() }
                }
                .collect {
                    state = state.copy(expenses = it)
                }
        }
    }
}