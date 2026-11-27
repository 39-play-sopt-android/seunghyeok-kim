package org.sopt.play.core.ui.ext

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemGestures
import androidx.compose.foundation.layout.tappableElement
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Modifier.customInsets(
    top: Boolean = false,
    bottom: Boolean = false,
): Modifier {
    var m = this
    if (top) m = m.windowInsetsPadding(
        WindowInsets.safeDrawing.only(WindowInsetsSides.Top)
            .union(WindowInsets.displayCutout.only(WindowInsetsSides.Top))
            .union(WindowInsets.systemGestures.only(WindowInsetsSides.Top))
    )
    if (bottom) m = m.windowInsetsPadding(
        WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom)
            .union(WindowInsets.tappableElement.only(WindowInsetsSides.Bottom))
            .union(WindowInsets.systemGestures.only(WindowInsetsSides.Bottom))
    )
    return m
}

@Composable
fun Modifier.playSoptDefault(
    color: Color = Color.White,
    topPadding: Boolean = false,
    bottomPadding: Boolean = false
): Modifier {
    return Modifier
        .background(color)
        .fillMaxSize()
        .then(this)
        .customInsets(top = topPadding, bottom = bottomPadding)
}

@Composable
fun Modifier.nullIndication(
    enabled: Boolean = true,
    onClick: () -> Unit
): Modifier = this.clickable(
    interactionSource = remember { MutableInteractionSource() },
    indication = null,
    enabled = enabled
) {
    onClick()
}

@Composable
fun Modifier.singleClickable(
    enabled: Boolean = true,
    delayMillis: Long = 600L,
    onClick: () -> Unit
): Modifier {
    var lastClickTime by remember { mutableLongStateOf(0L) }
    return this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        enabled = enabled
    ) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime >= delayMillis) {
            lastClickTime = currentTime
            onClick()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Modifier.singleCombinedClickable(
    enabled: Boolean = true,
    delayMillis: Long = 600L,
    onLongClick: (() -> Unit)? = null,
    onClick: () -> Unit = {}
): Modifier {
    var lastClickTime by remember { mutableLongStateOf(0L) }
    return this.combinedClickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        enabled = enabled,
        onLongClick = onLongClick,
        onClick = {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime >= delayMillis) {
                lastClickTime = currentTime
                onClick()
            }
        }
    )
}
