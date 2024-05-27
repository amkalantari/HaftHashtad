package com.hafthashtad.android.core.network.retrofit.interceptor

import com.hafthashtad.android.core.data.common.qualifer.Qualifiers
import com.hafthashtad.android.core.datastore.HafthashtadPreferencesDataSource
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale
import javax.inject.Inject
import javax.inject.Named

class HafthashtadHeaderInterceptor @Inject constructor(
    @Named(Qualifiers.QUALIFIER_APP_VERSION_NAME) private val appVersionName: String,
    private val hafthashtadPreferencesDataSource: HafthashtadPreferencesDataSource,
    private val mutex: Mutex,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = runBlocking {
        val request = chain.request()
        val builder = request.newBuilder().apply {
            addHeader("X-Client-Version", appVersionName)
            addHeader("X-Client-Name", "ANDROID")
            addHeader("X-Accept-Language", Locale.getDefault().language.uppercase())
            mutex.withLock {
                if (hafthashtadPreferencesDataSource.hasLogin())
                    addHeader(
                        "Authorization",
                        "Bearer "
                    )
            }
        }
        return@runBlocking chain.proceed(builder.build())
    }
}
