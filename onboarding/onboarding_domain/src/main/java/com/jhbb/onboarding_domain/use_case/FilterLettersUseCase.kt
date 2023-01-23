package com.jhbb.onboarding_domain.use_case

class FilterLettersUseCase {
    operator fun invoke(text: String): String {
        return text.filter { it.isLetter() || it.isWhitespace() }
    }
}