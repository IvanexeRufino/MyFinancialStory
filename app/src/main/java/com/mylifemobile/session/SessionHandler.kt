package com.mylifemobile.session

import android.app.Activity
import com.mylifemobile.localstorage.LocalStorageManager
import java.util.*

class SessionHandler(activity: Activity) {

    private val localStorage: LocalStorageManager = LocalStorageManager(activity)

    fun isUserLogged(): Boolean {
        return localStorage.getSessionId() != ""
    }

    fun createSession() {
        return localStorage.setSessionId(UUID.randomUUID().toString())
    }

    fun isValidSession(session: String): Boolean {
        return localStorage.getSessionId() == session
    }

}