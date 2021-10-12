import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
}

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        else -> ::iosX64
    }

    iosTarget("ios") {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                //COROUTINES
                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:${findProperty("version.coroutines")}")

                //NETWORK
                implementation("io.ktor:ktor-client-core:${findProperty("version.ktor")}")
                implementation("io.ktor:ktor-client-logging:${findProperty("version.ktor")}")
                implementation("io.ktor:ktor-client-json:${findProperty("version.ktor")}")
                implementation("io.ktor:ktor-client-serialization:${findProperty("version.ktor")}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:${findProperty("version.serialization")}")

                //DI
                api("io.insert-koin:koin-core:${findProperty("version.koin")}")
                api("io.insert-koin:koin-test:${findProperty("version.koin")}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))

                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
                implementation ("com.google.truth:truth:1.0.1")

                //TESTING
                implementation("org.assertj:assertj-core:${findProperty("version.assertj")}")
                implementation("org.mockito.kotlin:mockito-kotlin:${findProperty("version.mockito")}")
            }
        }
        val androidMain by getting {
            dependencies {
                //COROUTINES
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${findProperty("version.coroutines")}")

                //NETWORK
                implementation("io.ktor:ktor-client-android:${findProperty("version.ktor")}")
                implementation("io.ktor:ktor-client-okhttp:${findProperty("version.ktor")}")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:${findProperty("version.ktor")}")
            }
        }
        val iosTest by getting
    }
}

android {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(31)
    }
}
dependencies {
    testImplementation("junit:junit:4.12")
}
