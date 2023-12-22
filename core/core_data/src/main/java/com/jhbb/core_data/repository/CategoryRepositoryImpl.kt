package com.jhbb.core_data.repository

import com.jhbb.core_data.database.dao.CategoryDao
import com.jhbb.core_data.database.entity.toDomain
import com.jhbb.core_data.database.entity.toEntity
import com.jhbb.core_domain.model.Category
import com.jhbb.core_domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
) : CategoryRepository {
    override suspend fun getCategories(isEnabled: Boolean?): List<Category> {
        val categories = if (isEnabled == null) {
            categoryDao.selectCategory()
        } else {
            categoryDao.selectCategoryByFilter(isEnabled)
        }
        return categories.map { it.toDomain() }
    }

    override suspend fun updateCategories(entities: List<Category>): Result<Unit> {
        return try {
            categoryDao.updateCategories(entities.map { it.toEntity() })
            Result.success(Unit)
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}