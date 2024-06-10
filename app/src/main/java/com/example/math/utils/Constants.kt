package com.example.math.utils

import android.content.res.Resources
import androidx.compose.ui.unit.Dp

object Constants {
    const val PREFERENCE_NAME = "food"
    const val FAVOURITE_RECIPE = "favourite_recipe"
    const val TOKEN = "token"
}

inline val Double.dw: Dp
    get() = Resources.getSystem().displayMetrics.let {
        Dp(value = ((this * it.widthPixels) / it.density).toFloat())
    }

inline val Int.dw: Dp get() = this.toDouble().dw
inline val Float.dw: Dp get() = this.toDouble().dw

/**
 * This extension method converts a double value set as percentage of device height to Dp object
 * @example (0.1.dh) 10% of the device height
 */
inline val Double.dh: Dp
    get() = Resources.getSystem().displayMetrics.let {
        Dp(value = ((this * it.heightPixels) / it.density).toFloat())
    }
inline val Int.dh: Dp get() = this.toDouble().dh
inline val Float.dh: Dp get() = this.toDouble().dh