package com.jhbb.tracker_presentation.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhbb.core_domain.model.Register
import com.jhbb.core_domain.model.SynchronizationStatus
import com.jhbb.tracker_domain.repository.RegisterRepository
import com.jhbb.tracker_domain.use_case.synchronize_register.SynchronizationException
import com.jhbb.tracker_domain.use_case.synchronize_register.SynchronizeRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val registerRepository: RegisterRepository,
    private val synchronizeRegisterUseCase: SynchronizeRegisterUseCase,
) : ViewModel() {

    var state by mutableStateOf(HomeScreenState())
        private set

    init {
        registerRepository.getRegisters().onEach {
            state.registers.run {
                clear()
                addAll(it)
            }
            synchronize(state.getPendingSyncItems())
        }.launchIn(viewModelScope)
    }

    private fun synchronize(registers: List<Register>) {
        synchronizeRegisterUseCase.invoke(registers).onEach { result ->
            result.onSuccess {
                val successItemIndex = state.registers.indexOf(it)
                state.updateSyncStatus(successItemIndex, SynchronizationStatus.SUCCESS)
            }.onFailure {
                val failureItemIndex = (it as SynchronizationException).item.run {
                    state.registers.indexOf(this)
                }
                state.updateSyncStatus(failureItemIndex, SynchronizationStatus.ERROR)
            }
        }.launchIn(viewModelScope)
    }

    fun refreshItem(item: Register) {
        val indexToUpdate = state.registers.indexOf(item)
        state.updateSyncStatus(indexToUpdate, SynchronizationStatus.PENDING)
        synchronize(listOf(item))
    }
}