package com.example.math.utils

import android.content.res.Resources
import androidx.compose.ui.unit.Dp
import com.example.network.data.ErrorModel
import com.google.gson.Gson
import okhttp3.ResponseBody

object Constants {
    const val PREFERENCE_NAME = "food"
    const val FAVOURITE_RECIPE = "favourite_recipe"
    const val TOKEN = "token"

    fun convertToErrorModel(errorBody: ResponseBody): ErrorModel {
        val gson = Gson()
        return gson.fromJson(
            errorBody.source().readUtf8(),
            ErrorModel::class.java
        )
    }
}