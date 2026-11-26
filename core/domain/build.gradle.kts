import org.sopt.play.setNamespace

plugins {
    alias(libs.plugins.sopt.android.library)
}

setNamespace("core.domain")

dependencies {
    implementation(project(":core:datastore"))
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
}
