package com.mylifemobile.localstorage

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class LocalStorageManager(activity: Activity) {

    private val sessionKey: String = "SESSION_ID"
    private val userKey: String = "USER_ID"

    private var preferences: SharedPreferences = activity.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    fun getSessionId(): String {
        var string = preferences.getString(sessionKey, "")
        if (string == null) {
            string = ""
        }

        return string
    }

    fun getUserId(): Int {
        return preferences.getInt(userKey, 0)
    }

    fun setSessionId(session: String, userId: Int) {
        preferences.edit().putString(sessionKey, session).apply()
        preferences.edit().putInt(userKey, userId).apply()
    }
}