package com.example.motivation

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {
    private val security: SharedPreferences =
        context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)

    fun storeString(key: String, str: String) {
        security.edit().putString(key, str).apply()
    }

    fun getString(key: String) = security.getString(key, "") ?: ""

}