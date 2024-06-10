package com.example.math.ui.sceens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.lifecycle.LifecycleOwner
import coil.compose.rememberAsyncImagePainter
import com.example.math.ui.components.ExpandableCard
import com.example.math.ui.components.InfoCard
import com.example.math.ui.components.IngridentsCard
import com.example.math.ui.components.LoadingScreen
import com.example.math.viewmodel.FavoriteViewModel
import com.example.math.viewmodel.RecipeViewModel
import com.example.network.data.RecipeData

@Composable
fun RecipeDetailScreen(
    modifier: Modifier,
    recipeViewModel: RecipeViewModel,
    favoriteViewModel: FavoriteViewModel,
    id: Int
) {
    var recipeData by remember { mutableStateOf<RecipeData?>(null) }
    var favouriteIconState by remember { mutableStateOf<ImageVector>(Icons.Default.FavoriteBorder) }
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    if (favoriteViewModel.isFavouriteRecipe(id)) {
        recipeData = favoriteViewModel.getRecipeData(id)
    }else {
        recipeViewModel.fetchRecipe(id)
    }

    recipeViewModel.recipeData.observe(LocalLifecycleOwner.current) {
        if (it.id == id) {
            recipeData = it
        }
    }

    recipeViewModel.recipeError.observe(LocalLifecycleOwner.current) {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }

    recipeData?.let { data ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Box(modifier = Modifier.height(360.dp)) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = rememberAsyncImagePainter(data.image),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                )
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd),
                    onClick = {
                        if (favoriteViewModel.isFavouriteRecipe(data.id)) {
                            // Remove from favourite
                            favoriteViewModel.removeFavourite(data)
                            favouriteIconState = Icons.Default.FavoriteBorder
                        }else {
                            favoriteViewModel.addToFavourites(data)
                            favouriteIconState = Icons.Default.Favorite
                        }
                    }) {
                    if (favoriteViewModel.isFavouriteRecipe(data.id)) {
                        favouriteIconState = Icons.Default.Favorite
                    }
                    Icon(imageVector = favouriteIconState, contentDescription = ""
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                InfoCard(
                    modifier = Modifier.weight(1f),
                    info = "Ready In",
                    data = "${data.readyInMinutes} mins"
                )
                InfoCard(
                    modifier = Modifier.weight(1f),
                    info = "Serving",
                    data = "${data.servings}"
                )
                InfoCard(
                    modifier = Modifier.weight(1f),
                    info = "Price/Servings",
                    data = "${data.pricePerServing}"
                )
            }
            Text(
                modifier = Modifier
                    .padding(6.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                text = "Ingredients"
            )
            LazyRow(
                modifier = Modifier.padding(6.dp)
            ) {
                items(data.extendedIngredients?.size ?: 0) {
                    data.extendedIngredients[it].let { data ->
                        IngridentsCard(
                            painter = rememberAsyncImagePainter(data.image),
                            name = data.name
                        )
                    }
                }
            }
            Text(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 6.dp, start = 6.dp, end = 6.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                text = "Instructions"
            )
            Text(
                modifier = Modifier
                    .padding(6.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                text = HtmlCompat.fromHtml(data.instructions ?: "", 0).toString()
            )
            Text(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 6.dp, start = 6.dp, end = 6.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                text = "Equipments"
            )
            LazyRow(
                modifier = Modifier.padding(6.dp)
            ) {
                val temp = data.analyzedInstructions
                val equipments = mutableListOf<RecipeData.AnalyzedInstruction.Step.Equipment>()
                temp.forEach {
                    it.steps.forEach { steps ->
                        equipments.addAll(steps.equipment)
                    }
                }

                items(equipments.size) {
                    equipments[it].let { data ->
                        IngridentsCard(
                            painter = rememberAsyncImagePainter(data.image),
                            name = data.name
                        )
                    }
                }
            }
            Text(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 6.dp, start = 6.dp, end = 6.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                text = "Quick Summary"
            )
            Text(
                modifier = Modifier
                    .padding(6.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                text = HtmlCompat.fromHtml(data.summary, 0).toString()
            )
            ExpandableCard(
                title = "Nutrition",
                content = data.nutrition.toString()
            )
        }
    } ?: LoadingScreen()
}