package com.yourorg.toolkit.core.theme

import androidx.compose.ui.graphics.Color

/**
 * Holds the full set of color roles used by toolkit components.
 *
 * Construct a custom instance and pass it to [ToolkitTheme] to apply your brand colors.
 * The [default] and [dark] factory functions return neutral, unbranded schemes that
 * components render correctly with out of the box.
 */
data class ToolkitColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,
    val surfaceVariant: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
    val success: Color,
    val onSuccess: Color,
    val warning: Color,
    val onWarning: Color,
    val error: Color,
    val onError: Color,
) {
    companion object {
        /** Neutral light-theme defaults — replace via [ToolkitTheme] in your app. */
        fun default(): ToolkitColorScheme = ToolkitColorScheme(
            primary = ToolkitColorTokens.primary,
            onPrimary = ToolkitColorTokens.onPrimary,
            primaryContainer = ToolkitColorTokens.primaryContainer,
            onPrimaryContainer = ToolkitColorTokens.onPrimaryContainer,
            secondary = ToolkitColorTokens.secondary,
            onSecondary = ToolkitColorTokens.onSecondary,
            secondaryContainer = ToolkitColorTokens.secondaryContainer,
            onSecondaryContainer = ToolkitColorTokens.onSecondaryContainer,
            background = ToolkitColorTokens.background,
            onBackground = ToolkitColorTokens.onBackground,
            surface = ToolkitColorTokens.surface,
            onSurface = ToolkitColorTokens.onSurface,
            surfaceVariant = ToolkitColorTokens.surfaceVariant,
            onSurfaceVariant = ToolkitColorTokens.onSurfaceVariant,
            outline = ToolkitColorTokens.outline,
            success = ToolkitColorTokens.success,
            onSuccess = ToolkitColorTokens.onSuccess,
            warning = ToolkitColorTokens.warning,
            onWarning = ToolkitColorTokens.onWarning,
            error = ToolkitColorTokens.error,
            onError = ToolkitColorTokens.onError,
        )

        /** Neutral dark-theme defaults — replace via [ToolkitTheme] in your app. */
        fun dark(): ToolkitColorScheme = ToolkitColorScheme(
            primary = ToolkitColorTokens.primaryDark,
            onPrimary = ToolkitColorTokens.onPrimaryDark,
            primaryContainer = ToolkitColorTokens.primaryContainerDark,
            onPrimaryContainer = ToolkitColorTokens.onPrimaryContainerDark,
            secondary = ToolkitColorTokens.secondaryDark,
            onSecondary = ToolkitColorTokens.onSecondaryDark,
            secondaryContainer = ToolkitColorTokens.secondaryContainerDark,
            onSecondaryContainer = ToolkitColorTokens.onSecondaryContainerDark,
            background = ToolkitColorTokens.backgroundDark,
            onBackground = ToolkitColorTokens.onBackgroundDark,
            surface = ToolkitColorTokens.surfaceDark,
            onSurface = ToolkitColorTokens.onSurfaceDark,
            surfaceVariant = ToolkitColorTokens.surfaceVariantDark,
            onSurfaceVariant = ToolkitColorTokens.onSurfaceVariantDark,
            outline = ToolkitColorTokens.outlineDark,
            success = ToolkitColorTokens.success,
            onSuccess = ToolkitColorTokens.onSuccess,
            warning = ToolkitColorTokens.warning,
            onWarning = ToolkitColorTokens.onWarning,
            error = ToolkitColorTokens.error,
            onError = ToolkitColorTokens.onError,
        )
    }
}
