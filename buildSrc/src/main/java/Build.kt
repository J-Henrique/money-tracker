object Build {
    private const val androidBuildToolsVersion = "8.1.0"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"

    private const val desugarJdkLibsVersion = "2.0.0"
    const val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:$desugarJdkLibsVersion"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"

    private const val hiltAndroidGradlePluginVersion = "2.44.2"
    const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$hiltAndroidGradlePluginVersion"
}