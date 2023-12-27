package com.jhbb.tracker_domain.use_case.insert_register

import com.jhbb.core_domain.model.CategoryType
import com.jhbb.core_domain.model.Register
import com.jhbb.core_domain.repository.RegisterRepository
import java.util.Date
import javax.inject.Inject

class InsertRegisterUseCase @Inject constructor(
    private val registerRepository: RegisterRepository
) {
    suspend operator fun invoke(
        value: String,
        title: String,
        description: String,
        categoryName: String?,
        isIncome: Boolean
    ): Result<Unit> {
        val currentValue = try {
            value.toDouble()
        } catch (ex: Exception) {
            return Result.failure(InsertRegisterException.InvalidInputException)
        }

        if (title.isBlank() || description.isBlank() || categoryName.isNullOrBlank()) {
            return Result.failure(InsertRegisterException.InvalidInputException)
        }
        val register = Register(
            value = currentValue,
            title = title,
            description = description,
            time = Date(),
            categoryType = CategoryType.valueOf(categoryName),
            isIncome = isIncome,
        )
        return registerRepository.insertRegister(register)
            .onSuccess { Result.success(Unit) }
            .onFailure {
                Result.failure<InsertRegisterException>(InsertRegisterException.InsertionException)
            }
    }
}