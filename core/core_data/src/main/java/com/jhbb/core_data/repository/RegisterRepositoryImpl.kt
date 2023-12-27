package com.jhbb.core_data.repository

import com.jhbb.core_data.database.dao.RegisterDao
import com.jhbb.core_data.database.entity.toDomain
import com.jhbb.core_data.database.entity.toEntity
import com.jhbb.core_domain.model.Register
import com.jhbb.core_domain.repository.RegisterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val registerDao: RegisterDao
) : RegisterRepository {

    override fun getRegisters(): Flow<List<Register>> {
        return registerDao.selectRegister().map {
                registers -> registers.map { it.toDomain() }
        }
    }

    override suspend fun insertRegister(register: Register): Result<Unit> {
        return Result.success(registerDao.insertRegister(register.toEntity()))
    }
}