plugins {
    id("hafthashtad.android.library")
    id("hafthashtad.android.hilt")
    id("kotlinx-serialization")
    id("hafthashtad.secrets")
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.hafthashtad.android.core.network"
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:util"))
    implementation(project(":core:model"))
    implementation(project(":core:datastore"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)

    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.kotlinx.serialization.json)
}
