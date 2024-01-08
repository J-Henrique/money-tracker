package com.jhbb.tracker_data.remote.dto

import com.jhbb.core_domain.model.CategoryType
import com.jhbb.core_domain.model.Register
import com.jhbb.core_domain.model.SynchronizationStatus
import java.util.Date

data class RegisterDto(
    val title: String
)

fun RegisterDto.toDomain() = Register(
    title = title,
    description = "",
    value = 0.0,
    time = Date(),
    categoryType = CategoryType.BAR,
    isIncome = true,
    syncStatus = SynchronizationStatus.PENDING
)

fun Register.toDto() = RegisterDto(
    title = title
)
