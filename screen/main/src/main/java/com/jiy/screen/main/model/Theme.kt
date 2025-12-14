package com.jiy.screen.main.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// colors
internal val SurfaceColor = Color(0xFFF8F9FA)
internal val BorderColor = Color(0xFFF1F2F5)
internal val PrimaryColor = Color(0XFF20C997)
internal val PrimaryContainerColor = Color(0xFFE8F9F4)

// shapes
internal val CardShape = RoundedCornerShape(8.dp)
internal val ChipShape = CircleShape
internal val BottomSheetShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)

// padding
internal val CardPaddingValues = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
internal val ChipPaddingValues = PaddingValues(horizontal = 12.dp, vertical = 8.dp)