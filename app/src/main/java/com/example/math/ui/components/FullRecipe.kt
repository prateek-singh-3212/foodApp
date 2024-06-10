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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import coil.compose.rememberAsyncImagePainter
import com.example.network.data.RecipeData

@Composable
fun FullRecipe(
    onPreviousClick: (Int) -> Unit,
    instructions: String,
    summary: String,
    analyzedInstructions: List<RecipeData.AnalyzedInstruction>,
    nutrition: RecipeData.Nutrition
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
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
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
                text = HtmlCompat.fromHtml(instructions, 0).toString()
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
                val data = analyzedInstructions
                val equipments = mutableListOf<RecipeData.AnalyzedInstruction.Step.Equipment>()
                data.forEach {
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
                text = HtmlCompat.fromHtml(summary, 0).toString()
            )
            ExpandableCard(
                title = "Nutrition",
                content = nutrition.toString()
            )
        }
    }
}