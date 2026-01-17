package com.dd2d.json_placeholder.user.presentation.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.dd2d.json_placeholder.user.domain.model.UserTodo
import com.dd2d.json_placeholder.user.presentation.detail.model.TodoListUIState

@Composable
internal fun UserTodoListComponent(
  todoListUIState: TodoListUIState,
  onFilterChange: (Boolean?) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(modifier = modifier) {
    CompleteFilter(
      completeFilter = todoListUIState.completeFilter,
      onFilterChange = onFilterChange,
      modifier = Modifier.align(Alignment.End),
    )
    LazyColumn(
      modifier = Modifier.fillMaxWidth().weight(1F)
    ) {
      items(items = todoListUIState.todoList, key = UserTodo::id) { item ->
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center,
          modifier = Modifier
            .animateItem()
            .fillMaxWidth()
            .padding(vertical = 8.dp)
        ) {
          Text(
            text = item.title,
            fontWeight = FontWeight.W400,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 16.sp,
            lineHeight = 1.2.em,
            modifier = Modifier.weight(1F)
          )
          Checkbox(checked = item.isCompleted, onCheckedChange = null)
        }
      }
    }
  }
}

@Composable
private fun CompleteFilter(
  completeFilter: Boolean?,
  onFilterChange: (Boolean?) -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    modifier = modifier
      .height(IntrinsicSize.Min)
      .padding(vertical = 12.dp)
  ) {
    CompleteFilterButton(
      label = "진행중",
      isSelected = completeFilter == false,
      onClick = { newState ->
        if(newState) {
          onFilterChange(false)
        }
        else {
          onFilterChange(null)
        }
      }
    )
    VerticalDivider(modifier = Modifier.fillMaxHeight())
    CompleteFilterButton(
      label = "성공",
      isSelected = completeFilter == true,
      onClick = { newState ->
        if(newState) {
          onFilterChange(true)
        }
        else {
          onFilterChange(null)
        }
      }
    )
  }
}

@Composable
private fun CompleteFilterButton(
  label: String,
  isSelected: Boolean,
  onClick: (Boolean) -> Unit,
  modifier: Modifier = Modifier
) {
  Text(
    text = label,
    fontWeight =
      if(isSelected) FontWeight.W600
      else FontWeight.W400,
    color =
      if(isSelected) MaterialTheme.colorScheme.onSecondaryContainer
      else MaterialTheme.colorScheme.onSurface,
    fontSize = 14.sp,
    lineHeight = 1.2.em,
    modifier = modifier
      .clip(RoundedCornerShape(8.dp))
      .toggleable(value = isSelected, onValueChange = onClick)
      .background(
        color =
          if(isSelected) MaterialTheme.colorScheme.secondaryContainer
          else Color.Transparent
      )
      .padding(horizontal = 8.dp, vertical = 4.dp)
  )
}