plugins {
    id("com.android.library")
    alias(libs.plugins.junit5)
}

android {
    namespace = "com.jhbb.test_helper"
}

apply {
    from("$rootDir/scripts/base-module.gradle")
}

dependencies {
    "debugImplementation"(libs.bundles.test)
}