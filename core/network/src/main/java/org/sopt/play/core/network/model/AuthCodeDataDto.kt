package org.sopt.play.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthCodeDataDto(
    @SerialName("authorizationCode") val authorizationCode: String
)
