package com.jhbb.tracker_domain.use_case.synchronize_register

import com.jhbb.core_domain.model.Register
import com.jhbb.tracker_domain.repository.RegisterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SynchronizeRegisterUseCaseImpl @Inject constructor(
    private val registerRepository: RegisterRepository
) : SynchronizeRegisterUseCase {

    override fun invoke(registers: List<Register>): Flow<Result<Register>> {
        return flow {
            registers.forEach { register ->
                registerRepository.synchronizeRegister(register)
                    .onSuccess { emit(Result.success(register)) }
                    .onFailure { emit(Result.failure(SynchronizationException(register))) }
            }
        }
    }
}