plugins {
    id("com.android.library")
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
}