package com.beok.common.util

import android.content.SharedPreferences
import javax.inject.Inject

class Prefs @Inject constructor(
    private val prefs: SharedPreferences
) {

    var userId
        get() = prefs.getInt(USER_ID, -1)
        set(value) = prefs.edit().putInt(USER_ID, value).apply()

    companion object {
        private const val USER_ID = "user_id"
    }
}
