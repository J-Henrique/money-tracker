apply {
    from("$rootDir/scripts/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreDomain))

    "kapt"(Room.roomCompiler)
    "implementation"(Room.roomKtx)
    "implementation"(Room.roomRuntime)
}