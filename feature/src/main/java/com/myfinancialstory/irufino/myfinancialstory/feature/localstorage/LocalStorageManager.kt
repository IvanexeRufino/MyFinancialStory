package com.myfinancialstory.irufino.myfinancialstory.feature.localstorage

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class LocalStorageManager(activity: Activity) {

    private val SESSION_KEY: String = "SESSION_ID"

    private var preferences: SharedPreferences = activity.getSharedPreferences("preferences", Context.MODE_PRIVATE)

    fun getSessionId(): String {
        var string = preferences.getString(SESSION_KEY, "")
        if (string == null) {
            string = ""
        }

        return string
    }

    fun setSessionId(session: String) {
        preferences.edit().putString(SESSION_KEY, session).apply()
    }
}