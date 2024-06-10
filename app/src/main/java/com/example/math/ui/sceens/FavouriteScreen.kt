package com.example.math.ui.sceens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.math.ui.components.RecipeCard
import com.example.math.viewmodel.FavoriteViewModel
import com.example.network.data.RecipeData

@Composable
fun FavouriteScreen(
    modifier: Modifier = Modifier,
    favoriteViewModel: FavoriteViewModel,
    onRecipeClick: (Int) -> Unit
) {
    var favRecipeData by remember { mutableStateOf<List<RecipeData>>(emptyList()) }
    favRecipeData = favoriteViewModel.getAllFavouriteRecipes()

    Column(
        modifier = modifier
            .padding(6.dp)
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            text = "Popular Recipes",
            fontWeight = FontWeight.W700
        )
        LazyColumn {
            items(favRecipeData.size) {
                favRecipeData[it].let { data ->
                    RecipeCard(
                        recipeId = data.id,
                        image = rememberAsyncImagePainter(data.image),
                        recipeName = data.title,
                        prepTime =  "Ready in ${data.readyInMinutes} min",
                        onClick = onRecipeClick
                    )
                }
            }
        }
    }
}