package com.example.math.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.math.R
import com.example.math.ui.theme.SearchText

@Composable
fun IngridentsCard(
    painter: Painter,
    name: String
) {
    Column(
        modifier = Modifier
            .padding(6.dp)
            .width(86.dp)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(86.dp)
                .clip(CircleShape)
                .border(BorderStroke(1.dp, SearchText)),
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = "s"
        )
        Text(
            modifier = Modifier.padding(4.dp),
            fontSize = 12.sp,
            text = name,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }

}

@Preview
@Composable
fun IngridentsCardPreview() {
    IngridentsCard(
        painter = painterResource(id = R.raw.das),
        name = "onidsdadasdasdadon"
    )
}