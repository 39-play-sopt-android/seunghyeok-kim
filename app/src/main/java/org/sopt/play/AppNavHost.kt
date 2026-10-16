package org.sopt.play

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.play.core.navigation.MainTab
import org.sopt.play.core.navigation.Route
import org.sopt.play.designsystem.component.PlaySoptNavigationBar
import org.sopt.play.designsystem.theme.PlaySoptTheme
import org.sopt.play.feature.auth.navigation.authNavGraph
import org.sopt.play.feature.home.navigation.homeNavGraph
import org.sopt.play.feature.like.navigation.likeNavGraph
import org.sopt.play.feature.mypage.navigation.mypageNavGraph

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isAuthScreen = currentDestination?.hasRoute<Route.Auth>() == true
    val showBottomBar = !isAuthScreen && currentDestination != null

    val currentTab = MainTab.entries.find { tab ->
        currentDestination?.hierarchy?.any { it.hasRoute(tab.route::class) } == true
    } ?: MainTab.Home

    Scaffold(
        modifier = modifier,
        containerColor = PlaySoptTheme.color.white,
        bottomBar = {
            if (showBottomBar) {
                PlaySoptNavigationBar(
                    currentTab = currentTab,
                    onTabSelected = { tab ->
                        navController.navigate(tab.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.Auth,
            modifier = Modifier.padding(innerPadding)
        ) {
            authNavGraph(
                onNavToHome = {
                    navController.navigate(Route.Home) {
                        popUpTo(Route.Auth) { inclusive = true }
                    }
                },
                onBackClick = {}
            )
            homeNavGraph()
            likeNavGraph()
            mypageNavGraph()
        }
    }
}