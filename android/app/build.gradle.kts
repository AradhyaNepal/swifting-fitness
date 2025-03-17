plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("dagger.hilt.android.plugin") // Apply the Hilt plugin
    kotlin("kapt") // Kotlin annotation processing
}

android {
    namespace = "com.a2.swifting_fitness"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.a2.swifting_fitness"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Navigation and Datastore dependencies
    implementation("androidx.navigation:navigation-compose:2.8.9")
    implementation("androidx.datastore:datastore-preferences:1.1.3")
    implementation("androidx.datastore:datastore-core:1.1.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")

    // Hilt dependencies
    implementation("com.google.dagger:hilt-android:2.42") // Updated to stable version
    kapt("com.google.dagger:hilt-compiler:2.42") // Use Hilt compiler

    // Room dependencies
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    // Hilt Navigation for Compose
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Additional dependencies
    implementation(libs.foundation.pager)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Test and debug dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
