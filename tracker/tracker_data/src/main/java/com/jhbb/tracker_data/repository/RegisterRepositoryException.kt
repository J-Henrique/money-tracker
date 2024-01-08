package com.jhbb.tracker_data.repository

import com.jhbb.core_domain.model.Register

sealed class RegisterRepositoryException: Throwable() {
    data class RemoteSyncException(val item: Register): RegisterRepositoryException()
    data class LocalSyncException(val item: Register): RegisterRepositoryException()
}