package org.sopt.play.designsystem.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


interface PlaySoptColors{
    val black: Color
    val gray1: Color
    val gray2: Color
    val gray3: Color
    val gray5: Color
    val gray6: Color
    val red: Color
    val white: Color
}

@Stable
object PlaySoptColor : PlaySoptColors{
    override val black = Color(0xFF121212)
    override val gray1 = Color(0xFFF7F7F7)
    override val gray2 = Color(0xFFD1D5D6)
    override val gray3 = Color(0xFFB2BABD)
    override val gray5 = Color(0xFF505559)
    override val gray6 = Color(0xFF23272A)
    override val red = Color(0xFFFF4D4D)
    override val white = Color(0xFFFFFFFF)
}