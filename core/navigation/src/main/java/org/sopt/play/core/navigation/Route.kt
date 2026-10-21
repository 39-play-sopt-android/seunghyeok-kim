package org.sopt.play.core.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {
    @Serializable data object Auth : Route
    @Serializable data object Home : Route
    @Serializable data object Like : Route
    @Serializable data object Mypage : Route
}