package com.dd2d.maps.google_map.data

import com.dd2d.maps.google_map.data._source.local.LocationSource
import com.dd2d.maps.google_map.data._source.remote.GooglePlaceApi
import com.dd2d.maps.google_map.data._source.remote.dto.request.GooglePlaceSearchByTextRequestDto
import com.dd2d.maps.google_map.data._source.remote.dto.request.PlaceSearchLocationBias
import com.dd2d.maps.google_map.data._source.remote.dto.request.PlaceSearchLocationBiasCircle
import com.dd2d.maps.google_map.data._source.remote.dto.request.PlaceSearchLocationBiasCircleCenter
import com.dd2d.maps.google_map.domain.PlaceRepository
import com.dd2d.maps.google_map.domain.model.Place
import com.dd2d.maps.google_map.domain.model.PlaceImage
import com.dd2d.maps.google_map.domain.model.PlaceLocation
import com.dd2d.maps.google_map.domain.model.PlaceSearchOption
import com.dd2d.maps.google_map.domain.model.PlaceSearchResult
import kotlinx.coroutines.CancellationException
import javax.inject.Inject

internal class PlaceRepositoryImpl @Inject constructor(
  private val locationSource: LocationSource,
  private val googlePlaceApi: GooglePlaceApi,
): PlaceRepository {
  override suspend fun getCurrentLocation(): PlaceLocation {
    val location = locationSource.getCurrentLocation()
    return PlaceLocation(
      latitude = location.latitude,
      longitude = location.longitude
    )
  }

  override suspend fun getPlace(id: String): Place {
    val response =
      try { googlePlaceApi.getPlace(placeId = id) }
      catch (exception: CancellationException) { throw exception }

    return Place(
      id = response.id,
      name = response.displayName?.text,
      fullAddress = response.formattedAddress,
      location = response.location?.let { dto ->
        PlaceLocation(dto.latitude, dto.longitude)
      },
      types = response.types?: emptyList(),
      contact = response.internationalPhoneNumber,
      rating = response.rating,
      userRatingCount = response.userRatingCount,
      googleMapUri = response.googleMapsUri,
      websiteUrl = response.websiteUri,
      openNow = response.currentOpeningHours?.openNow,
      weekdayDescriptions = response.currentOpeningHours?.weekdayDescriptions,
      images = response.photos?.map { photo ->
        PlaceImage(
          placeId = response.id,
          name = photo.name,
          originWidth = photo.widthPx,
          originHeight = photo.heightPx,
        )
      }?: emptyList(),
    )
  }

  override suspend fun searchPlacesBy(option: PlaceSearchOption): List<PlaceSearchResult> {
    val response =
      try {
        googlePlaceApi.searchByText(
          body = GooglePlaceSearchByTextRequestDto(
            textQuery = option.keyword,
            languageCode = "ko",
            regionCode = "KR",
            locationBias = option.bias?.let {
              PlaceSearchLocationBias(
                circle = PlaceSearchLocationBiasCircle(
                  center = PlaceSearchLocationBiasCircleCenter(
                    latitude = option.bias.centerLat,
                    longitude = option.bias.centerLng,
                  ),
                  radius = option.bias.radius,
                )
              )
            },
          )
        )
      }
      catch (exception: CancellationException) { throw exception }

    return response.places.map { dto ->
      PlaceSearchResult(
        id = dto.id,
        name = dto.displayName.text,
        address = dto.formattedAddress
      )
    }
  }
}