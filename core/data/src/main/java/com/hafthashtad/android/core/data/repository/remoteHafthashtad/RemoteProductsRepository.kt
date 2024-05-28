package com.hafthashtad.android.core.data.repository.remoteHafthashtad

import com.hafthashtad.android.core.data.common.network.Dispatcher
import com.hafthashtad.android.core.data.common.network.HafthashtadDispatchers
import com.hafthashtad.android.core.data.model.Products
import com.hafthashtad.android.core.network.HafthashtadNetworkDataSource
import com.hafthashtad.android.core.network.retrofit.model.hafthashtad.asExternalModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteProductsRepository @Inject constructor(
    @Dispatcher(HafthashtadDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val network: HafthashtadNetworkDataSource,
) : ProductsRepository {

    override fun products(): Flow<List<Products>> = flow {
        val products = network.products().asExternalModel()
        emit(products)
    }.flowOn(ioDispatcher)
}
