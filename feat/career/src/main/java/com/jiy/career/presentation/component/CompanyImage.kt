package com.jiy.career.presentation.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.jiy.career.R
import com.jiy.ui.theme.Shape

@Composable
fun CompanyImage(
  companyName: String,
  image: String?,
  modifier: Modifier = Modifier,
  contentScale: ContentScale = ContentScale.Crop
) {
  AsyncImage(
    model = image?: R.drawable.office,
    contentDescription = "$companyName 대표 이미지",
    contentScale = contentScale,
    modifier = modifier
      .clip(Shape.Card)
      .aspectRatio(1F)
  )
}