package com.example.math.ui.sceens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.math.ui.components.BottomNavigationBar
import com.example.math.ui.nav.Navigations
import com.example.math.ui.nav.bottomBarRoutes
import com.example.math.viewmodel.FavoriteViewModel
import com.example.math.viewmodel.RecipeViewModel
import com.example.math.viewmodel.SearchViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    recipeViewModel: RecipeViewModel,
    searchViewModel: SearchViewModel,
    favoriteViewModel: FavoriteViewModel
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                BottomAppBar(modifier = Modifier) {
                    BottomNavigationBar(navController = navController)
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Navigations(
                recipeViewModel = recipeViewModel,
                searchViewModel = searchViewModel,
                favouriteViewModel = favoriteViewModel,
                modifier = Modifier,
                navController = navController
            )
        }
    }
}