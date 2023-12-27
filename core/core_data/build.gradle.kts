plugins {
    id("com.android.library")
}

apply {
    from("$rootDir/scripts/base-module.gradle")
}

android {
    namespace = "com.jhbb.core_data"

    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                compilerArgumentProviders(
                    RoomSchemaArgProvider(File(projectDir, "schemas"))
                )
            }
        }
    }
}

dependencies {
    "implementation"(project(Modules.coreDomain))

    "kapt"(libs.room.compiler)
    "implementation"(libs.room.ktx)
    "implementation"(libs.room.runtime)
}

class RoomSchemaArgProvider(
    @get:InputDirectory
    @get:PathSensitive(PathSensitivity.RELATIVE)
    val schemaDir: File
) : CommandLineArgumentProvider {
    override fun asArguments(): Iterable<String> {
        return listOf("-Aroom.schemaLocation=${schemaDir.path}")
    }
}