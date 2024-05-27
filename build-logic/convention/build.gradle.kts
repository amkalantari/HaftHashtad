plugins {
    `kotlin-dsl`
}

group = "com.hafthashtad.android.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "hafthashtad.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "hafthashtad.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "hafthashtad.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "hafthashtad.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "hafthashtad.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidHilt") {
            id = "hafthashtad.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("secrets") {
            id = "hafthashtad.secrets"
            implementationClass = "secret.SecretsPlugin"
        }
        register("firebase-perf") {
            id = "hafthashtad.firebase-perf"
            implementationClass = "FirebasePerfConventionPlugin"
        }
    }
}
