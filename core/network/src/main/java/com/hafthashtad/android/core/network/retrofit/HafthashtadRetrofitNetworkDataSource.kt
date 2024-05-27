package com.hafthashtad.android.core.network.retrofit

import com.hafthashtad.android.core.network.HafthashtadNetworkDataSource
import com.hafthashtad.android.core.network.retrofit.api.HafthashtadNetworkApi
import com.hafthashtad.android.core.network.retrofit.model.hafthashtad.NetworkProducts
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HafthashtadRetrofitNetworkDataSource @Inject constructor(
    private val networkApi: HafthashtadNetworkApi
) : HafthashtadNetworkDataSource {

    override suspend fun products(): NetworkProducts =
        networkApi.products()

}
