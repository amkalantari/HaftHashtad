package com.hafthashtad.android

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import coil.request.CachePolicy
import dagger.hilt.android.HiltAndroidApp

/**
 * [Application] class for Hafthashtad
 */
@HiltAndroidApp
class HafthashtadApplication : Application(), ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                add(SvgDecoder.Factory())
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .networkCachePolicy(CachePolicy.ENABLED)
            .respectCacheHeaders(false)
            .placeholder(R.mipmap.app_icon_round)
            .error(R.mipmap.app_icon_round)
            .build()
    }
}
