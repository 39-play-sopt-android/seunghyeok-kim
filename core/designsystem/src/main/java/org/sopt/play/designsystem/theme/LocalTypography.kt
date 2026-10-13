package org.sopt.play.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf

internal val LocalTypography = staticCompositionLocalOf<PlaySoptTypography> {
    error("LocalTypography가 제공되지 않았습니다. PlaySoptTheme을 적용했는지 확인해주세요.")
}