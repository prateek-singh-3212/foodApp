package com.example.math.ui.sceens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.math.ui.components.BasicInfo
import com.example.math.ui.components.FullRecipe
import com.example.math.ui.components.IngridientsInfo
import com.example.math.ui.components.SearchCard
import com.example.math.ui.components.SimilarRecipe
import com.example.math.ui.theme.Orange
import com.example.math.ui.theme.SearchContainer
import com.example.math.ui.theme.SearchText
import com.example.math.viewmodel.FavoriteViewModel
import com.example.math.viewmodel.RecipeViewModel
import com.example.math.viewmodel.SearchViewModel
import com.example.network.data.RecipeData
import com.example.network.data.SearchData
import com.example.network.data.SimilarRecipeData
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier,
    onBackPress: () -> Unit,
    searchViewModel: SearchViewModel,
    favoriteViewModel: FavoriteViewModel,
    recipeViewModel: RecipeViewModel,
    onRecipeClick: (Int) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetScaffoldState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    var searchTextState by remember { mutableStateOf("") }
    var selectedResultState by remember { mutableStateOf<RecipeData?>(null) }
    var recipeDetailState by remember { mutableIntStateOf(0) }
    var searchData by remember { mutableStateOf<List<SearchData.Result>>(emptyList()) }
    var favouriteIconState by remember { mutableStateOf<ImageVector>(Icons.Default.FavoriteBorder) }
    var similarRecipeData by remember { mutableStateOf<List<SimilarRecipeData>>(emptyList()) }

    searchViewModel.searchData.observe(lifecycleOwner) {
        searchData = it.results
    }

    searchViewModel.searchErrorState.observe(lifecycleOwner) {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }

    recipeViewModel.recipeData.observe(lifecycleOwner) {recipe ->
        if (searchData.any{ it.id == recipe.id }) {
            selectedResultState = recipe
            showBottomSheet = true
        }
    }

    recipeViewModel.recipeError.observe(lifecycleOwner) {
        Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
    }

    recipeViewModel.similarRecipeData.observe(lifecycleOwner) {
        similarRecipeData = it
    }

    Column(
        modifier = modifier
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 12.dp),
            value = searchTextState,
            onValueChange = {
                searchTextState = it
                if (searchTextState.length > 2) {
                    searchViewModel.searchRecipe(it)
                }
            },
            label = {
                Text(text = "Search any recipe")
            },
            leadingIcon = {
                IconButton(onClick = { onBackPress.invoke() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "S")
                }
            },
            trailingIcon = {
                if (searchTextState.isNotEmpty()) {
                    IconButton(onClick = { searchTextState = "" }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "S")
                    }
                }
            },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = SearchContainer,
                focusedContainerColor = SearchContainer,
                unfocusedTextColor = SearchText,
                focusedTextColor = SearchText,
                focusedLeadingIconColor = SearchText,
                unfocusedLeadingIconColor = SearchText
            )
        )
        LazyColumn {
            items(searchData.size) {
                searchData[it].let {data ->
                    SearchCard(
                        id = data.id,
                        title = data.title,
                        painter = rememberAsyncImagePainter(data.image),
                        onClick = {
                            // Open bottom sheet.
                            // Load data...
                            recipeViewModel.fetchRecipe(it)
                            recipeViewModel.fetchSimilarRecipes(it)
                        }
                    )
                }
            }
        }
    }

    if(showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                coroutineScope.launch {
                    bottomSheetScaffoldState.hide()
                }.invokeOnCompletion {
                    if (!bottomSheetScaffoldState.isVisible) {
                        showBottomSheet = false
                        recipeDetailState = 0
                        selectedResultState = null
                    }
                }
            },
            sheetState = bottomSheetScaffoldState
        ) {
            // Sheet content

            selectedResultState?.let {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(bottom = 50.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = {
                                coroutineScope.launch {
                                    bottomSheetScaffoldState.hide()
                                }.invokeOnCompletion {
                                    if (!bottomSheetScaffoldState.isVisible) {
                                        showBottomSheet = false
                                        recipeDetailState = 0
                                        selectedResultState = null
                                    }
                                }
                            }) {
                                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
                            }
                            Text(text = it.title)
                            IconButton(onClick = {
                                if (favoriteViewModel.isFavouriteRecipe(it.id)) {
                                    // Remove from favourite
                                    favoriteViewModel.removeFavourite(it)
                                    favouriteIconState = Icons.Default.FavoriteBorder
                                }else {
                                    favoriteViewModel.addToFavourites(it)
                                    favouriteIconState = Icons.Default.Favorite
                                }
                            }) {
                                if (favoriteViewModel.isFavouriteRecipe(it.id)) {
                                    favouriteIconState = Icons.Default.Favorite
                                }
                                Icon(imageVector = favouriteIconState, contentDescription = "")
                            }
                        }
                        when(recipeDetailState) {
                            0 -> BasicInfo(
                                painter = rememberAsyncImagePainter(it.image),
                                readyInMinutes = it.readyInMinutes,
                                servings = it.servings,
                                pricePerServing = it.pricePerServing.toInt()
                            )
                            1 -> IngridientsInfo(
                                onPreviousClick = {
                                    recipeDetailState = it
                                },
                                ingridentsData = it.extendedIngredients
                            )
                            2 -> FullRecipe(
                                onPreviousClick = {
                                    recipeDetailState = it
                                },
                                instructions = it.instructions,
                                summary = it.summary,
                                analyzedInstructions = it.analyzedInstructions,
                                nutrition = it.nutrition
                            )
                            3 -> SimilarRecipe(
                                onPreviousClick = {
                                    recipeDetailState = it
                                },
                                similarRecipe = similarRecipeData,
                                onRecipeClick = {}
                            )
                        }
                    }

                    recipeDetailState.let {
                        if (it >= 3) {
                            return@let
                        }

                        Button(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .padding(6.dp)
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Orange
                            ),
                            onClick = {
                                recipeDetailState += 1
                            }) {
                                Text(
                                    text = when(it) {
                                        0 -> "Get ingredients"
                                        1 -> "Get full recipe "
                                        2 -> "Get similar recipe"
                                        else -> "Get similar recipe"
                                    }
                                )
                            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "")
                        }
                    }
                }
            }
        }
    }
}