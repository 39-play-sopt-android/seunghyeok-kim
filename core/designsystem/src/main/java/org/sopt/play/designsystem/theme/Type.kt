package org.sopt.play.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.sopt.play.core.designsystem.R

val Pretendard: FontFamily = FontFamily(
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_bold, FontWeight.Bold)
)

@Immutable
data class PlaySoptTypography(
    val b28: TextStyle,
    val m18: TextStyle,
    val sb16: TextStyle,
    val m14: TextStyle,
    val sb14: TextStyle
)

val playSoptTypography: PlaySoptTypography
    @Composable
    get() {
        val pretendard = Pretendard
        return PlaySoptTypography(
            b28 = TextStyle(fontFamily = pretendard, fontSize = 28.sp, fontWeight = FontWeight.Bold, lineHeight = (28 * 1.2).sp, letterSpacing = (-0.01).em),
            m18 = TextStyle(fontFamily = pretendard, fontSize = 18.sp, fontWeight = FontWeight.Medium, lineHeight = (18 * 1.2).sp, letterSpacing = (-0.01).em),
            sb16 = TextStyle(fontFamily = pretendard, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, lineHeight = (16 * 1.2).sp, letterSpacing = (-0.01).em),
            m14 = TextStyle(fontFamily = pretendard, fontSize = 14.sp, fontWeight = FontWeight.Medium, lineHeight = (14 * 1.2).sp, letterSpacing = (-0.01).em),
            sb14 = TextStyle(fontFamily = pretendard, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, lineHeight = (14 * 1.2).sp, letterSpacing = (-0.01).em)
        )
    }
