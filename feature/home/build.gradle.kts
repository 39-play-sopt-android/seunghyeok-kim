import org.sopt.play.setNamespace

plugins {
    alias(libs.plugins.sopt.android.feature)
}

setNamespace("feature.home")

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:datastore"))
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
}
