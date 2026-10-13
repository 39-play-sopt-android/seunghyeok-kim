package org.sopt.play.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.play.feature.home.HomeRoute
import org.spot.play.core.navigation.Route

fun NavController.navigateIntro(navOptions: NavOptions) {
    navigate(route = Route.Home, navOptions = navOptions)
}

fun NavGraphBuilder.homeNavGraph(

) {
    composable<Route.Home> {
        HomeRoute(

        )
    }
}