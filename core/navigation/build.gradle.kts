import org.sopt.play.setNamespace

plugins {
    alias(libs.plugins.sopt.android.library)
    alias(libs.plugins.kotlin.serialization)
}

setNamespace("core.navigation")

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
