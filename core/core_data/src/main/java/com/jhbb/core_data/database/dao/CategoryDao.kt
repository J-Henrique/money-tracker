package com.jhbb.core_data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jhbb.core_data.database.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert
    suspend fun insertCategory(category: CategoryEntity)

    @Query("SELECT * from category")
    fun selectCategory(): Flow<List<CategoryEntity>>
}