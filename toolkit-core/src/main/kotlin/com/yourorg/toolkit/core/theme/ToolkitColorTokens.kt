package com.yourorg.toolkit.core.theme

import androidx.compose.ui.graphics.Color

/**
 * Design token constants for the toolkit's color system.
 *
 * Defaults are intentionally neutral and unbranded — consuming apps should
 * supply their own [ToolkitColorScheme] via [ToolkitTheme] to apply brand colors.
 */
internal object ToolkitColorTokens {

    // --- Primary role (neutral black by default; override with brand primary) ---
    val Primary          = Color(0xFF000000)
    val OnPrimary        = Color(0xFFFFFFFF)
    val PrimaryContainer = Color(0xFFE0E0E0)
    val OnPrimaryContainer = Color(0xFF1A1A1A)

    // --- Secondary role ---
    val Secondary          = Color(0xFF424242)
    val OnSecondary        = Color(0xFFFFFFFF)
    val SecondaryContainer = Color(0xFFF5F5F5)
    val OnSecondaryContainer = Color(0xFF212121)

    // --- Background / Surface ---
    val Background    = Color(0xFFFFFFFF)
    val OnBackground  = Color(0xFF1A1A1A)
    val Surface       = Color(0xFFFFFFFF)
    val OnSurface     = Color(0xFF1A1A1A)
    val SurfaceVariant   = Color(0xFFF5F5F5)
    val OnSurfaceVariant = Color(0xFF616161)
    val Outline          = Color(0xFFBDBDBD)

    // --- Semantic (these may be kept as-is or overridden) ---
    val Success   = Color(0xFF1A7A4A)
    val OnSuccess = Color(0xFFFFFFFF)
    val Warning   = Color(0xFFB45300)
    val OnWarning = Color(0xFFFFFFFF)
    val Error     = Color(0xFFCC0000)
    val OnError   = Color(0xFFFFFFFF)

    // --- Dark variants ---
    val PrimaryDark          = Color(0xFFFFFFFF)
    val OnPrimaryDark        = Color(0xFF000000)
    val PrimaryContainerDark = Color(0xFF303030)
    val OnPrimaryContainerDark = Color(0xFFE0E0E0)
    val SecondaryDark          = Color(0xFFBDBDBD)
    val OnSecondaryDark        = Color(0xFF000000)
    val SecondaryContainerDark = Color(0xFF212121)
    val OnSecondaryContainerDark = Color(0xFFE0E0E0)
    val BackgroundDark    = Color(0xFF121212)
    val OnBackgroundDark  = Color(0xFFE0E0E0)
    val SurfaceDark       = Color(0xFF1E1E1E)
    val OnSurfaceDark     = Color(0xFFE0E0E0)
    val SurfaceVariantDark   = Color(0xFF2C2C2C)
    val OnSurfaceVariantDark = Color(0xFFBDBDBD)
    val OutlineDark          = Color(0xFF616161)
}
