package com.jhbb.core_domain.model

import java.text.NumberFormat
import java.util.Date

data class Register(
    val id: Int? = null,
    val title: String,
    val description: String,
    val value: Double,
    val time: Date,
    val categoryType: CategoryType,
    val isIncome: Boolean,
    val syncStatus: SynchronizationStatus,
) {
    val monetaryValue: String
        get() {
            val value = if (isIncome) value else (value * -1)
            return NumberFormat.getCurrencyInstance().format(value)
        }
}