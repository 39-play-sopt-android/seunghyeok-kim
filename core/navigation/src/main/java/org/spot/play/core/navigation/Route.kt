package org.spot.play.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    @Serializable data object Auth : Route{

    }
    @Serializable data object Home : Route{

    }
}