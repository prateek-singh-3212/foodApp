package com.example.math.ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(var route: String, val icon: ImageVector, var title: String) {
    data object Home : NavigationItem("Home", Icons.Rounded.Home, "Home")
    data object Favourite : NavigationItem("Favourite", Icons.Rounded.FavoriteBorder, "Favourite")
}
