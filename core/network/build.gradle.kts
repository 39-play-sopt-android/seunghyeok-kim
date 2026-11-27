import java.util.Properties
import org.sopt.play.setNamespace

plugins {
    alias(libs.plugins.sopt.android.library)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.buildconfig)
}

setNamespace("core.network")

val localProperties = Properties().apply {
    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        load(localPropertiesFile.inputStream())
    }
}

val baseUrl = localProperties.getProperty("BASE_URL") ?: ""

buildConfig {
    packageName("org.sopt.play.core.network")
    buildConfigField("String", "BASE_URL", baseUrl)
}

dependencies {
    implementation(project(":core:datastore"))
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlinx.serialization.converter)
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging.interceptor)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
}
