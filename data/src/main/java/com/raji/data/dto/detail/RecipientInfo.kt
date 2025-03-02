package com.raji.data.dto.detail


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecipientInfo(
    @SerialName("email")
    val email: String,
    @SerialName("name")
    val name: String
)