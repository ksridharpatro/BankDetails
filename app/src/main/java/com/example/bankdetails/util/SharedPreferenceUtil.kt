package com.example.bankdetails.util

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.bankdetails.models.User
import com.google.gson.Gson

//Shared pref constants for cred file.
const val PREF_FILE_NAME_USER_DETAILS = "file_user_details"
const val KEY_USER_DETAILS = "user_details"

class SharedPreferenceUtil private constructor(private val application: Application) {

    private val gson: Gson = Gson()

    fun saveUser(user: User) {
        val preferences: SharedPreferences =
            application.getSharedPreferences(PREF_FILE_NAME_USER_DETAILS, Context.MODE_PRIVATE)
        preferences.edit().putString(KEY_USER_DETAILS, gson.toJson(user)).apply()
    }

    fun getUser(): User? {
        val user: User?
        val preferences: SharedPreferences =
            application.getSharedPreferences(PREF_FILE_NAME_USER_DETAILS, Context.MODE_PRIVATE)
        val userJson = preferences.getString(KEY_USER_DETAILS, "")
        user = gson.fromJson(userJson, User::class.java)
        return user
    }

    fun clearUser() {
        val preferences: SharedPreferences =
            application.getSharedPreferences(PREF_FILE_NAME_USER_DETAILS, Context.MODE_PRIVATE)
        preferences.edit().clear().apply()
    }

    companion object {
        private var instance: SharedPreferenceUtil? = null
        private val LOCK = Any()

        operator fun invoke(application: Application) = instance ?: synchronized(LOCK) {
            instance ?: createSharedPrefUtil(application).also {
                instance = it
            }
        }

        private fun createSharedPrefUtil(application: Application) =
            SharedPreferenceUtil(application)
    }
}