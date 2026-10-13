import org.gradle.api.artifacts.VersionCatalogsExtension

plugins {
    `kotlin-dsl`
}

group = "org.sopt.play.buildlogic"

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies {
    compileOnly(libs.findLibrary("android-gradlePlugin").get())
    compileOnly(libs.findLibrary("kotlin-gradlePlugin").get())
    compileOnly(libs.findLibrary("compose-compiler-gradle-plugin").get())
    compileOnly(libs.findLibrary("verify-detektPlugin").get())
}
