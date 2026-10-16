package org.sopt.play.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    @Serializable data object Auth : Route{

    }
    @Serializable data object Home : Route{

    }
    @Serializable data object Like : Route{

    }
    @Serializable data object Mypage : Route{

    }
}