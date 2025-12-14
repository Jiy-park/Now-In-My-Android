package com.jiy.screen.main.component

import android.content.ClipData
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.jiy.screen.main.R
import com.jiy.screen.main._core.ui.modifier.scalableClick
import com.jiy.screen.main.model.BottomSheetShape
import com.jiy.screen.main.model.CardPaddingValues
import com.jiy.screen.main.model.CardShape
import com.jiy.screen.main.model.ContactBottomSheetData
import kotlinx.coroutines.launch


@Composable
internal fun ContactBottomSheet(
  onDismissRequest: () -> Unit,
  data: ContactBottomSheetData,
  modifier: Modifier = Modifier,
  sheetState: SheetState = rememberModalBottomSheetState(),
) {
  val clipboard = LocalClipboard.current
  val scope = rememberCoroutineScope()
  fun copyData() {
    scope.launch {
      val clipData = ClipData.newPlainText(data.label, data.value)
      val entry = ClipEntry(clipData)
      clipboard.setClipEntry(entry)
    }
  }

  ModalBottomSheet(
    onDismissRequest = onDismissRequest,
    sheetState = sheetState,
    shape = BottomSheetShape,
    contentWindowInsets = {
      val bottomNavHeight = WindowInsets.navigationBars
      val bottomSheetInset = WindowInsets(left = 16.dp, right = 16.dp, top = 16.dp)
      bottomNavHeight.union(bottomSheetInset)
    },
    modifier = modifier,
  ) {
    Text(text = data.label, style = MaterialTheme.typography.bodyLarge)
    Spacer(Modifier.height(12.dp))
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween,
      modifier = Modifier
        .fillMaxWidth()
        .scalableClick(shape = CardShape, onClick = ::copyData)
        .padding(CardPaddingValues)
    ) {
      Text(text = data.value, style = MaterialTheme.typography.titleLarge)
      Icon(
        imageVector = ImageVector.vectorResource(R.drawable.copy),
        contentDescription = "${data.label} 복사하기"
      )
    }
    Spacer(Modifier.height(12.dp))
    Button(
      onClick = data.onAction,
      shape = RoundedCornerShape(12.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      Text(text = data.actionLabel, style = MaterialTheme.typography.bodySmall)
    }
  }
}