package com.jhbb.tracker_presentation.ui.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.jhbb.core_domain.model.Register
import com.jhbb.core_domain.model.SynchronizationStatus

data class HomeScreenState(
    val registers: SnapshotStateList<Register> = mutableStateListOf()
)

fun HomeScreenState.updateSyncStatus(index: Int, status: SynchronizationStatus) {
    registers[index] = registers[index].copy(syncStatus = status)
}

fun HomeScreenState.getPendingSyncItems(): List<Register> {
    return registers.filter { it.syncStatus == SynchronizationStatus.PENDING }
}