package com.hafthashtad.android.core.data.common.result

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponseMessage(
    @SerialName("message")
    val message : String,
    @SerialName("code")
    val code : Int,
    @SerialName("label")
    val label : String
)
