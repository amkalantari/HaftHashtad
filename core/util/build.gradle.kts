plugins {
    id("hafthashtad.android.library")
}

android {
    namespace = "com.hafthashtad.android.core.util"
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.kotlinx.datetime)
    implementation(libs.persianDate)
    api(libs.androidx.documentfile)

    api(platform(libs.firebase.bom))
    api(libs.firebase.analytics)
    api(libs.firebase.crashlytics)
    api(libs.firebase.messaging)
    api(libs.firebase.config)
    api(libs.google.auth)

    api(libs.androidx.paging.common.ktx)
    api(libs.androidx.paging.runtime)
    api(libs.androidx.paging.compose)

    api(libs.material)
}