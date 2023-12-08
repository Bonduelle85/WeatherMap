package com.example.weathermap.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.example.weathermap.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class LocationTrackerImpl @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
) : LocationTracker {
    override suspend fun getLocation(): Location? {

        // checking permissions: ACCESS_COARSE_LOCATION and ACCESS_FINE_LOCATION
        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED


        // getting a reference to the Location Manager from the Android system
        val locationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        // checking if the GPS is enabled
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        if (!hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission || !isGpsEnabled) return null

        // getting the location in suspend function using
        // 'suspendCancellableCoroutine', 'continuation' and listeners
        return suspendCancellableCoroutine { continuation ->
            val lastLocationTask = locationClient.lastLocation
            if (lastLocationTask.isComplete) {
                if (lastLocationTask.isSuccessful) {
                    continuation.resume(lastLocationTask.result)
                } else {
                    continuation.resume(null)
                    return@suspendCancellableCoroutine
                }

                lastLocationTask.addOnSuccessListener { location ->
                    continuation.resume(location)
                }
                lastLocationTask.addOnFailureListener {
                    continuation.resume(null)
                }
                lastLocationTask.addOnCanceledListener {
                    continuation.cancel()
                }
            }
        }
    }
}