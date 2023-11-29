package com.example.weathermap.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weathermap.R

@Composable
fun Background() {
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "background_img",
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.8f)
            .blur(radiusX = 4.dp, radiusY = 4.dp),
        contentScale = ContentScale.Crop
    )
}