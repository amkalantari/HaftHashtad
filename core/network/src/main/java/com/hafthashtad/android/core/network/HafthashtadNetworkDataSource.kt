package com.hafthashtad.android.core.network

import com.hafthashtad.android.core.network.retrofit.model.hafthashtad.NetworkProducts


interface HafthashtadNetworkDataSource {

    suspend fun products(): NetworkProducts

}
