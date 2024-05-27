plugins {
    id("hafthashtad.android.library")
    id("hafthashtad.android.library.compose")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    lint {
        checkDependencies = true
    }
    namespace = "com.hafthashtad.android.core.designsystem"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:util"))

    implementation(libs.androidx.core.ktx)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.material)
    debugApi(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)
    api(libs.androidx.compose.runtime)
    implementation(libs.androidx.activity.compose)
    api(libs.androidx.constraintlayout.compose)
    implementation(libs.accompanist.swiperefresh)
    api(libs.accompanist.pager)
    api(libs.accompanist.navigation.animation)
    api(libs.accompanist.permission)
    api(libs.swipe)
    api(libs.coil.kt)
    api(libs.coil.kt.svg)
    api(libs.coil.video)
    api(libs.coil.gif)
    api(libs.coil.kt.compose)
    api(libs.lottie.compose)
}