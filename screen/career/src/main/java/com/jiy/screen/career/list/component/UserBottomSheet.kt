package com.jiy.screen.career.list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jiy.ui.theme.Padding
import com.jiy.ui.theme.Shape
import com.jiy.ui.theme.Spacing
import com.jiy.user.domain.model.User
import com.jiy.user.presentation.ProfileImage
import com.jiy.user.presentation.UserDummy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun UserBottomSheet(
  onDismissRequest: () -> Unit,
  user: User,
  modifier: Modifier = Modifier,
  sheetState: SheetState = rememberModalBottomSheetState(),
  scope: CoroutineScope = rememberCoroutineScope()
) {
  ModalBottomSheet(
    onDismissRequest = {
      scope.launch {
        sheetState.hide()
        onDismissRequest()
      }
    },
    sheetState = sheetState,
    shape = Shape.BottomSheet,
    modifier = modifier
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(Spacing.Medium),
      modifier = Modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Min)
        .padding(Padding.BottomSheet)
    ) {
      ProfileImage(
        image = user.profileImageUrl,
        modifier = Modifier
          .size(48.dp)
      )
      Column(
        verticalArrangement = Arrangement.spacedBy(Spacing.ExtraSmall),
        modifier = Modifier.weight(1F)
      ) {
        Text(
          text = user.name,
          style = MaterialTheme.typography.labelLarge
        )
        Text(
          text = user.phoneNum,
          style = MaterialTheme.typography.bodySmall
        )
        Text(
          text = user.email,
          style = MaterialTheme.typography.bodySmall
        )
      }
    }
  }
}

@Preview
@Composable
private fun UserBottomSheetPrev() {
  var open by remember { mutableStateOf<User?>(null) }
  var c by remember { mutableStateOf(0) }
  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.fillMaxSize().background(Color.White)
  ) {

    Button({ open = UserDummy.user(); c++ }) {
      Text(c.toString())
    }
  }
  open?.let {
    UserBottomSheet(
      onDismissRequest = { open = null },
      user = it,
    )
  }
}