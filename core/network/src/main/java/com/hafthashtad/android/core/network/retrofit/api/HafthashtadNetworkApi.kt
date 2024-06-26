package com.hafthashtad.android.core.network.retrofit.api

import com.hafthashtad.android.core.network.retrofit.model.NetworkProducts
import retrofit2.http.GET

interface HafthashtadNetworkApi {

    @GET("/products")
    suspend fun products(): List<NetworkProducts>
}