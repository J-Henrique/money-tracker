apply {
    from("$rootDir/scripts/presentation-module.gradle")
}

dependencies {
    "implementation"(project(Modules.onboardingDomain))
    "implementation"(project(Modules.designSystem))
}