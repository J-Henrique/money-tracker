apply {
    from("$rootDir/scripts/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreDomain))
    "implementation"(project(Modules.coreData))
    "implementation"(project(Modules.onboardingDomain))
}