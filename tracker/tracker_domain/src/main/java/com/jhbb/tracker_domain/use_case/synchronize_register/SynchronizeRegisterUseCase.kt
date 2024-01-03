package com.jhbb.tracker_domain.use_case.synchronize_register

import com.jhbb.core_domain.model.Register
import kotlinx.coroutines.flow.Flow

interface SynchronizeRegisterUseCase {
    operator fun invoke(registers: List<Register>): Flow<Result<Register>>
}