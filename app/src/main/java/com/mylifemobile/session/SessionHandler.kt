package com.mylifemobile.session

import android.app.Activity
import com.mylifemobile.localstorage.LocalStorageManager
import java.util.*

class SessionHandler(activity: Activity) {

    private val localStorage: LocalStorageManager = LocalStorageManager(activity)

    fun isUserLogged(): Boolean {
        return localStorage.getSessionId() != ""
    }

    fun createSession(userId: Int) {
        return localStorage.setSessionId(UUID.randomUUID().toString(), userId)
    }

    fun getUserId(): Int {
        return localStorage.getUserId()
    }
}