package com.jhbb.core_ui.ui.components.category_card

import com.jhbb.core_domain.model.Category
import com.jhbb.core_domain.model.CategoryType

data class CategoryUiModel(
    val id: Int,
    val type: CategoryUiType,
    val isEnabled: Boolean
)

fun Category.toUiModel(): CategoryUiModel {
    return CategoryUiModel(
        id = id,
        type = CategoryUiType.valueOf(type.name),
        isEnabled = isEnabled
    )
}

fun CategoryUiModel.toDomain(): Category {
    return Category(
        id = id,
        type = CategoryType.valueOf(type.name),
        isEnabled = isEnabled
    )
}