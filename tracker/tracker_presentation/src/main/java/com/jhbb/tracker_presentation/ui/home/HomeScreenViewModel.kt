package com.jhbb.tracker_presentation.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhbb.core_domain.model.Register
import com.jhbb.core_domain.model.SynchronizationStatus
import com.jhbb.core_domain.repository.CategoryRepository
import com.jhbb.core_ui.ui.components.category_card.CategoryUiModel
import com.jhbb.core_ui.ui.components.category_card.toDomain
import com.jhbb.core_ui.ui.components.category_card.toUiModel
import com.jhbb.tracker_domain.repository.RegisterRepository
import com.jhbb.tracker_domain.use_case.synchronize_register.SynchronizationException
import com.jhbb.tracker_domain.use_case.synchronize_register.SynchronizeRegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val registerRepository: RegisterRepository,
    private val categoryRepository: CategoryRepository,
    private val synchronizeRegisterUseCase: SynchronizeRegisterUseCase,
) : ViewModel() {

    var state by mutableStateOf(HomeScreenState())
        private set

    var categoriesFilter = mutableStateListOf<CategoryUiModel>()
        private set

    init {
        getAllRegisters()
        getCategoriesFilters()
    }

    private fun getCategoriesFilters() {
        viewModelScope.launch {
            val categories = categoryRepository.getCategories(isEnabled = true)
                .map { it.toUiModel().copy(isEnabled = false) }
            categoriesFilter.addAll(categories)
        }
    }

    private fun getAllRegisters() {
        registerRepository.getAllRegisters().onEach {
            state.setRegisters(it)
            synchronize(state.getPendingSyncItems())
        }.launchIn(viewModelScope)
    }

    private fun synchronize(registers: List<Register>) {
        synchronizeRegisterUseCase.invoke(registers).onEach { result ->
            result.onSuccess {
                state.updateSyncStatus(it, SynchronizationStatus.SUCCESS)
            }.onFailure { error ->
                val failureItem = (error as SynchronizationException).item
                state.updateSyncStatus(failureItem, SynchronizationStatus.ERROR)
            }
        }.launchIn(viewModelScope)
    }

    private fun filterRegisters() {
        val selectedFilters = categoriesFilter.filter { it.isEnabled }.map { it.toDomain().type }
        registerRepository.getRegistersByCategory(selectedFilters).onEach {
            state.setRegisters(it)
        }.launchIn(viewModelScope)
    }

    fun refreshItem(item: Register) {
        state.updateSyncStatus(item, SynchronizationStatus.PENDING)
        synchronize(listOf(item))
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.OnFilter -> filterRegisters()
            HomeScreenEvent.OnClearFilter -> {
                val categories = categoriesFilter.filter { it.isEnabled }
                categories.forEach {
                    val index = categoriesFilter.indexOf(it)
                    categoriesFilter[index] = categoriesFilter[index].copy(isEnabled = false)
                }
                getAllRegisters()
            }
            is HomeScreenEvent.OnSelectFilter -> {
                val categoryIndex = categoriesFilter.indexOf(event.category)
                categoriesFilter[categoryIndex] =
                    categoriesFilter[categoryIndex].copy(isEnabled = !event.category.isEnabled)
            }
        }
    }
}