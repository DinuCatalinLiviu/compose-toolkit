package com.yourorg.toolkit.core.theme

import androidx.compose.ui.graphics.Color

/**
 * Design token constants for the toolkit's color system.
 *
 * Defaults are intentionally neutral and unbranded — consuming apps should
 * supply their own [ToolkitColorScheme] via [ToolkitTheme] to apply brand colors.
 */
internal object ToolkitColorTokens {

    // Primary role (neutral black by default; override with brand primary)
    val primary = Color(0xFF000000)
    val onPrimary = Color(0xFFFFFFFF)
    val primaryContainer = Color(0xFFE0E0E0)
    val onPrimaryContainer = Color(0xFF1A1A1A)

    // Secondary role
    val secondary = Color(0xFF424242)
    val onSecondary = Color(0xFFFFFFFF)
    val secondaryContainer = Color(0xFFF5F5F5)
    val onSecondaryContainer = Color(0xFF212121)

    // Background / Surface
    val background = Color(0xFFFFFFFF)
    val onBackground = Color(0xFF1A1A1A)
    val surface = Color(0xFFFFFFFF)
    val onSurface = Color(0xFF1A1A1A)
    val surfaceVariant = Color(0xFFF5F5F5)
    val onSurfaceVariant = Color(0xFF616161)
    val outline = Color(0xFFBDBDBD)

    // Semantic (these may be kept as-is or overridden)
    val success = Color(0xFF1A7A4A)
    val onSuccess = Color(0xFFFFFFFF)
    val warning = Color(0xFFB45300)
    val onWarning = Color(0xFFFFFFFF)
    val error = Color(0xFFCC0000)
    val onError = Color(0xFFFFFFFF)

    // Dark variants
    val primaryDark = Color(0xFFFFFFFF)
    val onPrimaryDark = Color(0xFF000000)
    val primaryContainerDark = Color(0xFF303030)
    val onPrimaryContainerDark = Color(0xFFE0E0E0)
    val secondaryDark = Color(0xFFBDBDBD)
    val onSecondaryDark = Color(0xFF000000)
    val secondaryContainerDark = Color(0xFF212121)
    val onSecondaryContainerDark = Color(0xFFE0E0E0)
    val backgroundDark = Color(0xFF121212)
    val onBackgroundDark = Color(0xFFE0E0E0)
    val surfaceDark = Color(0xFF1E1E1E)
    val onSurfaceDark = Color(0xFFE0E0E0)
    val surfaceVariantDark = Color(0xFF2C2C2C)
    val onSurfaceVariantDark = Color(0xFFBDBDBD)
    val outlineDark = Color(0xFF616161)
}
