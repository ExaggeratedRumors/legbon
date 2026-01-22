package com.er.legbon.features.users

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(val username: String, val password: String)
