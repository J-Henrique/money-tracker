package com.jhbb.core_data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jhbb.core_data.database.dao.CategoryDao
import com.jhbb.core_data.database.entity.CategoryEntity

@Database(
    entities = [CategoryEntity::class],
    version = 1,
    exportSchema = true
)
abstract class MoneyTrackerDatabase: RoomDatabase() {
    abstract val categoryDao: CategoryDao
}