package com.example.math.utils

import android.content.Context
import android.content.SharedPreferences

class FoodSharedPreference(
    context: Context
) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(
            Constants.PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )

    fun setString(key: String, value: String) {
        sharedPreferences.edit()
            .putString(key, value)
            .apply()
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun removeString(key: String): Boolean {
        return sharedPreferences.edit().remove(key).commit()
    }
}