plugins {
    id("com.android.application")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = ProjectConfig.appId
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(Modules.onboardingPresentation))
    implementation(project(Modules.trackerPresentation))
    implementation(project(Modules.coreUi))

    implementation(libs.androidx.coreKtx)
    implementation(libs.androidx.appcompat)

    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    implementation(libs.compose.ui)
    implementation(libs.compose.material)
    implementation(libs.compose.toolingPreview)
    implementation(libs.compose.activity)
    implementation(libs.compose.lifecycle.viewmodel)
    implementation(libs.compose.navigation)
    implementation(libs.compose.runtime)

    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hiltCompiler)

    debugImplementation(libs.compose.test.manifest)
    debugImplementation(libs.compose.tooling)

    testImplementation(libs.junit.junit4)
    testImplementation(libs.kotlin.test.coroutines)

    androidTestImplementation(composeBom)
    androidTestImplementation(libs.compose.test.junit4)
}