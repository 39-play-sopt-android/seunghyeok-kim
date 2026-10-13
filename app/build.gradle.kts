plugins {
    alias(libs.plugins.sopt.android.application)
    alias(libs.plugins.sopt.android.compose)
}

android {
    namespace = "org.sopt.play"

    defaultConfig {
        applicationId = "org.sopt.play"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))
    implementation(project(":feature:auth"))
}
