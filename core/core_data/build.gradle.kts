apply {
    from("$rootDir/scripts/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreDomain))
}