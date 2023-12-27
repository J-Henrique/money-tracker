package com.jhbb.core_domain.model

import java.util.Date

data class Register(
    val id: Int? = null,
    val title: String,
    val description: String,
    val value: Double,
    val time: Date,
    val categoryType: CategoryType,
    val isIncome: Boolean
)