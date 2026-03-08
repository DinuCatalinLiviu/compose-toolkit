package com.yourorg.toolkit.core.theme

import androidx.compose.ui.graphics.Color

/**
 * Design token interface for the toolkit's color system.
 *
 * Implement this interface to define your brand's light and dark color tokens
 * once, then pass the instance to [ToolkitTheme] for automatic light/dark switching.
 *
 * Defaults are intentionally neutral and unbranded — consuming apps should
 * supply their own implementation to apply brand colors.
 */
interface ToolkitColorTokens {

    // Light mode – Primary role
    val primary: Color
    val onPrimary: Color
    val primaryContainer: Color
    val onPrimaryContainer: Color

    // Light mode – Secondary role
    val secondary: Color
    val onSecondary: Color
    val secondaryContainer: Color
    val onSecondaryContainer: Color

    // Light mode – Background / Surface
    val background: Color
    val onBackground: Color
    val surface: Color
    val onSurface: Color
    val surfaceVariant: Color
    val onSurfaceVariant: Color
    val outline: Color

    // Semantic
    val success: Color
    val onSuccess: Color
    val warning: Color
    val onWarning: Color
    val error: Color
    val onError: Color

    // Dark mode – Primary role
    val primaryDark: Color
    val onPrimaryDark: Color
    val primaryContainerDark: Color
    val onPrimaryContainerDark: Color

    // Dark mode – Secondary role
    val secondaryDark: Color
    val onSecondaryDark: Color
    val secondaryContainerDark: Color
    val onSecondaryContainerDark: Color

    // Dark mode – Background / Surface
    val backgroundDark: Color
    val onBackgroundDark: Color
    val surfaceDark: Color
    val onSurfaceDark: Color
    val surfaceVariantDark: Color
    val onSurfaceVariantDark: Color
    val outlineDark: Color

    companion object {
        /** Neutral unbranded defaults. */
        fun default(): ToolkitColorTokens = DefaultToolkitColorTokens
    }
}

internal object DefaultToolkitColorTokens : ToolkitColorTokens {

    // Primary role (neutral black by default; override with brand primary)
    override val primary = Color(0xFF000000)
    override val onPrimary = Color(0xFFFFFFFF)
    override val primaryContainer = Color(0xFFE0E0E0)
    override val onPrimaryContainer = Color(0xFF1A1A1A)

    // Secondary role
    override val secondary = Color(0xFF424242)
    override val onSecondary = Color(0xFFFFFFFF)
    override val secondaryContainer = Color(0xFFF5F5F5)
    override val onSecondaryContainer = Color(0xFF212121)

    // Background / Surface
    override val background = Color(0xFFFFFFFF)
    override val onBackground = Color(0xFF1A1A1A)
    override val surface = Color(0xFFFFFFFF)
    override val onSurface = Color(0xFF1A1A1A)
    override val surfaceVariant = Color(0xFFF5F5F5)
    override val onSurfaceVariant = Color(0xFF616161)
    override val outline = Color(0xFFBDBDBD)

    // Semantic (these may be kept as-is or overridden)
    override val success = Color(0xFF1A7A4A)
    override val onSuccess = Color(0xFFFFFFFF)
    override val warning = Color(0xFFB45300)
    override val onWarning = Color(0xFFFFFFFF)
    override val error = Color(0xFFCC0000)
    override val onError = Color(0xFFFFFFFF)

    // Dark variants
    override val primaryDark = Color(0xFFFFFFFF)
    override val onPrimaryDark = Color(0xFF000000)
    override val primaryContainerDark = Color(0xFF303030)
    override val onPrimaryContainerDark = Color(0xFFE0E0E0)
    override val secondaryDark = Color(0xFFBDBDBD)
    override val onSecondaryDark = Color(0xFF000000)
    override val secondaryContainerDark = Color(0xFF212121)
    override val onSecondaryContainerDark = Color(0xFFE0E0E0)
    override val backgroundDark = Color(0xFF121212)
    override val onBackgroundDark = Color(0xFFE0E0E0)
    override val surfaceDark = Color(0xFF1E1E1E)
    override val onSurfaceDark = Color(0xFFE0E0E0)
    override val surfaceVariantDark = Color(0xFF2C2C2C)
    override val onSurfaceVariantDark = Color(0xFFBDBDBD)
    override val outlineDark = Color(0xFF616161)
}
