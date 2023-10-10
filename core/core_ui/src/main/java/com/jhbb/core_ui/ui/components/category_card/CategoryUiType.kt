package com.jhbb.core_ui.ui.components.category_card

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.jhbb.core_ui.R

enum class CategoryUiType(
    val description: String,
    @DrawableRes val icon: Int,
    val tint: Color
) {
    BAR(description = "Bares e Restaurantes", icon = R.drawable.ic_bar, tint = Color.Red),
    EDUCATION(description = "Educação", icon = R.drawable.ic_book, tint = Color.Blue),
    TRANSPORT(description = "Transporte", icon = R.drawable.ic_car, tint = Color.Cyan),
    INVESTMENTS(description = "Investimentos", icon = R.drawable.ic_dollar, tint = Color.Magenta),
    FOOD(description = "Alimentação", icon = R.drawable.ic_food, tint = Color.Yellow),
    HEALTH(description = "Saúde", icon = R.drawable.ic_health, tint = Color.Blue),
    PETS(description = "Pets", icon = R.drawable.ic_pets, tint = Color.Red),
    SPORTS(description = "Esportes", icon = R.drawable.ic_sports, tint = Color.Green),
    TRAVEL(description = "Viagens", icon = R.drawable.ic_travel, tint = Color.Yellow);
}