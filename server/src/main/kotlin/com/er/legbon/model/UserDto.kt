package com.er.legbon.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: Int? = null,
    val discordId: Int,
    val nick: String,
    val admin: Boolean = false,
    val active: Boolean = true
)