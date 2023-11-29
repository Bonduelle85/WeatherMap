package com.example.weathermap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import com.example.weathermap.presentation.Background
import com.example.weathermap.presentation.MainCard
import com.example.weathermap.presentation.TabLayout
import com.example.weathermap.presentation.ui.theme.WeatherMapTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherMapTheme {
                Background()
                Column() {
                    MainCard(context = this@MainActivity)
                    TabLayout()
                }
            }
        }
    }
}

