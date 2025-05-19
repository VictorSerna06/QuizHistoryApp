package com.example.quizhistoryapp.util

import android.content.Context

object SharedPreferencesHelper {

    private const val PREF_NAME = "QuizPreference"
    private const val KEY_USER_NAME = "username"

    // Guarda el nombre del usuario
    fun saveUsername(context: Context, name: String){
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_USER_NAME,name).apply()
    }

    // Recupera el nombre del usuario
    fun getUsername(context: Context): String{
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_USER_NAME, "") ?: ""
    }
}