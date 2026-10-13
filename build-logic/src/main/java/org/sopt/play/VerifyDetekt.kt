package org.sopt.play

import org.gradle.api.Project

internal fun Project.configureVerifyDetekt() {
    with(pluginManager) {
        apply("io.gitlab.arturbosch.detekt")
    }
}
