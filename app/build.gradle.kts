plugins {
    id("hafthashtad.android.application")
    id("hafthashtad.android.application.compose")
    id("hafthashtad.android.hilt")
    id("jacoco")
}

android {

    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        applicationId = "com.hafthashtad.android"
        versionCode = 1
        versionName = "1.0.0" // X.Y.Z; X = Major, Y = minor, Z = Patch level
        setProperty("archivesBaseName", "Hafthashtad-${versionName}")
        testInstrumentationRunner = ".core.testing.HafthashtadTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        val debug by getting {

        }
        val release by getting {
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    bundle {
        language {
            enableSplit = false
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            merges += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE-notice.md"
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    namespace = "com.hafthashtad.android"
}

dependencies {
    implementation(project(":feature:home"))

    implementation(project(":core:ui"))
    implementation(project(":core:designsystem"))

    implementation(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))

    androidTestImplementation(project(":core:network"))

    androidTestImplementation(libs.androidx.navigation.testing)
    debugImplementation(libs.androidx.compose.ui.testManifest)

    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.runtime.tracing)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.window.manager)
    implementation(libs.androidx.profileinstaller)

    implementation(libs.coil.kt)
    implementation(libs.coil.kt.svg)
    implementation(libs.coil.video)

    implementation(libs.app.update)
    implementation(libs.app.update.ktx)
}


configurations.configureEach {
    resolutionStrategy {
        force(libs.junit4)
        force("org.objenesis:objenesis:2.6")
    }
}

tasks.register<Copy>("publish") {
    dependsOn("assembleRelease", "bundleRelease")

    val apkSourceDir = "build/outputs/apk/prod/release/"
    val bundleSourceDir = "build/outputs/bundle/prodRelease/"

    from(apkSourceDir)
    include("*.apk")
    into("/Users/amiir/Desktop")

    from(bundleSourceDir)
    include("*.aab")
    into("/Users/amiir/Desktop")

    doLast {
        fileTree(apkSourceDir) { include("*.apk") }.forEach { it.delete() }
        fileTree(bundleSourceDir) { include("*.aab") }.forEach { it.delete() }
    }
}