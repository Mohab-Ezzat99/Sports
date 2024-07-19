package com.app.sports.utils

import android.content.SharedPreferences
import java.util.*
import javax.inject.Inject

class AppManger @Inject constructor(private var sharedPreferences: SharedPreferences) {

    companion object {
        const val TOKEN = "token"
    }
}
