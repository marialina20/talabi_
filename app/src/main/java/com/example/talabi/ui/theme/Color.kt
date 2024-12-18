package com.example.talabi.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.core.content.pm.ShortcutInfoCompat.Surface

val orange = Color(0xfFfE8C00)
val blue = Color(0xFF333A73)
val gray = Color(0xFF3F3D56)
val white= Color.White
val gray2= Color(0xfff7f6f6)

@Immutable
data class AppColors(
   val background:Color,
   val onBackground:Color,
    val surface:Color,
   val onSurface:Color,
    val secondarySurface:Color,
    val onSecondarySurface: Color,
    val regularSurface:Color,
    val onRegularSurface:Color,
    val actionSurface:Color,
    val highlightSurface: Color,
    val onHighlightSurface:Color
)
val LocalAppColors= staticCompositionLocalOf {
    AppColors(
        background=Color.Unspecified,
     onBackground=Color.Unspecified,
     surface=Color.Unspecified,
        onSurface=Color.Unspecified,
        secondarySurface=Color.Unspecified,
     onSecondarySurface=Color.Unspecified,
     regularSurface=Color.Unspecified,
     onRegularSurface=Color.Unspecified,
     actionSurface=Color.Unspecified,
     highlightSurface=Color.Unspecified,
     onHighlightSurface=Color.Unspecified
    )
}
val extendedColors =AppColors(
    background=Color.White,
    onBackground=orange,
    surface=Color.White,
    onSurface= orange,
    secondarySurface= orange,
    onSecondarySurface= blue,
    regularSurface=Color.White,
    onRegularSurface= gray,
    actionSurface=orange,
    highlightSurface= blue,
    onHighlightSurface=Color.White
)

