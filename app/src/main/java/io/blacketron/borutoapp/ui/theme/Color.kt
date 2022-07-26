package io.blacketron.borutoapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

//val LightGray = Color(0xFFD8D8D8)
val LightGray = Color(0xFFA4A4A4)
val DarkGray = Color(0xFF2A2A2A)

val splashScreenBackgroundColor
    @Composable
    get() = if (isSystemInDarkTheme())
        Brush.verticalGradient(listOf(Color.Black, Color.Black))
    else Brush.verticalGradient(listOf(Purple700, Purple500))

val welcomeScreenBackgroundColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else Color.White

val welcomeScreenTitleColor
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray else Color.Black.copy(alpha = 0.8f)

val welcomeScreenDescriptionColor
    @Composable
    get() = if (isSystemInDarkTheme()) LightGray.copy(alpha = 0.5f) else Color.Black.copy(alpha = 0.5f)

val activeIndicatorColor
    @Composable
    get() = if (isSystemInDarkTheme()) Purple500 else Purple700

val inactiveIndicatorColor
    @Composable
    get() = if (isSystemInDarkTheme()) DarkGray else LightGray

val finishButtonBackgroundColor
    @Composable
    get() = if (isSystemInDarkTheme()) Purple500 else Purple700