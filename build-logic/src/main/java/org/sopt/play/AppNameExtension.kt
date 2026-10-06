package org.sopt.play

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

fun Project.setNamespace(name: String) {
    val formattedName = name.removePrefix(":").replace(":", ".").replace("-", ".")
    val namespaceValue = if (formattedName.isEmpty()) "org.sopt.play" else "org.sopt.play.$formattedName"
    (extensions.findByName("android") as? CommonExtension)?.namespace = namespaceValue
}

fun Project.setAutoNamespace() {
    val pathName = project.path.removePrefix(":").replace(":", ".").replace("-", ".")
    setNamespace(pathName)
}
