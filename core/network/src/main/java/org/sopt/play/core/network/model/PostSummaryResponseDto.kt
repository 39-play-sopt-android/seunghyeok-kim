package org.sopt.play.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostSummaryResponseDto(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String,
    @SerialName("content") val content: String,
    @SerialName("author") val author: String? = null,
    @SerialName("createdAt") val createdAt: String? = null,
    @SerialName("updatedAt") val updatedAt: String? = null
)
