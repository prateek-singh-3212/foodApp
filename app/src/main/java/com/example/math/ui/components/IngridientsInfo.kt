package com.example.math.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.network.data.RecipeData

@Composable
fun IngridientsInfo(
    onPreviousClick: (Int) -> Unit,
    ingridentsData: List<RecipeData.ExtendedIngredient>
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(3)
        ) {
            items(ingridentsData.size) {
                ingridentsData[it].let { data ->
                    IngridentsCard(
                        painter = rememberAsyncImagePainter(data.image),
                        name = data.name
                    )
                }
            }
        }
    }
}