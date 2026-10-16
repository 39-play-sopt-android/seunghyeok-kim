package org.sopt.play.feature.mypage.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.play.core.navigation.Route
import org.sopt.play.feature.mypage.MypageRoute

fun NavController.navigateMypage(navOptions: NavOptions) {
    navigate(route = Route.Mypage, navOptions = navOptions)
}

fun NavGraphBuilder.mypageNavGraph(
    modifier: Modifier = Modifier
) {
    composable<Route.Mypage> {
        MypageRoute(
            modifier = modifier
        )
    }
}