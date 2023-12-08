package com.example.weathermap.domain.location

import android.location.Location

interface LocationTracker {
    suspend fun getLocation(): Location?
}