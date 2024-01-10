plugins {
    id("com.android.library")
    alias(libs.plugins.junit5)
}

apply {
    from("$rootDir/scripts/base-module.gradle")
}

android {
    namespace = "com.jhbb.tracker_domain"
}

dependencies {
    "implementation"(project(Modules.coreDomain))
}