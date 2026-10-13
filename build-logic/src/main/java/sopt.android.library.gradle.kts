import org.sopt.play.configureAndroidCommon
import org.sopt.play.libraryExtension
import org.sopt.play.setAutoNamespace

plugins {
    id("com.android.library")
}

configureAndroidCommon(libraryExtension)
setAutoNamespace()
