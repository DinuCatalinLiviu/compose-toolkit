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
        fun default() = ToolkitColorScheme(
            primary             = ToolkitColorTokens.Primary,
            onPrimary           = ToolkitColorTokens.OnPrimary,
            primaryContainer    = ToolkitColorTokens.PrimaryContainer,
            onPrimaryContainer  = ToolkitColorTokens.OnPrimaryContainer,
            secondary           = ToolkitColorTokens.Secondary,
            onSecondary         = ToolkitColorTokens.OnSecondary,
            secondaryContainer  = ToolkitColorTokens.SecondaryContainer,
            onSecondaryContainer = ToolkitColorTokens.OnSecondaryContainer,
            background          = ToolkitColorTokens.Background,
            onBackground        = ToolkitColorTokens.OnBackground,
            surface             = ToolkitColorTokens.Surface,
            onSurface           = ToolkitColorTokens.OnSurface,
            surfaceVariant      = ToolkitColorTokens.SurfaceVariant,
            onSurfaceVariant    = ToolkitColorTokens.OnSurfaceVariant,
            outline             = ToolkitColorTokens.Outline,
            success             = ToolkitColorTokens.Success,
            onSuccess           = ToolkitColorTokens.OnSuccess,
            warning             = ToolkitColorTokens.Warning,
            onWarning           = ToolkitColorTokens.OnWarning,
            error               = ToolkitColorTokens.Error,
            onError             = ToolkitColorTokens.OnError,
        )

        /** Neutral dark-theme defaults — replace via [ToolkitTheme] in your app. */
        fun dark() = ToolkitColorScheme(
            primary             = ToolkitColorTokens.PrimaryDark,
            onPrimary           = ToolkitColorTokens.OnPrimaryDark,
            primaryContainer    = ToolkitColorTokens.PrimaryContainerDark,
            onPrimaryContainer  = ToolkitColorTokens.OnPrimaryContainerDark,
            secondary           = ToolkitColorTokens.SecondaryDark,
            onSecondary         = ToolkitColorTokens.OnSecondaryDark,
            secondaryContainer  = ToolkitColorTokens.SecondaryContainerDark,
            onSecondaryContainer = ToolkitColorTokens.OnSecondaryContainerDark,
            background          = ToolkitColorTokens.BackgroundDark,
            onBackground        = ToolkitColorTokens.OnBackgroundDark,
            surface             = ToolkitColorTokens.SurfaceDark,
            onSurface           = ToolkitColorTokens.OnSurfaceDark,
            surfaceVariant      = ToolkitColorTokens.SurfaceVariantDark,
            onSurfaceVariant    = ToolkitColorTokens.OnSurfaceVariantDark,
            outline             = ToolkitColorTokens.OutlineDark,
            success             = ToolkitColorTokens.Success,
            onSuccess           = ToolkitColorTokens.OnSuccess,
            warning             = ToolkitColorTokens.Warning,
            onWarning           = ToolkitColorTokens.OnWarning,
            error               = ToolkitColorTokens.Error,
            onError             = ToolkitColorTokens.OnError,
        )
    }
}
