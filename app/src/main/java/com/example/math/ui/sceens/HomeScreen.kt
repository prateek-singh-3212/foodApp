package com.example.math.ui.sceens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.math.ui.components.AdRecipeCard
import com.example.math.ui.components.LoadingScreen
import com.example.math.ui.components.PopularRecipeCard
import com.example.math.ui.components.RecipeCard
import com.example.math.ui.theme.SearchContainer
import com.example.math.ui.theme.SearchText
import com.example.math.viewmodel.RecipeViewModel
import com.example.math.viewmodel.SearchViewModel
import com.example.network.data.RecipeData

@Composable
fun HomeScreen(
    recipeViewModel: RecipeViewModel,
    modifier: Modifier = Modifier,
    onRecipeClick: (Int) -> Unit,
    onSearchClick: () -> Unit
) {
    val popularRecipeData by recipeViewModel.popularRecipeData.observeAsState(emptyList())
    val allRecipeData by recipeViewModel.allRecipeData.observeAsState(emptyList())
    val isLoading by recipeViewModel.isPopularDataLoading.observeAsState(false)
    val errorState by recipeViewModel.popularRecipeError.observeAsState()
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    if (errorState != null) {
        Toast.makeText(context, errorState, Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = modifier
            .padding(6.dp)
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        if (isLoading) {
            LoadingScreen()
        } else {
            Text(
                text = "\uD83D\uDC4B Hey <user first name>",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Discover tasty and healthy receipt",
                fontSize = 12.sp,
                color = SearchText
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp)
                    .clickable { onSearchClick.invoke() },
                value = "",
                enabled = false,
                onValueChange = {},
                label = {
                    Text(text = "Search any recipe")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "S")
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
            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                text = "Popular Recipes",
                fontWeight = FontWeight.W700
            )
            LazyRow(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            ) {
                items(popularRecipeData.size) {
                    popularRecipeData[it].let {data ->
                        PopularRecipeCard(
                            recipeId = data.id,
                            image = rememberAsyncImagePainter(data.image),
                            recipeName = data.title,
                            prepTime =  "Ready in ${data.readyInMinutes} min",
                            onClick = onRecipeClick
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                text = "All Recipes",
                fontWeight = FontWeight.W700
            )
            allRecipeData.forEachIndexed { index, recipeData ->
                if (index%5 == 0) {
                    AdRecipeCard()
                }
                RecipeCard(
                    recipeId = recipeData.id,
                    image = rememberAsyncImagePainter(recipeData.image),
                    recipeName = recipeData.title,
                    prepTime =  "Ready in ${recipeData.readyInMinutes} min",
                    onClick = onRecipeClick
                )
            }
        }
    }
}