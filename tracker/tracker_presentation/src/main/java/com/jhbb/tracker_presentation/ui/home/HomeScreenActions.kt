package com.jhbb.tracker_presentation.ui.home

import com.jhbb.core_domain.model.Register

data class HomeScreenActions(
    val onRegister: () -> Unit,
    val onRefresh: (Register) -> Unit
)
