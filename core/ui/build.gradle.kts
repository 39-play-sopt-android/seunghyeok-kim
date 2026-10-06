import org.sopt.play.setNamespace

plugins {
    alias(libs.plugins.sopt.android.library)
    alias(libs.plugins.sopt.android.compose)
}

setNamespace("core.ui")

dependencies {
    implementation(project(":core:designsystem"))
}
