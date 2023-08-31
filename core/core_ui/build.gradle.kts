plugins {
    id("com.android.library")
}

apply {
    from("$rootDir/scripts/presentation-module.gradle")
}

android {
    namespace = "com.jhbb.core_ui"
}

dependencies {
    "implementation"(project(Modules.coreDomain))
    "implementation"(Lottie.lottie)
}