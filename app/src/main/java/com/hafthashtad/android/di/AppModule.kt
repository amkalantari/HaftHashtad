package com.hafthashtad.android.di

import com.hafthashtad.android.core.data.common.qualifer.Qualifiers.QUALIFIER_APP_VERSION_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.hafthashtad.android.BuildConfig
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named(QUALIFIER_APP_VERSION_NAME)
    fun providesAppVersionName(): String = BuildConfig.VERSION_NAME
}
