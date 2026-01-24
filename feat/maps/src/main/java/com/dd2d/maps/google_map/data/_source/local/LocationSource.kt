package com.dd2d.maps.google_map.data._source.local

import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal class LocationSource(context: Context) {
  private val client = LocationServices.getFusedLocationProviderClient(context)

  suspend fun getLastLocation(): Location = suspendCancellableCoroutine { continuation ->
    client.lastLocation
      .addOnFailureListener { exception ->
        continuation.resumeWithException(exception)
      }
      .addOnCanceledListener { continuation.cancel(null) }
      .addOnSuccessListener { location ->
        when(location) {
          null -> continuation.resumeWithException(NoSuchElementException())
          else -> continuation.resume(location)
        }
      }
  }

  suspend fun getCurrentLocation(): Location = suspendCancellableCoroutine { continuation ->
    val cancellationTokenSource = CancellationTokenSource()
    val priority = Priority.PRIORITY_HIGH_ACCURACY

    continuation.invokeOnCancellation {
      cancellationTokenSource.cancel()
    }

    client.getCurrentLocation(priority, cancellationTokenSource.token)
      .addOnFailureListener { exception ->
        continuation.resumeWithException(exception)
      }
      .addOnCanceledListener { continuation.cancel(null) }
      .addOnSuccessListener { location ->
        when(location) {
          null -> continuation.resumeWithException(NoSuchElementException())
          else -> continuation.resume(location)
        }
      }
  }
}