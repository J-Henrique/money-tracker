package com.jhbb.onboarding_presentation.ui.username_field

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.jhbb.onboarding_domain.use_case.FilterLettersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserNameFieldViewModelImpl @Inject constructor(
    private val filterLettersUseCase: FilterLettersUseCase
): UserNameFieldViewModel, ViewModel() {
    override var username by mutableStateOf("")
        private set

    override fun onEnterText(text: String) {
        username = filterLettersUseCase(text)
    }
}

