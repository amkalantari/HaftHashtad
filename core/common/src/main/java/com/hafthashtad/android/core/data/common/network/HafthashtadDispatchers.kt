package com.hafthashtad.android.core.data.common.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val hafthashtadDispatcher: HafthashtadDispatchers)

enum class HafthashtadDispatchers {
    IO
}
