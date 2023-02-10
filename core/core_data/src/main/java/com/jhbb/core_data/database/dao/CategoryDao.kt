package com.jhbb.core_data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.jhbb.core_data.database.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    suspend fun selectCategory(): List<CategoryEntity>

    @Update
    suspend fun updateCategories(entities: List<CategoryEntity>)
}