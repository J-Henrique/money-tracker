package com.jhbb.tracker_domain.repository

import com.jhbb.core_domain.model.CategoryType
import com.jhbb.core_domain.model.Register
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    fun getAllRegisters(): Flow<List<Register>>
    fun getRegistersByCategory(filter: List<CategoryType>): Flow<List<Register>>
    suspend fun insertRegister(register: Register): Result<Unit>
    suspend fun updateRegister(register: Register): Result<Unit>
    suspend fun synchronizeRegister(register: Register): Result<Unit>
}