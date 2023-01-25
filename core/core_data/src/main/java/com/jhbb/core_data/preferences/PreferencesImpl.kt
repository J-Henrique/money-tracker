package com.jhbb.core_data.preferences

import android.content.SharedPreferences
import com.jhbb.core_domain.preferences.Preferences
import javax.inject.Inject

class PreferencesImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): Preferences {

    override fun putUserName(value: String) {
        sharedPreferences.edit()
            .putString(USER_NAME_KEY, value)
            .apply()
    }

    override fun getUserName(): String? {
        return sharedPreferences.getString(USER_NAME_KEY, "")
    }

    private companion object {
        const val USER_NAME_KEY = "user_name_key"
    }
}