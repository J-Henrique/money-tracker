package com.jhbb.tracker_data.repository

import com.jhbb.core_data.database.dao.RegisterDao
import com.jhbb.core_data.database.entity.toDomain
import com.jhbb.core_data.database.entity.toEntity
import com.jhbb.core_domain.model.CategoryType
import com.jhbb.core_domain.model.Register
import com.jhbb.core_domain.model.SynchronizationStatus
import com.jhbb.tracker_data.remote.RegisterService
import com.jhbb.tracker_data.remote.dto.toDto
import com.jhbb.tracker_domain.repository.RegisterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val registerDao: RegisterDao,
    private val registerService: RegisterService,
) : RegisterRepository {

    override fun getAllRegisters(): Flow<List<Register>> {
        return registerDao.getAllRegisters().map { registers ->
            registers.map { it.toDomain() }
        }
    }

    override fun getRegistersByCategory(filter: List<CategoryType>): Flow<List<Register>> {
        return registerDao.filterRegisterByCategory(filter).map { registers ->
            registers.map { it.toDomain() }
        }
    }

    override suspend fun insertRegister(register: Register): Result<Unit> {
        return Result.success(registerDao.insertRegister(register.toEntity()))
    }

    override suspend fun updateRegister(register: Register): Result<Unit> {
        return Result.success(registerDao.updateRegister(register.toEntity()))
    }

    override suspend fun synchronizeRegister(register: Register): Result<Unit> {
        try {
            withContext(Dispatchers.IO) {
                registerService.postRegister(register.toDto())
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return Result.failure(RegisterRepositoryException.RemoteSyncException(register))
        }

        return try {
            updateRegister(register.copy(syncStatus = SynchronizationStatus.SUCCESS))
            Result.success(Unit)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(RegisterRepositoryException.LocalSyncException(register))
        }
    }
}