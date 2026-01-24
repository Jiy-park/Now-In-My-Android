package com.dd2d.maps.google_map.presentation.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import com.dd2d.core.stateful.Stateful
import com.dd2d.maps.google_map.domain.model.Place

@Composable
internal fun PlaceBottomSheet(
  onDismissRequest: () -> Unit,
  placeState: Stateful<Place>,
  modifier: Modifier = Modifier,
  state: SheetState = rememberModalBottomSheetState()
) {
  val context = LocalContext.current

  ModalBottomSheet(
    sheetState = state,
    onDismissRequest = onDismissRequest,
    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
    contentWindowInsets = {
      WindowInsets.navigationBars
    },
    modifier = modifier
  ) {
    Column(
      modifier = Modifier
        .padding(horizontal = 16.dp)
    ) {
      when(placeState) {
        is Stateful.Loading -> {
          Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 20.dp)
          ) {
            CircularProgressIndicator(
              strokeWidth = 2.dp,
              modifier = Modifier.size(20.dp)
            )
          }
        }
        is Stateful.Error -> {
          Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
              .fillMaxWidth()
              .height(200.dp)
          ) {
            Text(text = placeState.exception.localizedMessage?: "오류")
          }
        }
        is Stateful.Success -> {
          placeState.data.name?.let { name ->
            Text(text = name)
          }
          placeState.data.openNow?.let { openNow ->
            Text(text = if(openNow) "영업중" else "닫힘")
          }
          placeState.data.weekdayDescriptions?.forEach { description ->
            Text(text = description)
          }
          placeState.data.rating?.let { rating ->
            Text(
              text = buildString {
                append(rating)
                placeState.data.userRatingCount?.let { userRatingCount ->
                  append("($userRatingCount)")
                }
              }
            )
          }
          placeState.data.contact?.let { contact ->
            Text(text = contact)
          }
          placeState.data.websiteUrl?.let { websiteUrl ->
            Text(text = websiteUrl)
          }
          placeState.data.googleMapUri?.let { googleMapUri ->
            Text(text = googleMapUri)
          }
          placeState.data.fullAddress?.let { fullAddress ->
            Text(text = fullAddress)
          }
          placeState.data.location?.let { location ->
            Text(text = location.toString())
          }
          FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            placeState.data.types.forEach { type ->
              Text(text = type)
            }
          }
          Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState())
          ) {
            val imageSize = 300
            placeState.data.images.forEachIndexed { index, image ->
              AsyncImage(
                model = ImageRequest.Builder(context)
                  .data(image.url(size = imageSize))
                  .diskCachePolicy(CachePolicy.DISABLED)
                  .memoryCachePolicy(CachePolicy.ENABLED)
                  .memoryCacheKey("${image.placeId}/image/$index/&size=$imageSize")
                  .size(imageSize)
                  .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                  .clip(RoundedCornerShape(8.dp))
                  .width(100.dp)
                  .aspectRatio(1F)
              )
            }
          }
        }
      }
    }
  }
}