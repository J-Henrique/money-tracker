package com.jhbb.core_domain.repository

import com.jhbb.core_domain.model.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
    suspend fun updateCategories(entities: List<Category>): Result<Unit>
}