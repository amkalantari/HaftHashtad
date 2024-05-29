package com.hafthashtad.android.core.network

import com.hafthashtad.android.core.network.retrofit.model.NetworkProducts


interface HafthashtadNetworkDataSource {

    suspend fun products(): List<NetworkProducts>

}
