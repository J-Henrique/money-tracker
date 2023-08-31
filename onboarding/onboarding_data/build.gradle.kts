plugins {
    id("com.android.library")
}

apply {
    from("$rootDir/scripts/base-module.gradle")
}

android {
    namespace = "com.jhbb.onboarding_data"
}

dependencies {
    "implementation"(project(Modules.coreDomain))
    "implementation"(project(Modules.coreData))
    "implementation"(project(Modules.onboardingDomain))
}