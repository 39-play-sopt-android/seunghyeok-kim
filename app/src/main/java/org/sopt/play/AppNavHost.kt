package org.sopt.play

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.sopt.play.feature.auth.navigation.authNavGraph
import org.sopt.play.feature.home.navigation.homeNavGraph
import org.spot.play.core.navigation.Route

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Route.Auth,
        modifier = modifier
    ) {
        authNavGraph(
            onNavToHome = {
                navController.navigate(Route.Home) {
                    popUpTo(Route.Auth) { inclusive = true }
                }
            },
            onBackClick = {

            }
        )
        homeNavGraph(

        )
    }
}