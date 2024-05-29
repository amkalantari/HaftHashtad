package com.hafthashtad.android.core.network.di

import com.hafthashtad.android.core.network.BuildConfig
import com.hafthashtad.android.core.network.HafthashtadNetworkDataSource
import com.hafthashtad.android.core.network.retrofit.HafthashtadRetrofitNetworkDataSource
import com.hafthashtad.android.core.network.retrofit.api.HafthashtadNetworkApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.sync.Mutex
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindsHafthashtadNetworkDatasource(
        hafthashtadNetworkDataSource: HafthashtadRetrofitNetworkDataSource
    ): HafthashtadNetworkDataSource

    companion object {

        @Provides
        @Singleton
        fun providesNetworkJson(): Json = Json {
            ignoreUnknownKeys = true
        }

        @Singleton
        @Provides
        fun provideTokenMutex(): Mutex = Mutex()


        @Provides
        @Singleton
        fun providesHafthashtadRetrofitNetworkDataSource(
            networkJson: Json,
        ): HafthashtadNetworkApi {

            val hafthashtadBaseUrl = BuildConfig.SERVER_API_URL_HAFTHASHTAD

            val okHttpClientBuilder = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS) // connect timeout
                .writeTimeout(30, TimeUnit.SECONDS) // write timeout
                .readTimeout(30, TimeUnit.SECONDS) // read timeout

            if (BuildConfig.DEBUG) {
                okHttpClientBuilder.addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
            }

            val builder = Retrofit.Builder()
                .baseUrl(hafthashtadBaseUrl)
                .client(okHttpClientBuilder.build())
                .addConverterFactory(
                    networkJson.asConverterFactory("application/json".toMediaType())
                )
                .build()

            return builder.create(HafthashtadNetworkApi::class.java)
        }
    }
}
