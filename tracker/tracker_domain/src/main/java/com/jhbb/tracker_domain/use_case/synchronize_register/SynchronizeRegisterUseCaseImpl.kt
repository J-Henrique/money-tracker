package com.jhbb.tracker_domain.use_case.synchronize_register

import com.jhbb.core_domain.model.Register
import com.jhbb.core_domain.repository.RegisterRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SynchronizeRegisterUseCaseImpl @Inject constructor(
    private val registerRepository: RegisterRepository
) : SynchronizeRegisterUseCase {

    override fun invoke(registers: List<Register>): Flow<Result<Register>> {
        return flow {
            registers.forEach {
                delay(2000)
                emit(Result.success(it))
//                emit(Result.failure(SynchronizationException(it)))
            }
        }
    }
}