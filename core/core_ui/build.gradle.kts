apply {
    from("$rootDir/scripts/presentation-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreDomain))

    "implementation"(Lottie.lottie)
}