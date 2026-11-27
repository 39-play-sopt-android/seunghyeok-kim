package org.sopt.play.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdatePostRequestDto(
    @SerialName("title") val title: String,
    @SerialName("content") val content: String
)
