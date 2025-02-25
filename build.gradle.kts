// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id ("com.google.devtools.ksp") version "2.1.0-1.0.29" apply false
//    id ("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
//    id ("com.google.devtools.ksp") version "1.9.22" apply false
    id("androidx.room") version "2.6.1" apply false
    id("com.google.dagger.hilt.android") version "2.55" apply false
//    id("org.jetbrains.kotlin.jvm") version "1.9.21"
}


//buildscript {
//    dependencies {
//        val hiltVersion by extra("2.54")
//        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
//    }
//}// Top-level build file where you can add configuration options common to all sub-projects/modules.
//plugins {
//    id("com.android.application") version "8.7.3" apply false
//    id("com.android.library") version "8.7.3" apply false
//    id("org.jetbrains.kotlin.android") version "2.1.0" apply false
//    id("com.google.devtools.ksp") version "2.1.0-1.0.29" apply false
//}
//
//tasks.register("clean", Delete::class) {
//    delete(rootProject.layout.buildDirectory)
//}