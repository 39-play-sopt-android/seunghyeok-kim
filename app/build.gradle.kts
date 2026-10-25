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
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    implementation(project(":core:designsystem"))
    implementation(project(":core:navigation"))
    implementation(project(":core:datastore"))
    implementation(project(":core:domain"))

    implementation(project(":feature:auth"))
    implementation(project(":feature:home"))
    implementation(project(":feature:like"))
    implementation(project(":feature:mypage"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.navigation3.runtime)
    implementation(libs.navigation3.ui)
    implementation(libs.navigationevent)
    implementation(libs.navigationevent.compose)
}
