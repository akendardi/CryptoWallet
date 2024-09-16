// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.parcelize) apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.0"
    kotlin("kapt") version "1.9.20"
    alias(libs.plugins.google) apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
}
