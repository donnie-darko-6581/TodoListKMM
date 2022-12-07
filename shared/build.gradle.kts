plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.7.10"
    id("com.android.library")
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {

        val ktorVersion = "2.1.2"
        val koin_version= "3.2.2"
        val napierVersion = "2.6.1"
        val mockkVersion = "1.13.3"

        val commonMain by getting {

            repositories {
                mavenCentral()
            }

            dependencies {
                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4-native-mt")

                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")

                // di
                implementation("io.insert-koin:koin-core:$koin_version")

                // logger
                implementation("io.github.aakira:napier:$napierVersion")

                // arch
                api("io.github.fededri.arch:shared:0.5")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
            }
        }
        val androidMain by getting {

            val lifecycleVersion = "2.2.0"

            dependencies {
                repositories {
                    google()
                    mavenCentral()
                }
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
                implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")

                implementation("io.ktor:ktor-client-logging-jvm:$ktorVersion")

            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
                implementation("io.github.fededri.arch:shared-ios:0.5")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.example.kmmlist"
    compileSdk = 32
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}