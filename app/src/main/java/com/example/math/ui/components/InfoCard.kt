package com.example.math.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.math.ui.theme.Orange
import com.example.math.ui.theme.SearchText

@Composable
fun InfoCard(
    modifier: Modifier,
    info: String,
    data: String,
) {
    Card(
        modifier = modifier
            .padding(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp, SearchText),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 6.dp, top = 6.dp),
            text = info,
            textAlign = TextAlign.Center,
            color = SearchText,
            fontSize = 12.sp
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 6.dp, top = 2.dp, bottom = 6.dp),
            text = data,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Orange
        )
    }
}

@Preview
@Composable
fun InfoCardPreview() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        InfoCard(
            modifier = Modifier.weight(1f),
            info = "Ready In",
            data = "2 mins"
        )
        InfoCard(
            modifier = Modifier.weight(1f),
            info = "Serving",
            data = "222"
        )
        InfoCard(
            modifier = Modifier.weight(1f),
            info = "Price/Servings",
            data = "2222"
        )
    }
}