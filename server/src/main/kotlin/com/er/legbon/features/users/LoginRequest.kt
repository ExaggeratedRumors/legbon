package com.er.legbon.features.users

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(val username: String, val password: String)
