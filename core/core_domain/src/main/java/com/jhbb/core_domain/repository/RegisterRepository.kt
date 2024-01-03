package com.jhbb.core_domain.repository

import com.jhbb.core_domain.model.Register
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    fun getRegisters(): Flow<List<Register>>
    suspend fun insertRegister(register: Register): Result<Unit>
    suspend fun updateRegister(register: Register): Result<Unit>
}