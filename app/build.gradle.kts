import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.teamsprit.palashsetu"
    compileSdk = 35
    buildToolsVersion = "35.0.0"
    val localDebugKeystore = file("../signing/debug.keystore")
    val releaseSigningProperties = Properties().apply {
        val propertiesFile = file("../signing/release-upload.properties")
        if (propertiesFile.exists()) propertiesFile.inputStream().use { load(it) }
    }
    val releaseKeystore = file("../signing/release-upload.keystore")
    signingConfigs {
        if (localDebugKeystore.exists()) {
            create("localDebug") {
                storeFile = localDebugKeystore
                storePassword = "android"
                keyAlias = "androiddebugkey"
                keyPassword = "android"
            }
        }
        if (releaseKeystore.exists() && releaseSigningProperties.isNotEmpty()) {
            create("playUpload") {
                storeFile = releaseKeystore
                storePassword = releaseSigningProperties.getProperty("storePassword")
                keyAlias = releaseSigningProperties.getProperty("keyAlias")
                keyPassword = releaseSigningProperties.getProperty("keyPassword")
            }
        }
    }
    buildTypes {
        getByName("debug") {
            if (localDebugKeystore.exists()) signingConfig = signingConfigs.getByName("localDebug")
        }
        getByName("release") {
            if (releaseKeystore.exists() && releaseSigningProperties.isNotEmpty()) signingConfig = signingConfigs.getByName("playUpload")
            isMinifyEnabled = false
        }
    }
    defaultConfig {
        applicationId = "com.teamsprit.palashsetu"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
    buildFeatures { compose = true }
    packaging { resources.excludes += "/META-INF/{AL2.0,LGPL2.1}" }
}

dependencies {
    val bom = platform("androidx.compose:compose-bom:2024.12.01")
    implementation(bom)
    androidTestImplementation(bom)
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.activity:activity-compose:1.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.7")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    debugImplementation("androidx.compose.ui:ui-tooling")
}
