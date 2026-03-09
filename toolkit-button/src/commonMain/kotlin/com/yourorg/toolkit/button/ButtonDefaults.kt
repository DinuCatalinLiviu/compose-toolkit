package com.yourorg.toolkit.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.Dp

/**
 * Default values and token-backed helpers for [Button].
 *
 * Every property is derived from [MaterialTheme] tokens (which are themselves
 * driven by [com.yourorg.toolkit.core.theme.ToolkitTheme]) so consumer apps
 * can restyle buttons just by providing a custom color scheme or shapes.
 */
object ButtonDefaults {

    /** Default content padding for a [ButtonSize.Large] button without a leading icon. */
    val contentPadding: PaddingValues get() = ButtonTokens.largeContentPadding

    /** Default content padding when a [ButtonSize.Large] button has a leading icon. */
    val iconContentPadding: PaddingValues get() = ButtonTokens.largeIconContentPadding

    /** Spacing between a leading icon and the button label. */
    val iconSpacing: Dp get() = ButtonTokens.iconSpacing

    /** Returns the appropriate content padding for [size], with or without a leading icon. */
    fun contentPadding(size: ButtonSize, hasLeadingIcon: Boolean = false): PaddingValues = when (size) {
        ButtonSize.Large -> if (hasLeadingIcon) ButtonTokens.largeIconContentPadding else ButtonTokens.largeContentPadding
        ButtonSize.Small -> if (hasLeadingIcon) ButtonTokens.smallIconContentPadding else ButtonTokens.smallContentPadding
    }

    /** Returns the minimum height token for [size]. */
    fun minHeight(size: ButtonSize): Dp = when (size) {
        ButtonSize.Large -> ButtonTokens.largeMinHeight
        ButtonSize.Small -> ButtonTokens.smallMinHeight
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
        pressedContainerColor: Color = variantPressedContainerColor(variant, containerColor),
        pressedContentColor: Color = variantPressedContentColor(variant, contentColor),
        focusedContainerColor: Color = variantFocusedContainerColor(variant, containerColor),
        focusedContentColor: Color = variantFocusedContentColor(variant, contentColor),
        hoveredContainerColor: Color = variantHoveredContainerColor(variant, containerColor),
        hoveredContentColor: Color = variantHoveredContentColor(variant, contentColor),
    ): ButtonColors = ButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        borderColor = borderColor,
        disabledBorderColor = disabledBorderColor,
        pressedContainerColor = pressedContainerColor,
        pressedContentColor = pressedContentColor,
        focusedContainerColor = focusedContainerColor,
        focusedContentColor = focusedContentColor,
        hoveredContainerColor = hoveredContainerColor,
        hoveredContentColor = hoveredContentColor,
    )

    // Variant base color helpers

    @Composable
    private fun variantContainerColor(variant: ButtonVariant): Color = when (variant) {
        ButtonVariant.Primary -> MaterialTheme.colorScheme.primary
        ButtonVariant.Secondary -> Color.Transparent
        ButtonVariant.Ghost -> Color.Transparent
        ButtonVariant.Destructive -> MaterialTheme.colorScheme.error
    }

    @Composable
    private fun variantContentColor(variant: ButtonVariant): Color = when (variant) {
        ButtonVariant.Primary -> MaterialTheme.colorScheme.onPrimary
        ButtonVariant.Secondary -> MaterialTheme.colorScheme.primary
        ButtonVariant.Ghost -> MaterialTheme.colorScheme.primary
        ButtonVariant.Destructive -> MaterialTheme.colorScheme.onError
    }

    @Composable
    private fun variantBorderColor(variant: ButtonVariant): Color = when (variant) {
        ButtonVariant.Secondary -> MaterialTheme.colorScheme.outline
        ButtonVariant.Ghost -> Color.Transparent
        else -> Color.Transparent
    }

    // Pressed state

    @Composable
    private fun variantPressedContainerColor(variant: ButtonVariant, base: Color): Color =
        when (variant) {
            // Secondary / Ghost invert to black bg per Figma "Active" row
            ButtonVariant.Secondary,
            ButtonVariant.Ghost -> MaterialTheme.colorScheme.onSurface
            // Primary / Destructive: lighter overlay
            ButtonVariant.Primary,
            ButtonVariant.Destructive -> MaterialTheme.colorScheme.onPrimary
                .copy(alpha = ButtonTokens.pressedStateLayerAlpha)
                .compositeOver(base)
        }

    @Composable
    private fun variantPressedContentColor(variant: ButtonVariant, base: Color): Color =
        when (variant) {
            ButtonVariant.Secondary,
            ButtonVariant.Ghost -> MaterialTheme.colorScheme.surface
            ButtonVariant.Primary,
            ButtonVariant.Destructive -> base
        }

    // Focused state

    @Composable
    private fun variantFocusedContainerColor(variant: ButtonVariant, base: Color): Color =
        when (variant) {
            ButtonVariant.Secondary,
            ButtonVariant.Ghost -> MaterialTheme.colorScheme.onSurface
                .copy(alpha = ButtonTokens.focusStateLayerAlpha)
                .compositeOver(MaterialTheme.colorScheme.surface)
            ButtonVariant.Primary,
            ButtonVariant.Destructive -> MaterialTheme.colorScheme.onPrimary
                .copy(alpha = ButtonTokens.focusStateLayerAlpha)
                .compositeOver(base)
        }

    @Composable
    private fun variantFocusedContentColor(variant: ButtonVariant, base: Color): Color = base

    // Hovered state

    @Composable
    private fun variantHoveredContainerColor(variant: ButtonVariant, base: Color): Color =
        when (variant) {
            ButtonVariant.Secondary,
            ButtonVariant.Ghost -> MaterialTheme.colorScheme.onSurface
                .copy(alpha = ButtonTokens.hoverStateLayerAlpha)
                .compositeOver(MaterialTheme.colorScheme.surface)
            ButtonVariant.Primary,
            ButtonVariant.Destructive -> MaterialTheme.colorScheme.onPrimary
                .copy(alpha = ButtonTokens.hoverStateLayerAlpha)
                .compositeOver(base)
        }

    @Composable
    private fun variantHoveredContentColor(variant: ButtonVariant, base: Color): Color = base

    /** Minimum touch target width. */
    val minWidth: Dp get() = ButtonTokens.minWidth

    /** Minimum touch target height (48 dp for WCAG AA). */
    val minHeightDefault: Dp get() = ButtonTokens.largeMinHeight
}
