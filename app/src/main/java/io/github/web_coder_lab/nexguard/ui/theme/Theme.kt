package io.github.web_coder_lab.nexguard.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import io.github.web_coder_lab.nexguard.domain.model.NexGuardThemeMode

// Theme A: Black
private val BlackColorScheme = darkColorScheme(
    primary = BlackTextPrimary,
    onPrimary = BlackBg,
    primaryContainer = BlackAccentContainer,
    onPrimaryContainer = BlackTextPrimary,
    secondary = BlackTextSecondary,
    onSecondary = BlackBg,
    background = BlackBg,
    onBackground = BlackTextPrimary,
    surface = BlackSurface,
    onSurface = BlackTextPrimary,
    surfaceVariant = BlackSurfaceVariant,
    onSurfaceVariant = BlackTextSecondary,
    outline = BlackBorder
)

// Theme B: White
private val WhiteColorScheme = lightColorScheme(
    primary = WhiteAccent,
    onPrimary = WhiteBg,
    primaryContainer = WhiteAccentContainer,
    onPrimaryContainer = WhiteAccent,
    secondary = WhiteTextSecondary,
    onSecondary = WhiteBg,
    background = WhiteBg,
    onBackground = WhiteTextPrimary,
    surface = WhiteSurface,
    onSurface = WhiteTextPrimary,
    surfaceVariant = WhiteSurfaceVariant,
    onSurfaceVariant = WhiteTextSecondary,
    outline = WhiteBorder
)

// Theme C: Golden & Black
private val GoldenBlackColorScheme = darkColorScheme(
    primary = GoldAccent,
    onPrimary = GoldBlackBg,
    primaryContainer = GoldAccentContainer,
    onPrimaryContainer = GoldAccentLight,
    secondary = GoldAccent,
    onSecondary = GoldBlackBg,
    background = GoldBlackBg,
    onBackground = GoldBlackTextPrimary,
    surface = GoldBlackSurface,
    onSurface = GoldBlackTextPrimary,
    surfaceVariant = GoldBlackSurfaceVariant,
    onSurfaceVariant = GoldBlackTextSecondary,
    outline = GoldBlackBorder
)

// Theme D: Golden & White
private val GoldenWhiteColorScheme = lightColorScheme(
    primary = GoldWhiteAccent,
    onPrimary = GoldWhiteBg,
    primaryContainer = GoldWhiteAccentContainer,
    onPrimaryContainer = GoldWhiteTextPrimary,
    secondary = GoldWhiteAccent,
    onSecondary = GoldWhiteBg,
    background = GoldWhiteBg,
    onBackground = GoldWhiteTextPrimary,
    surface = GoldWhiteSurface,
    onSurface = GoldWhiteTextPrimary,
    surfaceVariant = GoldWhiteSurfaceVariant,
    onSurfaceVariant = GoldWhiteTextSecondary,
    outline = GoldWhiteBorder
)

@Composable
fun NexGuardTheme(
    themeMode: NexGuardThemeMode = NexGuardThemeMode.BLACK,
    content: @Composable () -> Unit
) {
    val colorScheme = when (themeMode) {
        NexGuardThemeMode.BLACK -> BlackColorScheme
        NexGuardThemeMode.WHITE -> WhiteColorScheme
        NexGuardThemeMode.GOLDEN_BLACK -> GoldenBlackColorScheme
        NexGuardThemeMode.GOLDEN_WHITE -> GoldenWhiteColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

// Keep GuardianTheme alias pointing to NexGuardTheme for seamless compatibility
@Composable
fun GuardianTheme(
    themeMode: NexGuardThemeMode = NexGuardThemeMode.BLACK,
    content: @Composable () -> Unit
) {
    NexGuardTheme(themeMode = themeMode, content = content)
}
