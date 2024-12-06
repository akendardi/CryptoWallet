plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.parcelize)
    id("com.google.gms.google-services")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.akendardi.cryptowallet"

    compileSdk = 34

    defaultConfig {
        applicationId = "com.akendardi.cryptowallet"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}


fun DependencyHandlerScope.retrofitDependencies() {
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gsonConverter)
}

fun DependencyHandlerScope.daggerDependencies() {
    implementation(libs.dagger.core)
    ksp(libs.dagger.compiler)
}

fun DependencyHandlerScope.hiltDependencies() {
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
}

fun DependencyHandlerScope.androidxDependencies() {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.storage)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.animation)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    ksp(libs.androidx.lifecycle.compiler)
}

fun DependencyHandlerScope.testDependencies() {
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)

    testImplementation("com.google.truth:truth:1.4.4")

    testImplementation("org.junit.jupiter:junit-jupiter-api: 4.11.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine: 4.11.1")

    testImplementation("org.mockito:mockito-junit-jupiter:4.0.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
    testImplementation("org.mockito:mockito-inline:3.11.2")
    testImplementation ("com.squareup.okhttp3:mockwebserver:4.9.0")


    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2")

}

fun DependencyHandlerScope.debugImplementation() {
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

fun DependencyHandlerScope.glideDependencies() {
    implementation(libs.glide.compose)
}

fun DependencyHandlerScope.roomDependencies() {
    implementation(libs.room.core)
    ksp(libs.room.compiler)
}

fun DependencyHandlerScope.firebaseDependencies() {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.storage.ktx)
    implementation(libs.firebase.database)

}

fun DependencyHandlerScope.accompanistDependencies() {
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.navigation.animation)
}

fun DependencyHandlerScope.coilDependencies() {
    implementation(libs.coil.compose)
}

fun DependencyHandlerScope.materialDependencies() {
    implementation(libs.material3)
    implementation(libs.icons)
}

fun DependencyHandlerScope.googleDependencies() {
    implementation("com.google.accompanist:accompanist-swiperefresh:0.36.0")
    implementation ("com.google.accompanist:accompanist-navigation-animation:0.36.0")


}

dependencies {


    retrofitDependencies()
    testDependencies()
    androidxDependencies()
    daggerDependencies()
    hiltDependencies()
    debugImplementation()
    glideDependencies()
    roomDependencies()
    firebaseDependencies()
    accompanistDependencies()
    coilDependencies()
    materialDependencies()
    googleDependencies()
    implementation ("androidx.compose.foundation:foundation:1.7.5")
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.5.3")



}