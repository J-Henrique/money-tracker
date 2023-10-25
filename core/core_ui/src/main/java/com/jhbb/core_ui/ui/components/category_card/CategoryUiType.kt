package com.jhbb.core_ui.ui.components.category_card

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.jhbb.core_ui.R

private val yellowCategory = Color(0xFFFCAC12)
private val violetCategory = Color(0xFF7F3DFF)
private val redCategory = Color(0xFFFD3C4A)
private val greenCategory = Color(0xFF00A86B)
private val blueCategory = Color(0xFF0077FF)

enum class CategoryUiType(
    val description: String,
    @DrawableRes val icon: Int,
    val tint: Color
) {
    BAR(description = "Bares e Restaurantes", icon = R.drawable.ic_bar, tint = blueCategory),
    EDUCATION(description = "Educação", icon = R.drawable.ic_book, tint = greenCategory),
    TRANSPORT(description = "Transporte", icon = R.drawable.ic_car, tint = violetCategory),
    INVESTMENTS(description = "Investimentos", icon = R.drawable.ic_dollar, tint = yellowCategory),
    FOOD(description = "Alimentação", icon = R.drawable.ic_food, tint = greenCategory),
    HEALTH(description = "Saúde", icon = R.drawable.ic_health, tint = redCategory),
    PETS(description = "Pets", icon = R.drawable.ic_pets, tint = yellowCategory),
    SPORTS(description = "Esportes", icon = R.drawable.ic_sports, tint = redCategory),
    TRAVEL(description = "Viagens", icon = R.drawable.ic_travel, tint = violetCategory);
}