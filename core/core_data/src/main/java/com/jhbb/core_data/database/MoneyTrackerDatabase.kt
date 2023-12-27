package com.jhbb.core_data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jhbb.core_data.converter.DateConverter
import com.jhbb.core_data.database.dao.CategoryDao
import com.jhbb.core_data.database.dao.RegisterDao
import com.jhbb.core_data.database.entity.CategoryEntity
import com.jhbb.core_data.database.entity.RegisterEntity

@Database(
    entities = [CategoryEntity::class, RegisterEntity::class],
    version = 2,
    exportSchema = true,
    autoMigrations = [
        AutoMigration (from = 1, to = 2)
    ]
)
@TypeConverters(DateConverter::class)
abstract class MoneyTrackerDatabase: RoomDatabase() {
    abstract val categoryDao: CategoryDao
    abstract val registerDao: RegisterDao
}