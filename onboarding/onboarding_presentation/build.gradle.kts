plugins {
    id("com.android.library")
}

apply {
    from("$rootDir/scripts/presentation-module.gradle")
}

android {
    namespace = "com.jhbb.onboarding_presentation"
}

dependencies {
    "implementation"(project(Modules.onboardingDomain))
    "implementation"(project(Modules.onboardingData))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.coreDomain))
}