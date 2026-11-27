import org.sopt.play.setNamespace

plugins {
    alias(libs.plugins.sopt.android.library)
    alias(libs.plugins.kotlin.serialization)
}

setNamespace("core.data")

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
    implementation(project(":core:datastore"))
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
}
