package com.example.math

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.math.ui.components.BottomNavigationBar
import com.example.math.ui.nav.Navigations
import com.example.math.ui.sceens.MainScreen
import com.example.math.ui.sceens.RecipeDetailScreen
import com.example.math.ui.theme.MathTheme
import com.example.math.viewmodel.FavoriteViewModel
import com.example.math.viewmodel.RecipeViewModel

import com.example.math.viewmodel.SearchViewModel

class MainActivity : ComponentActivity() {
    private val searchViewModel: SearchViewModel by viewModels { SearchViewModel.Factory }
    private val recipeViewModel: RecipeViewModel by viewModels { RecipeViewModel.Factory }
    private val favoriteViewModel: FavoriteViewModel by viewModels { FavoriteViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        recipeViewModel.fetchPopularRecipe(2)
        setContent {
            val navController = rememberNavController()
            MathTheme {
                MainScreen(
                    navController = navController,
                    searchViewModel = searchViewModel,
                    recipeViewModel = recipeViewModel,
                    favoriteViewModel = favoriteViewModel
                )
            }
        }
    }
}