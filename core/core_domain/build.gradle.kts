plugins {
    id("com.android.library")
}

android {
    namespace = "com.jhbb.core_domain"
}

apply {
    from("$rootDir/scripts/base-module.gradle")
}