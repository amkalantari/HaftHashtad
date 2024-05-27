plugins {
    id("hafthashtad.android.feature")
    id("hafthashtad.android.library.compose")
}

android {
    namespace = "com.hafthashtad.android.feature.home"
}

dependencies {
    implementation(libs.kotlinx.datetime)
    implementation(libs.androidx.paging.compose)
    testImplementation(project(":core:common"))
}
