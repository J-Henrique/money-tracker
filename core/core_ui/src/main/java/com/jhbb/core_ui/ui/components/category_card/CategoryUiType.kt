package com.jhbb.core_ui.ui.components.category_card

import androidx.annotation.DrawableRes
import com.jhbb.core_ui.R

enum class CategoryUiType(val description: String, @DrawableRes val icon: Int) {
    BAR(description = "Bares e Restaurantes", icon = R.drawable.ic_bar),
    EDUCATION(description = "Educação", icon = R.drawable.ic_book),
    TRANSPORT(description = "Transporte", icon = R.drawable.ic_car),
    INVESTMENTS(description = "Investimentos", icon = R.drawable.ic_dollar),
    FOOD(description = "Alimentação", icon = R.drawable.ic_food),
    HEALTH(description = "Saúde", icon = R.drawable.ic_health),
    PETS(description = "Pets", icon = R.drawable.ic_pets),
    SPORTS(description = "Esportes", icon = R.drawable.ic_sports),
    TRAVEL(description = "Viagens", icon = R.drawable.ic_travel);
}