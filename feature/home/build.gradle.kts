plugins {
    id("hafthashtad.android.feature")
    id("hafthashtad.android.library.compose")
}

android {
    namespace = "com.hafthashtad.android.feature.home"
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(project(":core:common"))

    testApi(libs.junit)
    api(libs.io.mockK)
    api(libs.kotlinx.coroutine.test)

    androidTestApi(libs.androidx.test.ext.junit)
    androidTestApi(libs.espresso.core)
    androidTestApi(platform(libs.androidx.compose.bom))
    androidTestApi(libs.ui.test.junit4)

    debugApi(libs.ui.tooling)
    debugApi(libs.ui.test.manifest)
}
