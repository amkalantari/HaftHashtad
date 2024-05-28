package com.hafthashtad.android.core.data.repository.remoteHafthashtad

import com.hafthashtad.android.core.data.model.Products
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    fun products(): Flow<List<Products>>

}