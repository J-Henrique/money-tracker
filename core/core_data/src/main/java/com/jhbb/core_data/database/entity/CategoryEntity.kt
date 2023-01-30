package com.jhbb.core_data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "category_id")
    val id: Int,

    @ColumnInfo("category_name")
    val name: String,

    @ColumnInfo(name = "category_enabled")
    val isEnabled: Boolean,
)