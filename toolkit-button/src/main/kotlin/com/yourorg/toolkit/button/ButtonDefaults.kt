package com.yourorg.toolkit.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Default values and token-backed helpers for [Button].
 *
 * Every property is derived from [MaterialTheme] tokens (which are themselves
 * driven by [com.yourorg.toolkit.core.theme.ToolkitTheme]) so consumer apps
 * can restyle buttons just by providing a custom color scheme or shapes.
 */
object ButtonDefaults {

    /** Default content padding for a [ButtonSize.Large] button without a leading icon. */
    val ContentPadding: PaddingValues get() = ButtonTokens.LargeContentPadding

    /** Default content padding when a [ButtonSize.Large] button has a leading icon. */
    val IconContentPadding: PaddingValues get() = ButtonTokens.LargeIconContentPadding

    /** Spacing between a leading icon and the button label. */
    val IconSpacing: Dp get() = ButtonTokens.IconSpacing

    /** Returns the appropriate content padding for [size], with or without a leading icon. */
    fun contentPadding(size: ButtonSize, hasLeadingIcon: Boolean = false): PaddingValues = when (size) {
        ButtonSize.Large -> if (hasLeadingIcon) ButtonTokens.LargeIconContentPadding else ButtonTokens.LargeContentPadding
        ButtonSize.Small -> if (hasLeadingIcon) ButtonTokens.SmallIconContentPadding else ButtonTokens.SmallContentPadding
    }

    /** Returns the minimum height token for [size]. */
    fun minHeight(size: ButtonSize): Dp = when (size) {
        ButtonSize.Large -> ButtonTokens.LargeMinHeight
        ButtonSize.Small -> ButtonTokens.SmallMinHeight
    }

    /**
     * Returns the default shape for a [Button].
     * Delegates to [MaterialTheme.shapes.small] so it tracks the toolkit theme.
     */
    val shape: Shape
        @Composable get() = MaterialTheme.shapes.small

    /**
     * Builds a [ButtonColors] set for the given [variant] sourced from the
     * current [MaterialTheme] color scheme.
     *
     * Override individual colours as needed:
     * ```kotlin
     * ButtonDefaults.colors(containerColor = Color.Red)
     * ```
     */
    @Composable
    fun colors(
        variant: ButtonVariant = ButtonVariant.Primary,
        containerColor: Color = variantContainerColor(variant),
        contentColor: Color = variantContentColor(variant),
        disabledContainerColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
        disabledContentColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
        borderColor: Color = variantBorderColor(variant),
        disabledBorderColor: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
    ): ButtonColors = ButtonColors(
        containerColor        = containerColor,
        contentColor          = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor  = disabledContentColor,
        borderColor           = borderColor,
        disabledBorderColor   = disabledBorderColor,
    )

    // ── Variant helpers ───────────────────────────────────────────────────────

    @Composable
    private fun variantContainerColor(variant: ButtonVariant): Color = when (variant) {
        ButtonVariant.Primary     -> MaterialTheme.colorScheme.primary
        ButtonVariant.Secondary   -> Color.Transparent
        ButtonVariant.Ghost       -> Color.Transparent
        ButtonVariant.Destructive -> MaterialTheme.colorScheme.error
    }

    @Composable
    private fun variantContentColor(variant: ButtonVariant): Color = when (variant) {
        ButtonVariant.Primary     -> MaterialTheme.colorScheme.onPrimary
        ButtonVariant.Secondary   -> MaterialTheme.colorScheme.primary
        ButtonVariant.Ghost       -> MaterialTheme.colorScheme.primary
        ButtonVariant.Destructive -> MaterialTheme.colorScheme.onError
    }

    @Composable
    private fun variantBorderColor(variant: ButtonVariant): Color = when (variant) {
        ButtonVariant.Secondary -> MaterialTheme.colorScheme.outline
        ButtonVariant.Ghost     -> Color.Transparent
        else                    -> Color.Transparent
    }

    /** Minimum touch target width. */
    val MinWidth: Dp get() = ButtonTokens.MinWidth

    /** Minimum touch target height (48 dp for WCAG AA). */
    val MinHeight: Dp get() = ButtonTokens.MinHeight
}
