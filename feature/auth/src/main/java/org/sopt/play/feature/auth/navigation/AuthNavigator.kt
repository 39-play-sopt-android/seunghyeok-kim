package org.sopt.play.feature.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.play.feature.auth.AuthRoute
import org.sopt.play.core.navigation.Route

fun NavController.navigateAuth(navOptions: NavOptions) {
    navigate(route = Route.Auth, navOptions = navOptions)
}

fun NavGraphBuilder.authNavGraph(
    onNavToHome: () -> Unit,
    onBackClick: () -> Unit
) {
    composable<Route.Auth> {
        AuthRoute(
            onNavToHome = onNavToHome,
            onBackClick = onBackClick
        )
    }
}