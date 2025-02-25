// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id ("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
//    id ("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
//    id ("com.google.devtools.ksp") version "1.9.22" apply false
    id("androidx.room") version "2.6.1" apply false
    id("com.google.dagger.hilt.android") version "2.55" apply false
//    id("org.jetbrains.kotlin.jvm") version "1.9.21"
}