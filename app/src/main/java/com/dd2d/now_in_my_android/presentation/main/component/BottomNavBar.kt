package com.dd2d.now_in_my_android.presentation.main.component

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.dd2d.now_in_my_android.presentation.main.model.MainScreenBottomNavItem

@Composable
fun BottomNavBar(
  selectedItem: MainScreenBottomNavItem,
  onItemClick: (MainScreenBottomNavItem) -> Unit,
  modifier: Modifier = Modifier
) {
  NavigationBar(
    modifier = modifier
  ) {
    MainScreenBottomNavItem.entries.forEach { item ->
      val isSelected = item == selectedItem
      NavigationBarItem(
        selected = isSelected,
        onClick = { onItemClick(item) },
        icon = {
          Icon(
            imageVector = ImageVector.vectorResource(id = if(isSelected) item.selectedIconRes else item.iconRes),
            contentDescription = null
          )
        },
        label = {
          Text(
            text = item.label,
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 14.sp,
            lineHeight = 1.2.em
          )
        },
        alwaysShowLabel = true,
      )
    }
  }
}