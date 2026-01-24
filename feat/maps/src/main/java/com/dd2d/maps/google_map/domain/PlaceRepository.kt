package com.dd2d.maps.google_map.domain

import com.dd2d.maps.google_map.domain.model.Place
import com.dd2d.maps.google_map.domain.model.PlaceLocation

interface PlaceRepository {
  suspend fun getCurrentLocation(): PlaceLocation
  suspend fun getPlace(id: String): Place
}