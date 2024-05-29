plugins {
    id("hafthashtad.android.library")
    id("hafthashtad.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.hafthashtad.android.core.data"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    api(project(":core:datastore"))
    implementation(project(":core:network"))
    implementation(project(":core:util"))

    implementation(libs.androidx.documentfile)
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
}
