package org.sopt.play.feature.home.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.play.core.navigation.Route
import org.sopt.play.feature.home.HomeRoute

fun NavController.navigateHome(navOptions: NavOptions) {
    navigate(route = Route.Home, navOptions = navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    modifier: Modifier = Modifier
) {
    composable<Route.Home> {
        HomeRoute(
            modifier = modifier
        )
    }
}