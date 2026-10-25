import org.sopt.play.setNamespace

plugins {
    alias(libs.plugins.sopt.android.library)
    alias(libs.plugins.kotlin.serialization)
}

setNamespace("core.datastore")

dependencies {
    implementation(libs.androidx.datastore)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
}
