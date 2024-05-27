package com.hafthashtad.android.core.data.di

import com.hafthashtad.android.core.data.repository.HafthashtadUserDataRepository
import com.hafthashtad.android.core.data.repository.offline.OfflineHafthashtadUserDataRepository
import com.hafthashtad.android.core.data.repository.remoteHafthashtad.RemoteProductsRepository
import com.hafthashtad.android.core.data.repository.remoteHafthashtad.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsHafthashtadUserDataRepository(
        userDataRepository: OfflineHafthashtadUserDataRepository
    ): HafthashtadUserDataRepository

    @Binds
    fun bindsHafthashtadRemoteUserRepository(
        remoteUserRepository: RemoteProductsRepository
    ): ProductsRepository

}
