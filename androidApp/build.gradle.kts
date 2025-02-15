plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.example.kmmlist.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.example.kmmlist.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {

    val koin_version= "3.2.2"

    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.1.1")
    implementation("androidx.compose.ui:ui-tooling:1.1.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.1")
    implementation("androidx.compose.foundation:foundation:1.2.1")
    implementation("androidx.compose.material:material:1.2.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.0")
    implementation("io.insert-koin:koin-android:$koin_version")
}