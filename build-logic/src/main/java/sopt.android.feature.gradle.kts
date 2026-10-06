import org.sopt.play.libs
import org.gradle.kotlin.dsl.dependencies

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
}
