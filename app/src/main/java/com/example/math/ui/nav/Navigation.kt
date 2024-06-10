package com.example.math.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.math.ui.sceens.SearchScreen
import com.example.math.ui.sceens.FavouriteScreen
import com.example.math.ui.sceens.HomeScreen
import com.example.math.ui.sceens.RecipeDetailScreen
import com.example.math.viewmodel.FavoriteViewModel
import com.example.math.viewmodel.RecipeViewModel
import com.example.math.viewmodel.SearchViewModel

val bottomBarRoutes = setOf(
    NavigationItem.Home.route,
    NavigationItem.Favourite.route
)

@Composable
fun Navigations(
    searchViewModel: SearchViewModel,
    recipeViewModel: RecipeViewModel,
    favouriteViewModel: FavoriteViewModel,
    modifier: Modifier, navController: NavHostController
) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen(
                recipeViewModel = recipeViewModel,
                modifier = modifier,
                onSearchClick = {
                    navController.navigate("search")
                },
                onRecipeClick = {
                    navController.navigate("recipe_detail/$it")
                }
            )
        }
        composable(NavigationItem.Favourite.route) {
            FavouriteScreen(
                modifier = modifier,
                favoriteViewModel = favouriteViewModel,
                onRecipeClick = {
                    navController.navigate("recipe_detail/$it")
                }
            )
        }
        composable(
            route = "recipe_detail/{id}",
            arguments = listOf(navArgument("id") {type = NavType.IntType})
        ) {
            val argument = it.arguments?.getInt("id") ?: 1
            RecipeDetailScreen(
                modifier = modifier,
                recipeViewModel = recipeViewModel,
                favoriteViewModel = favouriteViewModel,
                id = argument
            )
        }
        composable("search") {
            SearchScreen(
                onBackPress = { navController.popBackStack() },
                searchViewModel = searchViewModel,
                favoriteViewModel = favouriteViewModel,
                recipeViewModel = recipeViewModel,
                modifier = modifier
            ) {
                navController.navigate("recipe_detail/$it")
            }
        }
    }
}