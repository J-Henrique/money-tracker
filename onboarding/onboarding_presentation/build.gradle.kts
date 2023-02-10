apply {
    from("$rootDir/scripts/presentation-module.gradle")
}

dependencies {
    "implementation"(project(Modules.onboardingDomain))
    "implementation"(project(Modules.onboardingData))
    "implementation"(project(Modules.coreUi))
    "implementation"(project(Modules.coreDomain))
}