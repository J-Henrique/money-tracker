package com.jhbb.core_domain.model

data class Category(
    val id: Int,
    val type: CategoryType,
    val isEnabled: Boolean
)