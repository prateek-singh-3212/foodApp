package com.example.math.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import coil.compose.rememberAsyncImagePainter
import com.example.network.data.RecipeData
import com.example.network.data.SimilarRecipeData

@Composable
fun SimilarRecipe(
    onPreviousClick: (Int) -> Unit,
    similarRecipe: List<SimilarRecipeData>,
    onRecipeClick: (Int) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(6.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                text = "Ingredients"
            )
            IconButton(onClick = {
                onPreviousClick(0)
            }) {
                Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(6.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                text = "Full Recipe"
            )
            IconButton(onClick = {
                onPreviousClick(1)
            }) {
                Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(6.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                text = "Similar Recipe"
            )
            IconButton(onClick = {
                onPreviousClick(2)
            }) {
                Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "")
            }
        }

        LazyColumn(
            modifier = Modifier.padding(10.dp)
        ){
            items(similarRecipe.size) {
                similarRecipe[it].let {data ->
                    RecipeCard(
                        recipeId = data.id,
                        image = rememberAsyncImagePainter(data.sourceUrl),
                        recipeName = data.title,
                        prepTime =  "Ready in ${data.readyInMinutes} min",
                        onClick = onRecipeClick
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun SimilarRecipePreview() {
//    SimilarRecipe()
}