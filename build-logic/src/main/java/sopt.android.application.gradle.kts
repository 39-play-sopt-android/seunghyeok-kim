import org.sopt.play.configureAndroidCommon
import org.sopt.play.applicationExtension
import org.sopt.play.libs

plugins {
    id("com.android.application")
}

configureAndroidCommon(applicationExtension)

(extensions.findByName("android") as? com.android.build.api.dsl.ApplicationExtension)?.apply {
    defaultConfig {
        targetSdk = libs.findVersion("android-targetSdk").get().requiredVersion.toInt()
    }
}
