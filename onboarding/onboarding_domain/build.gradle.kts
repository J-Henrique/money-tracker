plugins {
    id("com.android.library")
}

apply {
    from("$rootDir/scripts/base-module.gradle")
}

android {
    namespace = "com.jhbb.onboarding_domain"
}

dependencies {
    "implementation"(project(Modules.coreDomain))
}