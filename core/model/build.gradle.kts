@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("hafthashtad.android.library")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
    kotlin("kapt")
}

android {
    namespace = "com.hafthashtad.android.core.model"
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.documentfile)
}
