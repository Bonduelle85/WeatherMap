package com.example.weathermap.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weathermap.presentation.ui.theme.DarkGray
import com.example.weathermap.presentation.ui.theme.LightBlue

@Composable
fun HourItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        colors = CardDefaults.cardColors(LightBlue),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = "12:00",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkGray
                    )
                )
                Text(
                    text = "Sunny",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkGray
                    )
                )
            }
            Text(
                text = "27°С",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkGray
                )
            )
            AsyncImage(
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 10.dp),
                model = "https://cdn.weatherapi.com/weather/64x64/day/116.png",
                contentDescription = "icon"
            )
        }
    }
}