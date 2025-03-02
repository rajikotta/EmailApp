package com.raji.data.dto.list


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attachment(
    @SerialName("filename")
    val filename: String? = null,
    @SerialName("mimeType")
    val mimeType: String? = null,
    @SerialName("size")
    val size: Int? = null
)