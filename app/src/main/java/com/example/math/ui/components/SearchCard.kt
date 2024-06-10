package com.example.math.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.math.R

@Composable
fun SearchCard(
    id: Int,
    painter: Painter,
    title: String,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable { onClick.invoke(id) }
    ) {
        Image(
            modifier = Modifier
                .size(35.dp)
                .clip(RoundedCornerShape(4.dp)),
            painter = painter,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier
                .padding(start = 10.dp, end = 6.dp, top = 6.dp, bottom = 6.dp),
            text = title
        )
    }
}

@Preview
@Composable
fun SearchCardPreview() {
    SearchCard(
        id = 1,
        painter = painterResource(id = R.raw.das),
        title = "tsasds",
    ) {

    }
}