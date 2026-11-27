package org.sopt.play.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IssueAuthCodeRequestDto(
    @SerialName("part") val part: String
)
