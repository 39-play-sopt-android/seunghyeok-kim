package org.sopt.play

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import org.sopt.play.core.navigation.MainTab
import org.sopt.play.core.navigation.Route
import org.sopt.play.designsystem.component.PlaySoptNavigationBar
import org.sopt.play.designsystem.theme.PlaySoptTheme
import org.sopt.play.feature.auth.AuthRoute
import org.sopt.play.feature.home.HomeRoute
import org.sopt.play.feature.like.LikeRoute
import org.sopt.play.feature.mypage.MypageRoute

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier
) {
    val backStack = remember { mutableStateListOf<NavKey>(Route.Auth) }
    val currentDestination = backStack.lastOrNull()

    val isAuthScreen = currentDestination is Route.Auth
    val showBottomBar = !isAuthScreen && currentDestination != null

    val currentTab = MainTab.entries.find { tab ->
        currentDestination == tab.route
    } ?: MainTab.Home

    val myEntryProvider: (NavKey) -> NavEntry<NavKey> = { key ->
        NavEntry(key = key) {
            when (key) {
                is Route.Auth -> {
                    AuthRoute(
                        onNavToHome = {
                            backStack.clear()
                            backStack.add(Route.Home)
                        },
                        onBackClick = {
                            if (backStack.size > 1) {
                                backStack.removeAt(backStack.lastIndex)
                            }
                        }
                    )
                }

                is Route.Home -> {
                    HomeRoute()
                }

                is Route.Like -> {
                    LikeRoute()
                }

                is Route.Mypage -> {
                    MypageRoute()
                }

                else -> {}
            }
        }
    }

    Scaffold(
        modifier = modifier,
        containerColor = PlaySoptTheme.color.white,
        bottomBar = {
            if (showBottomBar) {
                PlaySoptNavigationBar(
                    currentTab = currentTab,
                    onTabSelected = { tab ->
                        if (backStack.lastOrNull() != tab.route) {
                            backStack.removeAll { it !is Route.Auth }
                            backStack.add(tab.route)
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.padding(innerPadding),
            onBack = {
                if (backStack.size > 1) {
                    backStack.removeAt(backStack.lastIndex)
                }
            },
            entryProvider = myEntryProvider
        )
    }
}