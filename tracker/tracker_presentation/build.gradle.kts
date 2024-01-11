plugins {
    id("com.android.library")
    alias(libs.plugins.junit5)
}

apply {
    from("$rootDir/scripts/presentation-module.gradle")
}

android {
    namespace = "com.jhbb.tracker_presentation"
}

dependencies {
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.coreDomain))
    "implementation"(project(Modules.trackerDomain))
    "implementation"(project(Modules.trackerData))
}