package com.jhbb.core_data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jhbb.core_domain.model.Category
import com.jhbb.core_domain.model.CategoryType

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

fun CategoryEntity.toDomain() = Category(
    id = id,
    type = CategoryType.valueOf(name),
    isEnabled = isEnabled
)

fun Category.toEntity() = CategoryEntity(
    id = id,
    name = type.name,
    isEnabled = isEnabled
)