package presentation.screen.main

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabNavigator
import getNavigationBarPadding
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.composable.NavigationBarComposable
import presentation.composable.NavigationBarItem
import presentation.composable.drawTopIndicator
import presentation.composable.toPx
import theme.Theme

object MainContainer : Screen {
    @Composable
    override fun Content() {
        TabNavigator(HomeTab) {
            Scaffold(
                bottomBar = {
                    val tabNavigator = LocalTabNavigator.current
                    val tabs = rememberTabsContainer()
                    BottomBar(tabs, tabNavigator)
                },
                content = {
                    Column(Modifier.fillMaxSize().padding(bottom = it.calculateBottomPadding())) {
                        CurrentTab()
                    }
                }
            )
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    @Composable
    private fun BottomBar(tabs: List<TabContainer>, tabNavigator: TabNavigator) {
        var xIndicatorOffset by remember { mutableStateOf(Float.NaN) }
        val xOffsetAnimated by animateFloatAsState(targetValue = xIndicatorOffset)
        val indicatorWidthPx = 40.dp.toPx()
        val iconSizePx = 24.dp.toPx()

        NavigationBarComposable (
            modifier = Modifier.background(Theme.colors.surface)
                .drawTopIndicator(xOffsetAnimated).padding(getNavigationBarPadding())
        ) {
            tabs.forEach { tabContainer ->
                val selected = tabNavigator.current == tabContainer.tab
                val drawable =
                    if (selected) tabContainer.selectedIcon else tabContainer.unSelectedIcon

                NavigationBarItem(
                    icon = { color ->
                        Icon(
                            painter = painterResource(drawable),
                            contentDescription = null,
                            tint = color,
                            modifier = Modifier.size(24.dp).onGloballyPositioned {
                                if (selected) {
                                    xIndicatorOffset =
                                        it.positionInRoot().x + (iconSizePx - indicatorWidthPx) / 2
                                }
                            }
                        )
                    },
                    label = { Text(text = tabContainer.tab.options.title, style = it) },
                    selected = selected,
                    onClick = { tabNavigator.current = tabContainer.tab },
                )
            }
        }
    }
}
