package com.example.math.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.math.R
import com.example.math.ui.theme.SearchContainer
import com.example.math.ui.theme.SearchText

@Composable
fun RecipeCard(
    recipeId: Int,
    image: Painter,
    recipeName: String,
    prepTime: String,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(6.dp).
            clickable { onClick(recipeId) },
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, SearchContainer)
    ) {
        Box(
            modifier = Modifier.fillMaxSize().background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.28f)
                )
                Column(
                    modifier = Modifier.fillMaxSize().padding(12.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = recipeName,
                        color = Color.Black,
                        maxLines = 1,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = prepTime,
                        color = SearchText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun AdRecipeCard() {
    Card(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(6.dp),
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, SearchContainer)
    ) {
        Box(
            modifier = Modifier.fillMaxSize().background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.raw.das),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.28f)
                )
                Column(
                    modifier = Modifier.fillMaxSize().padding(12.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Ad",
                        color = Color.Black,
                        maxLines = 1,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "this is a ad",
                        color = SearchText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RecipeCardPreview() {
    RecipeCard(
        recipeId = 1,
        image = painterResource(id = R.raw.das),
        recipeName = "SSdasda",
        prepTime = "dsds"
    ) {}
    AdRecipeCard()
}
