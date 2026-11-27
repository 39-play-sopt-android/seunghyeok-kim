package org.sopt.play.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostSliceResponseDto(
    @SerialName("posts") val posts: List<PostSummaryResponseDto> = emptyList(),
    @SerialName("nextCursor") val nextCursor: Long? = null,
    @SerialName("hasNext") val hasNext: Boolean = false
)
