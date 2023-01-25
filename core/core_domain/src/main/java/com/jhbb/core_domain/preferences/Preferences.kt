package com.jhbb.core_domain.preferences

interface Preferences {
    fun putUserName(value: String)
    fun getUserName(): String?
}