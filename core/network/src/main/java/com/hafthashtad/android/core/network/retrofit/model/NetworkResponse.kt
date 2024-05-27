package com.hafthashtad.android.core.network.retrofit.model

import com.hafthashtad.android.core.network.di.NetworkModule
import kotlinx.serialization.Serializable

/**
 * Wrapper for data provided from the [NetworkModule.hafthashtadBaseUrl]
 */
@Serializable
data class NetworkResponse<T>(
    val isSuccessful: Boolean,
    val message: String,
    val data: T
)
