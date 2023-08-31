plugins {
    id("com.android.library")
}

apply {
    from("$rootDir/scripts/base-module.gradle")
}

android {
    namespace = "com.jhbb.core_data"
}

dependencies {
    "implementation"(project(Modules.coreDomain))

    "kapt"(Room.roomCompiler)
    "implementation"(Room.roomKtx)
    "implementation"(Room.roomRuntime)
}