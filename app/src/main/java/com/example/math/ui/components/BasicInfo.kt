package com.example.math.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.math.R

@Composable
fun BasicInfo(
    painter: Painter,
    readyInMinutes: Int,
    servings: Int,
    pricePerServing: Int,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            modifier = Modifier.height(360.dp),
            painter = painter,
            contentDescription = "",
            contentScale = ContentScale.Crop,
        )
        Row(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            InfoCard(
                modifier = Modifier.weight(1f),
                info = "Ready In",
                data = "${readyInMinutes} mins"
            )
            InfoCard(
                modifier = Modifier.weight(1f),
                info = "Serving",
                data = "${servings}"
            )
            InfoCard(
                modifier = Modifier.weight(1f),
                info = "Price/Servings",
                data = "${pricePerServing}"
            )
        }
    }
}

@Preview
@Composable
fun BasicInfoPreview() {
    BasicInfo(
        painter = painterResource(id = R.raw.das),
        1,
        2,
        3
    )
}