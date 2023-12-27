package com.jhbb.core_data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jhbb.core_domain.model.CategoryType
import com.jhbb.core_domain.model.Register
import java.util.Date

@Entity(tableName = "register")
data class RegisterEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "register_id")
    val id: Int?,

    @ColumnInfo("register_value")
    val value: Double,

    @ColumnInfo("register_title")
    val title: String,

    @ColumnInfo("register_description")
    val description: String,

    @ColumnInfo("register_date")
    val time: Date,

    @ColumnInfo("register_category_type")
    val category: CategoryType,

    @ColumnInfo("register_is_income")
    val isIncome: Boolean,
)

fun RegisterEntity.toDomain() = Register(
    id = id,
    title = title,
    value = value,
    description = description,
    time = time,
    categoryType = category,
    isIncome = isIncome
)

fun Register.toEntity() = RegisterEntity(
    id = id,
    value = value,
    title = title,
    description = description,
    time = time,
    category = categoryType,
    isIncome = isIncome
)