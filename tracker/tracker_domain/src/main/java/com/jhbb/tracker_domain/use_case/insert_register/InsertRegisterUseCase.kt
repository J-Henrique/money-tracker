package com.jhbb.tracker_domain.use_case.insert_register

interface InsertRegisterUseCase {
    suspend operator fun invoke(
        value: String,
        title: String,
        description: String,
        categoryName: String?,
        isIncome: Boolean,
    ): Result<Unit>
}