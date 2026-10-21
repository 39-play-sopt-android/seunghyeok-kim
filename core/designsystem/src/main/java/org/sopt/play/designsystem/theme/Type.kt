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
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_regular, FontWeight.Normal)
)

@Immutable
data class PlaySoptTypography(
    val b28: TextStyle,
    val m18: TextStyle,
    val sb16: TextStyle,
    val m14: TextStyle,
    val sb14: TextStyle,
    val r14: TextStyle
)

val playSoptTypography: PlaySoptTypography
    @Composable
    get() {
        val pretendard = Pretendard
        return PlaySoptTypography(
            b28 = TextStyle(fontFamily = pretendard, fontSize = 28.sp, fontWeight = FontWeight.Bold, lineHeight = 1.2.em, letterSpacing = (-0.01).em, color = PlaySoptColor.black),
            m18 = TextStyle(fontFamily = pretendard, fontSize = 18.sp, fontWeight = FontWeight.Medium, lineHeight = 1.2.em, letterSpacing = (-0.01).em, color = PlaySoptColor.black),
            sb16 = TextStyle(fontFamily = pretendard, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, lineHeight = 1.2.em, letterSpacing = (-0.01).em, color = PlaySoptColor.black),
            m14 = TextStyle(fontFamily = pretendard, fontSize = 14.sp, fontWeight = FontWeight.Medium, lineHeight = 1.2.em, letterSpacing = (-0.01).em, color = PlaySoptColor.black),
            sb14 = TextStyle(fontFamily = pretendard, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, lineHeight = 1.2.em, letterSpacing = (-0.01).em, color = PlaySoptColor.black),
            r14 = TextStyle(fontFamily = pretendard, fontSize = 14.sp, fontWeight = FontWeight.Normal, lineHeight = 1.2.em, letterSpacing = (-0.01).em, color = PlaySoptColor.black)
        )
    }
