package com.example.talabi

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

class UserPreferences(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val LOGGED_IN_KEY = "is_logged_in"
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(LOGGED_IN_KEY, false)
    }

    fun setLoggedIn(loggedIn: Boolean) {
        sharedPreferences.edit().putBoolean(LOGGED_IN_KEY, loggedIn).apply()
    }
}
