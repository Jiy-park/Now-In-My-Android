package com.jiy.screen.toy.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.jiy.ui.component.SectionLabel
import com.jiy.ui.modifier.scalableClick
import com.jiy.ui.theme.Padding
import com.jiy.ui.theme.Shape
import com.jiy.ui.theme.Spacing
import com.joy.toy.domain.toy_core.model.ToyImage

@Composable
internal fun ToyImages(
  images: List<ToyImage>,
  onImageClick: (index: Int, image: ToyImage) -> Unit,
  modifier: Modifier = Modifier
) {
  val pagerState = rememberPagerState { images.size }
  Column(modifier = modifier) {
    SectionLabel("이미지 (${pagerState.currentPage + 1}/${images.size})")
    HorizontalPager(
      state = pagerState,
      key = { images[it].id },
      pageSpacing = Spacing.Large,
      pageSize = PageSize.Fixed(270.dp),
      modifier = Modifier
        .padding(top = Spacing.Small)
        .fillMaxWidth()
    ) { page ->
      val toyImage = images[page]
      ToyImageComponent(
        toyImage = toyImage,
        onClick = { onImageClick(page, toyImage) },
        modifier = Modifier
          .fillMaxWidth()
      )
    }
  }
}

@Composable
private fun ToyImageComponent(
  toyImage: ToyImage,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  Column(
    modifier = modifier
      .scalableClick(onClick = onClick, shape = Shape.Card)
  ) {
    AsyncImage(
      model = ImageRequest.Builder(context)
        .data(toyImage.imageUrl)
        .size(1000,  750)
        .build(),
      contentDescription = toyImage.description,
      contentScale = ContentScale.Crop,
      modifier = Modifier
        .clip(Shape.Card)
        .fillMaxWidth()
        .aspectRatio(4F / 3F)
    )
    Text(
      text = toyImage.title,
      style = MaterialTheme.typography.titleSmall,
      modifier = Modifier
        .padding(top = Padding.Component.Vertical.Small)
    )
    toyImage.description?.let { description ->
      Text(
        text = description,
        style = MaterialTheme.typography.bodySmall,
      )
    }
  }
}