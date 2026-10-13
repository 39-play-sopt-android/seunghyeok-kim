package org.sopt.play.core.ui.ext

import android.util.Patterns

fun String?.isValidEmail(): Boolean {
    return !this.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
