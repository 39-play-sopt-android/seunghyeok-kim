package org.sopt.play.core.data.mapper

import org.sopt.play.core.domain.model.Post
import org.sopt.play.core.network.model.CreatePostRequestDto
import org.sopt.play.core.network.model.PostDetailResponseDto
import org.sopt.play.core.network.model.PostSummaryResponseDto
import org.sopt.play.core.network.model.UpdatePostRequestDto
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun formatToDotDate(rawDate: String?): String {
    if (rawDate.isNullOrBlank()) return ""
    return runCatching {
        val parsed = runCatching { OffsetDateTime.parse(rawDate).toLocalDateTime() }
            .getOrElse { runCatching { LocalDateTime.parse(rawDate) }.getOrElse { null } }

        if (parsed != null) {
            parsed.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
        } else {
            val parts = rawDate.take(10).split("-", "/")
            if (parts.size == 3) "${parts[0]}.${parts[1]}.${parts[2]}" else rawDate
        }
    }.getOrDefault(rawDate)
}

fun PostSummaryResponseDto.toModel(): Post {
    return Post(
        id = id,
        title = title,
        content = content,
        author = author ?: "",
        createdAt = formatToDotDate(createdAt),
        updatedAt = formatToDotDate(updatedAt)
    )
}

fun PostDetailResponseDto.toModel(): Post {
    return Post(
        id = id,
        title = title,
        content = content,
        author = author ?: "",
        createdAt = formatToDotDate(createdAt),
        updatedAt = formatToDotDate(updatedAt)
    )
}

fun Post.toCreateDto(author: String = this.author): CreatePostRequestDto {
    return CreatePostRequestDto(
        title = title,
        content = content,
        author = author
    )
}

fun Post.toUpdateDto(): UpdatePostRequestDto {
    return UpdatePostRequestDto(
        title = title,
        content = content
    )
}
