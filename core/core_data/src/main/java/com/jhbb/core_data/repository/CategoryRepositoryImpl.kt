package com.jhbb.core_data.repository

import com.jhbb.core_data.database.dao.CategoryDao
import com.jhbb.core_domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao
): CategoryRepository {

    override suspend fun getCategory() {
        return categoryDao.selectCategory().collect {

        }
    }
}