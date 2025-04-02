plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("androidx.room")
    id("com.google.dagger.hilt.android")
}

room {
    schemaDirectory("$projectDir/schemas")
}

android {
    namespace = "edu.ddukk.jetbrew"
    compileSdk = 35



    defaultConfig {
        applicationId = "edu.ddukk.jetbrew"
        minSdk = 25
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

//    AndroidX Core
    implementation(libs.androidx.core.ktx)
//    Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel)
//    Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.navigation.compose)
//    Room
//    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.androidx.runtime.livedata)
    ksp ("androidx.room:room-compiler:2.6.1")
//    Dagger Hilt
//    implementation(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
//    ksp(libs.hilt.android.compiler)
    ksp("com.google.dagger:hilt-android-compiler:2.55")
//    ksp ("com.google.dagger:dagger-compiler:2.55") // Dagger compiler
    ksp ("com.google.dagger:hilt-compiler:2.55")
    ksp ("androidx.hilt:hilt-compiler:1.2.0")

//    implementation(libs.androidx.hilt.lifecycle.viewmodel)
//    implementation(libs.androidx.hilt.navigation.compose)




    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

//kapt {
//    correctErrorTypes = true
//}