package com.jhbb.tracker_presentation.ui.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.jhbb.core_domain.model.CategoryType
import com.jhbb.core_domain.model.Register
import com.jhbb.core_domain.model.SynchronizationStatus
import com.jhbb.core_ui.ui.components.category_card.CategoryUiModel

data class HomeScreenState(
    val registers: SnapshotStateList<Register> = mutableStateListOf()
)

fun HomeScreenState.setRegisters(items: List<Register>) {
    registers.run {
        clear()
        addAll(items)
    }
}

fun HomeScreenState.updateSyncStatus(item: Register, status: SynchronizationStatus) {
    val indexToUpdate = registers.indexOfFirst { it.id == item.id }
    registers[indexToUpdate] = registers[indexToUpdate].copy(syncStatus = status)
}

fun HomeScreenState.getPendingSyncItems(): List<Register> {
    return registers.filter { it.syncStatus == SynchronizationStatus.PENDING }
}