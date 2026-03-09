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
        /** Builds a light-theme [ToolkitColorScheme] from the given [tokens]. */
        fun fromTokens(tokens: ToolkitColorTokens): ToolkitColorScheme = ToolkitColorScheme(
            primary = tokens.primary,
            onPrimary = tokens.onPrimary,
            primaryContainer = tokens.primaryContainer,
            onPrimaryContainer = tokens.onPrimaryContainer,
            secondary = tokens.secondary,
            onSecondary = tokens.onSecondary,
            secondaryContainer = tokens.secondaryContainer,
            onSecondaryContainer = tokens.onSecondaryContainer,
            background = tokens.background,
            onBackground = tokens.onBackground,
            surface = tokens.surface,
            onSurface = tokens.onSurface,
            surfaceVariant = tokens.surfaceVariant,
            onSurfaceVariant = tokens.onSurfaceVariant,
            outline = tokens.outline,
            success = tokens.success,
            onSuccess = tokens.onSuccess,
            warning = tokens.warning,
            onWarning = tokens.onWarning,
            error = tokens.error,
            onError = tokens.onError,
        )

        /** Builds a dark-theme [ToolkitColorScheme] from the given [tokens]. */
        fun darkFromTokens(tokens: ToolkitColorTokens): ToolkitColorScheme = ToolkitColorScheme(
            primary = tokens.primaryDark,
            onPrimary = tokens.onPrimaryDark,
            primaryContainer = tokens.primaryContainerDark,
            onPrimaryContainer = tokens.onPrimaryContainerDark,
            secondary = tokens.secondaryDark,
            onSecondary = tokens.onSecondaryDark,
            secondaryContainer = tokens.secondaryContainerDark,
            onSecondaryContainer = tokens.onSecondaryContainerDark,
            background = tokens.backgroundDark,
            onBackground = tokens.onBackgroundDark,
            surface = tokens.surfaceDark,
            onSurface = tokens.onSurfaceDark,
            surfaceVariant = tokens.surfaceVariantDark,
            onSurfaceVariant = tokens.onSurfaceVariantDark,
            outline = tokens.outlineDark,
            success = tokens.success,
            onSuccess = tokens.onSuccess,
            warning = tokens.warning,
            onWarning = tokens.onWarning,
            error = tokens.error,
            onError = tokens.onError,
        )

        /** Neutral light-theme defaults — replace via [ToolkitTheme] in your app. */
        fun default(): ToolkitColorScheme = fromTokens(ToolkitColorTokens.default())

        /** Neutral dark-theme defaults — replace via [ToolkitTheme] in your app. */
        fun dark(): ToolkitColorScheme = darkFromTokens(ToolkitColorTokens.default())
    }
}
