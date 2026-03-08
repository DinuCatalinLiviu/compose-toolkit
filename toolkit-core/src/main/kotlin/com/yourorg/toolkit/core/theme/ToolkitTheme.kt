package com.yourorg.toolkit.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.font.FontFamily

private val localToolkitColorScheme = staticCompositionLocalOf { ToolkitColorScheme.default() }

/** Retrieves the current [ToolkitColorScheme] from the nearest [ToolkitTheme] ancestor. */
object ToolkitTheme {
    val colorScheme: ToolkitColorScheme
        @Composable @ReadOnlyComposable
        get() = localToolkitColorScheme.current
}

/**
 * Token-based theme wrapper that auto-switches between light and dark schemes.
 *
 * Define your brand tokens once by implementing [ToolkitColorTokens] and pass
 * the instance here. The library builds the appropriate [ToolkitColorScheme]
 * based on [darkTheme].
 *
 * @param colorTokens Brand color tokens. Defaults to neutral, unbranded values.
 * @param fontFamily Font family applied to the Material 3 typography scale.
 * @param darkTheme When `true` the dark variant of the tokens is used.
 * @param content Screen content rendered inside the theme.
 */
@Composable
fun ToolkitTheme(
    colorTokens: ToolkitColorTokens = ToolkitColorTokens.default(),
    fontFamily: FontFamily = FontFamily.Default,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) {
        ToolkitColorScheme.darkFromTokens(colorTokens)
    } else {
        ToolkitColorScheme.fromTokens(colorTokens)
    }
    ToolkitTheme(
        colorScheme = colorScheme,
        fontFamily = fontFamily,
        darkTheme = darkTheme,
        content = content,
    )
}

/**
 * Root theme wrapper for all toolkit components.
 *
 * Wrap your app (or a section of it) with this composable to provide
 * design tokens to every toolkit component in the tree.
 *
 * All parameters have sensible neutral defaults. Consuming apps should
 * supply their own values to apply brand identity.
 *
 * @param colorScheme Color roles for the current theme mode. Use
 *   [ToolkitColorScheme.default] (light) or [ToolkitColorScheme.dark] as starting
 *   points, or construct a fully custom instance.
 * @param fontFamily Font family applied to the Material 3 typography scale.
 *   Defaults to the system font; override to match your brand typeface.
 * @param darkTheme When `true` the [colorScheme] is treated as a dark scheme.
 *   Defaults to the system setting.
 * @param content Screen content rendered inside the theme.
 */
@Composable
fun ToolkitTheme(
    colorScheme: ToolkitColorScheme,
    fontFamily: FontFamily = FontFamily.Default,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    // Map toolkit color scheme to Material 3 so Material components benefit from the tokens too.
    val m3ColorScheme = if (darkTheme) {
        darkColorScheme(
            primary = colorScheme.primary,
            onPrimary = colorScheme.onPrimary,
            primaryContainer = colorScheme.primaryContainer,
            onPrimaryContainer = colorScheme.onPrimaryContainer,
            secondary = colorScheme.secondary,
            onSecondary = colorScheme.onSecondary,
            secondaryContainer = colorScheme.secondaryContainer,
            onSecondaryContainer = colorScheme.onSecondaryContainer,
            background = colorScheme.background,
            onBackground = colorScheme.onBackground,
            surface = colorScheme.surface,
            onSurface = colorScheme.onSurface,
            surfaceVariant = colorScheme.surfaceVariant,
            onSurfaceVariant = colorScheme.onSurfaceVariant,
            outline = colorScheme.outline,
            error = colorScheme.error,
            onError = colorScheme.onError,
        )
    } else {
        lightColorScheme(
            primary = colorScheme.primary,
            onPrimary = colorScheme.onPrimary,
            primaryContainer = colorScheme.primaryContainer,
            onPrimaryContainer = colorScheme.onPrimaryContainer,
            secondary = colorScheme.secondary,
            onSecondary = colorScheme.onSecondary,
            secondaryContainer = colorScheme.secondaryContainer,
            onSecondaryContainer = colorScheme.onSecondaryContainer,
            background = colorScheme.background,
            onBackground = colorScheme.onBackground,
            surface = colorScheme.surface,
            onSurface = colorScheme.onSurface,
            surfaceVariant = colorScheme.surfaceVariant,
            onSurfaceVariant = colorScheme.onSurfaceVariant,
            outline = colorScheme.outline,
            error = colorScheme.error,
            onError = colorScheme.onError,
        )
    }

    CompositionLocalProvider(localToolkitColorScheme provides colorScheme) {
        MaterialTheme(
            colorScheme = m3ColorScheme,
            typography = toolkitTypography(fontFamily),
            shapes = Shapes(), // Material 3 defaults; expose ToolkitShapes later
            content = content,
        )
    }
}
