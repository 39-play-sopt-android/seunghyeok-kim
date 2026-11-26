package org.sopt.play

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import org.sopt.play.core.navigation.MainTab
import org.sopt.play.core.navigation.Route
import org.sopt.play.core.designsystem.component.PlaySoptNavigationBar
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.feature.auth.AuthRoute
import org.sopt.play.feature.home.HomeRoute
import org.sopt.play.feature.like.LikeRoute
import org.sopt.play.feature.mypage.MypageRoute

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(Route.Auth)
    val currentDestination = backStack.lastOrNull()

    val currentTab = MainTab.entries.find { it.route == currentDestination }

    fun popBackStack() {
        if (backStack.size > 1) {
            backStack.removeAt(backStack.lastIndex)
        }
    }

    Scaffold(
        modifier = modifier,
        containerColor = PlaySoptTheme.color.white,
        bottomBar = {
            if (currentTab != null) {
                PlaySoptNavigationBar(
                    currentTab = currentTab,
                    onTabSelected = { tab ->
                        if (backStack.lastOrNull() != tab.route) {
                            backStack.clear()
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
            onBack = ::popBackStack,
            entryProvider = entryProvider {
                entry<Route.Auth> {
                    AuthRoute(
                        onNavToHome = {
                            backStack.clear()
                            backStack.add(Route.Home)
                        },
                        onBackClick = ::popBackStack
                    )
                }
                entry<Route.Home> {
                    HomeRoute()
                }
                entry<Route.Like> {
                    LikeRoute()
                }
                entry<Route.Mypage> {
                    MypageRoute()
                }
            }
        )
    }
}