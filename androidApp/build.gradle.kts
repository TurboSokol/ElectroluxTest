plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")

    //NAVIGATION
    implementation ("androidx.navigation:navigation-fragment-ktx:${findProperty("version.navigation")}")
    implementation ("androidx.navigation:navigation-ui-ktx:${findProperty("version.navigation")}")

    //COMPOSE
    implementation("androidx.compose.ui:ui:${findProperty("version.compose")}")
    implementation("androidx.compose.material:material:${findProperty("version.compose")}")
    implementation("androidx.compose.ui:ui-tooling-preview:${findProperty("version.compose")}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.3.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${findProperty("version.compose")}")
    debugImplementation("androidx.compose.ui:ui-tooling:${findProperty("version.compose")}")
    implementation("io.coil-kt:coil-compose:1.4.0")

    //DI
    implementation("io.insert-koin:koin-core:${findProperty("version.koin")}")
    implementation("io.insert-koin:koin-test:${findProperty("version.koin")}")
    implementation("io.insert-koin:koin-test-junit4:${findProperty("version.koin")}")
    implementation("io.insert-koin:koin-android:${findProperty("version.koin")}")
    implementation("io.insert-koin:koin-androidx-compose:${findProperty("version.koin")}")

    //TESTS
    // Test rules and transitive dependencies:
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${findProperty("version.compose")}")
    // Needed for createComposeRule, but not createAndroidComposeRule:
    debugImplementation("androidx.compose.ui:ui-test-manifest:${findProperty("version.compose")}")

}

android {
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "com.turbosokol.electroluxtest.android"
        minSdkVersion(24)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = findProperty("version.compose") as String
        kotlinCompilerVersion = "1.5.21"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}