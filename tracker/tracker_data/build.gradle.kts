plugins {
    id("com.android.library")
}

apply {
    from("$rootDir/scripts/base-module.gradle")
}

android {
    namespace = "com.jhbb.tracker_data"
}

dependencies {
    "implementation"(project(Modules.coreDomain))
    "implementation"(project(Modules.coreData))
    "implementation"(project(Modules.trackerDomain))

    "implementation"(libs.retrofit2.retrofit)
    "implementation"(libs.retrofit2.moshiConverter)
    "implementation"(libs.okhttp.loggingInterceptor)
    "implementation"(libs.okhttp)
}