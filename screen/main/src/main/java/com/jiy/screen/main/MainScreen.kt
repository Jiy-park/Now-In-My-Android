package com.jiy.screen.main

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jiy.screen.main.model.MainScreenNavEvent
import com.jiy.screen.main.model.MainScreenTab
import com.jiy.screen.main.playground.PlaygroundScreen
import com.jiy.screen.main.playground.PlaygroundViewModel
import com.jiy.screen.main.portfolio.PortfolioScreen
import com.jiy.screen.main.portfolio.PortfolioViewModel

@Composable
fun MainScreen(
  onNavEvent: (MainScreenNavEvent) -> Unit,
  modifier: Modifier = Modifier,
  viewModel: MainViewModel = hiltViewModel()
) {
  val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()

  val portfolioViewModel: PortfolioViewModel = hiltViewModel()
  val playgroundViewModel: PlaygroundViewModel = hiltViewModel()

  Scaffold(
    bottomBar = {
      BottomAppBar {
        NavigationBar {
          MainScreenTab.entries.forEach { tab ->
            val isSelected = currentTab == tab
            NavigationBarItem(
              selected = isSelected,
              onClick = { viewModel.changeTab(tab) },
              icon = {
                Icon(
                  imageVector = ImageVector.vectorResource(if(isSelected) tab.unselectedIconRes else tab.unselectedIconRes),
                  contentDescription = tab.selectedText,
                )
              },
              label = {
                Text(text = tab.selectedText)
              },
              colors = NavigationBarItemDefaults.colors(
                selectedIconColor = MaterialTheme.colorScheme.primary,
                selectedTextColor = MaterialTheme.colorScheme.primary,
                unselectedIconColor = MaterialTheme.colorScheme.primaryContainer,
                unselectedTextColor = MaterialTheme.colorScheme.primaryContainer,
              ),
            )
          }
        }
      }
    },
    modifier = modifier
  ) { inner ->
    Crossfade(
      targetState = currentTab,
      modifier = Modifier
        .consumeWindowInsets(inner)
        .fillMaxSize()
        .padding(inner)
    ) { tab ->
      when(tab) {
        MainScreenTab.Portfolio -> {
          PortfolioScreen(
            onGitHubClick = { onNavEvent(MainScreenNavEvent.Portfolio.GitHub(it)) },
            onCareerDetailClick = { onNavEvent(MainScreenNavEvent.Portfolio.CareerDetail(it)) },
            onCareerListClick = { onNavEvent(MainScreenNavEvent.Portfolio.CareerList) },
            viewModel = portfolioViewModel,
            modifier = Modifier.fillMaxSize(),
          )
        }
        MainScreenTab.Playground -> {
          PlaygroundScreen(
            onToyClick = { onNavEvent(MainScreenNavEvent.Playground.ToyDetail(it)) },
            onMoreToyClick = { onNavEvent(MainScreenNavEvent.Playground.ToyList) },
            viewModel = playgroundViewModel,
            modifier = Modifier.fillMaxSize(),
          )
        }
      }
    }
  }
}