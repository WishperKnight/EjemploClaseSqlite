plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "ies.carrillo.ejemploclasesqlite"
    compileSdk = 34

    defaultConfig {
        applicationId = "ies.carrillo.ejemploclasesqlite"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation ("com.google.android.material:material:1.9.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.androidx.room.runtime.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // Dependencias de Room
    implementation("androidx.room:room-runtime:2.5.0")
    annotationProcessor ("androidx.room:room-compiler:2.5.0")
    // Dependencia jBCrypt
    implementation ("de.svenkubiak:jBCrypt:0.4.3")


}