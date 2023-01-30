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
        javaCompileOptions {
            annotationProcessorOptions {
                compilerArgumentProviders(
                    RoomSchemaArgProvider(File(projectDir, "schemas"))
                )
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles("proguard-android.txt", "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeCompilerVersion
    }
    packagingOptions {
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
    implementation(project(Modules.coreUi))

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)

    val composeBom = platform(Compose.composeBom)
    implementation(composeBom)
    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.toolingPreview)
    implementation(Compose.activityCompose)
    implementation(Compose.viewModelCompose)
    implementation(Compose.navigation)
    implementation(Compose.runtime)

    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltCompiler)



    debugImplementation(Testing.composeUiTestManifest)
    debugImplementation(Compose.tooling)

    testImplementation(Testing.junit4)
    testImplementation(Testing.junitAndroidExt)
    testImplementation(Testing.coroutines)

    androidTestImplementation(composeBom)
    androidTestImplementation(Testing.composeJunit)
    androidTestImplementation(Testing.junitAndroidExt)
    androidTestImplementation(Testing.testRunner)
}

class RoomSchemaArgProvider(
    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    val schemaDir: File
) : CommandLineArgumentProvider {
    override fun asArguments(): Iterable<String> {
        return listOf("-Aroom.schemaLocation=${schemaDir.path}")
    }
}