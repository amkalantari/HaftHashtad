package com.hafthashtad.android.core.domain.hafthashtad

import com.hafthashtad.android.core.data.model.Products
import com.hafthashtad.android.core.data.repository.remoteHafthashtad.RemoteProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productsRepository: RemoteProductsRepository
) {
    /**
     * Returns available product.
     *
     */
    operator fun invoke(): Flow<Products> {
        return productsRepository.products()
    }
}