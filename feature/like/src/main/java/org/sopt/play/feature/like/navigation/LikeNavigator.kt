package org.sopt.play.feature.like.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.play.core.navigation.Route
import org.sopt.play.feature.like.LikeRoute

fun NavController.navigateLike(navOptions: NavOptions) {
    navigate(route = Route.Like, navOptions = navOptions)
}

fun NavGraphBuilder.likeNavGraph(
    modifier: Modifier = Modifier
) {
    composable<Route.Like> {
        LikeRoute(
            modifier = modifier
        )
    }
}