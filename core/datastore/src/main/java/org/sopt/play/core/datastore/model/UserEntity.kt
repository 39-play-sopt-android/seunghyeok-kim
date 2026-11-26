@file:OptIn(kotlinx.serialization.InternalSerializationApi::class)

package org.sopt.play.core.datastore.model

import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    val id: Int,
    val name: String,
    val email: String,
    val password: String
)
