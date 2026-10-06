import org.sopt.play.libs

plugins {
    id("sopt.android.library")
    id("sopt.android.compose")
}

dependencies {
    add("implementation", libs.findLibrary("androidx-core-ktx").get())
    add("implementation", libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
    add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
    add("implementation", libs.findLibrary("androidx-navigation-compose").get())
    add("implementation", libs.findLibrary("coroutines-android").get())

    add("implementation", project(":core:designsystem"))
    add("implementation", project(":core:ui"))
}
