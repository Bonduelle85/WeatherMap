package com.example.weathermap.presentation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weathermap.R
import com.example.weathermap.presentation.ui.theme.DarkGray
import com.example.weathermap.presentation.ui.theme.LightBlue

@Composable
fun MainCard(context: Context) {
    var dateTime by rememberSaveable {
        mutableStateOf("13 Dec 2023 / 10:30")
    }
    var city by rememberSaveable {
        mutableStateOf("Taskent")
    }
    var currentTemp by rememberSaveable {
        mutableStateOf(16)
    }
    var minTemp by rememberSaveable {
        mutableStateOf(9)
    }
    var maxTemp by rememberSaveable {
        mutableIntStateOf(27)
    }
    var weatherState by rememberSaveable {
        mutableStateOf("Cloudy")
    }
    Column(
        modifier = Modifier
            .padding(5.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            colors = CardDefaults.cardColors(LightBlue),
            shape = RoundedCornerShape(5.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = 10.dp, start = 10.dp),
                        text = dateTime,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkGray
                        )
                    )
                    AsyncImage(
                        modifier = Modifier
                            .size(55.dp)
                            .padding(top = 10.dp, end = 10.dp),
                        model = "https://cdn.weatherapi.com/weather/64x64/day/116.png",
                        contentDescription = "icon"
                    )

                }
                Text(
                    text = city,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = DarkGray,
                        fontSize = 30.sp,

                        )
                )
                Text(
                    text = "$currentTemp°С",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = DarkGray,
                        fontSize = 60.sp,

                        )
                )
                Text(
                    text = weatherState,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = DarkGray,
                        fontSize = 20.sp,

                        )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = {
                            Toast.makeText(context, "Searching...", Toast.LENGTH_SHORT).show()
                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.baseline_search_24),
                            contentDescription = "glass",
                            tint = DarkGray
                        )
                    }

                    Text(
                        text = "$maxTemp°С/$minTemp°С",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkGray
                        )
                    )

                    IconButton(
                        onClick = {
                            Toast.makeText(
                                context,
                                "Synchronization...",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = R.drawable.baseline_refresh_24),
                            contentDescription = "glass",
                            tint = DarkGray
                        )
                    }
                }
            }
        }
    }
}