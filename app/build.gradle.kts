plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 35 //
    namespace = "ru.egorrublev.kubelab5"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    defaultConfig {
        applicationId = "ru.egorrublev.kubelab5"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }


    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}
kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(17))
    }
}
dependencies {
    implementation("androidx.core:core-ktx:1.15.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    testImplementation("junit:junit:4.13.2")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.activity:activity-ktx:1.7.2")
    implementation("com.google.android.material:material:1.9.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.0")
}