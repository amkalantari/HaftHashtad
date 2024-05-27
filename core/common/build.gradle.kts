plugins {
    id("hafthashtad.android.library")
    id("hafthashtad.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.hafthashtad.android.core.data.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
}