package com.jiy.screen.main

import android.content.Intent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jiy.core.state.Stateful
import com.jiy.screen.main.component.ContactBottomSheet
import com.jiy.screen.main.component.career.CareersComponent
import com.jiy.screen.main.component.career.CareersComponentPlaceholder
import com.jiy.screen.main.component.career.CareersLabel
import com.jiy.screen.main.component.skill.SkillStackComponent
import com.jiy.screen.main.component.skill.SkillStackComponentPlaceholder
import com.jiy.screen.main.component.skill.SkillStackLabel
import com.jiy.screen.main.component.user.UserComponent
import com.jiy.screen.main.component.user.UserComponentPlaceholder
import com.jiy.screen.main.model.ContactBottomSheetData
import com.jiy.screen.main.model.MainScreenNavEvent
import com.jiy.user.domain.model.Career
import com.jiy.user.domain.model.Skill
import com.jiy.user.domain.model.User
import com.jiy.user.presentation.UserDummy
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
  onNavEvent: (MainScreenNavEvent) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: MainViewModel = hiltViewModel(),
) {
  val context = LocalContext.current

  val userState by viewModel.userState.collectAsStateWithLifecycle()
  val skillStackState by viewModel.skillStackState.collectAsStateWithLifecycle()
  val careersState by viewModel.careersState.collectAsStateWithLifecycle()

  var contactBottomSheetData by remember { mutableStateOf<ContactBottomSheetData?>(null) }

  Scaffold(
    modifier = modifier
  ) { inner ->
    MainContent(
      userState = userState,
      skillStackState = skillStackState,
      careersState = careersState,
      onPhoneIconClick = { phoneNum ->
        contactBottomSheetData = ContactBottomSheetData(
          label = "연락처",
          value = phoneNum,
          actionLabel = "연락하기",
          onAction = {
            Intent(Intent.ACTION_DIAL)
              .apply { data = "tel:$phoneNum".toUri() }
              .run(context::startActivity)

          },
        )
      },
      onEmailIconClick = { email ->
        contactBottomSheetData = ContactBottomSheetData(
          label = "이메일",
          value = email,
          actionLabel = "메일보내기",
          onAction = {
            Intent(Intent.ACTION_SENDTO)
              .apply { data = "mailto:$email".toUri() }
              .run(context::startActivity)

          },
        )
      },
      onGithubIconClick = { onNavEvent(MainScreenNavEvent.GitHub(it)) },
      onCareerClick = { onNavEvent(MainScreenNavEvent.CareerDetail(it)) },
      onMoreCareerClick = { onNavEvent(MainScreenNavEvent.CareerList) },
      modifier = Modifier
        .consumeWindowInsets(inner)
        .fillMaxSize()
        .padding(inner)
    )
  }

  contactBottomSheetData?.let { data ->
    ContactBottomSheet(
      onDismissRequest = { contactBottomSheetData = null },
      data = data,
    )
  }
}

@Composable
private fun MainContent(
  userState: Stateful<User>,
  skillStackState: Stateful<List<Skill>>,
  careersState: Stateful<List<Career>>,
  onPhoneIconClick: (phoneNum: String) -> Unit,
  onEmailIconClick: (email: String) -> Unit,
  onGithubIconClick: (url: String) -> Unit,
  onCareerClick: (id: Int) -> Unit,
  onMoreCareerClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    verticalArrangement = Arrangement.spacedBy(24.dp),
    modifier = modifier
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp, vertical = 24.dp)
  ) {
    // 유저 정보
    Crossfade(targetState = userState) { state ->
      when(state) {
        is Stateful.Loading -> UserComponentPlaceholder()
        is Stateful.Error -> Text(text = "오류: ${state.exception.mainMessage}")
        is Stateful.Success -> {
          UserComponent(
            user = state.data,
            onPhoneIconClick = onPhoneIconClick,
            onEmailIconClick = onEmailIconClick,
            onGithubIconClick = onGithubIconClick,
          )
        }
      }
    }
    // 기술 스택
    Column {
      SkillStackLabel()
      Crossfade(
        targetState = skillStackState,
        modifier = Modifier.padding(top = 12.dp)
      ) { state ->
        when(state) {
          is Stateful.Loading -> SkillStackComponentPlaceholder()
          is Stateful.Error -> Text("오류 발생: ${state.exception.mainMessage}")
          is Stateful.Success -> SkillStackComponent(state.data)
        }
      }
    }

    // 경력
    Column {
      CareersLabel()
      Crossfade(
        targetState = careersState,
        modifier = Modifier.padding(top = 12.dp)
      ) { state ->
        when(state) {
          is Stateful.Loading -> CareersComponentPlaceholder()
          is Stateful.Error -> Text("오류 발생: ${state.exception.mainMessage}")
          is Stateful.Success -> {
            CareersComponent(
              careers = state.data,
              onClick = onCareerClick,
              onMoreClick = onMoreCareerClick,
            )
          }
        }
      }
    }
  }
}

@Preview
@Composable
private fun MainContentPrev() {
  var us by remember { mutableStateOf<Stateful<User>>(Stateful.Loading) }
  var sss by remember { mutableStateOf<Stateful<List<Skill>>>(Stateful.Loading) }
  var cs by remember { mutableStateOf<Stateful<List<Career>>>(Stateful.Loading) }

  val scope = rememberCoroutineScope()

  fun setSuccess() {
    us = Stateful.Success(UserDummy.user())
    sss = Stateful.Success(List(10) { UserDummy.skill() }.sortedByDescending { it.level })
    cs = Stateful.Success(List(3) { UserDummy.career() })
  }

  LaunchedEffect(Unit) {
    delay(300)
    setSuccess()
  }

  Column(
    modifier = Modifier.fillMaxSize().background(Color.White).padding(horizontal = 16.dp)
  ) {
    MainContent(
      userState = us,
      skillStackState = sss,
      careersState = cs,
      onPhoneIconClick = {},
      onEmailIconClick = {},
      onGithubIconClick = {},
      onCareerClick = {},
      onMoreCareerClick = {},
      modifier = Modifier.weight(1F),
    )
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center,
      modifier = Modifier.fillMaxWidth()
    ) {
      Button(
        {
          scope.launch {
            us = Stateful.Loading
            sss = Stateful.Loading
            cs = Stateful.Loading
            delay(3000)
            setSuccess()
          }
        }
      ) {
        Text("loading -> success")
      }
    }
  }
}