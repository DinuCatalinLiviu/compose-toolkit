package com.yourorg.toolkit.button

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

/**
 * Size variants for [IconButton].
 *
 * - [Small] — 36 dp container, 16 dp icon, no border.
 * - [Medium] — 36 dp container, 20 dp icon, thin border.
 * - [Large] — 54 dp container, 24 dp icon, medium border.
 */
enum class IconButtonSize { Small, Medium, Large }

/**
 * Default values and token-backed helpers for [IconButton].
 *
 * Reuses [ButtonColors] for state-color consistency with text [Button].
 * Variant-based defaults are built by delegating to [ButtonDefaults.colors],
 * with icon-button-specific container and content color overrides.
 */
object IconButtonDefaults {

    /** Default shape for an [IconButton] — fully circular. */
    val shape: Shape = CircleShape

    /** Spacing between the icon container and the optional label. */
    val labelSpacing: Dp get() = ButtonTokens.iconButtonLabelSpacing

    /** Outer container size for an [IconButton] at the given [size]. */
    fun containerSize(size: IconButtonSize): Dp = when (size) {
        IconButtonSize.Small -> ButtonTokens.iconButtonSmallContainerSize
        IconButtonSize.Medium -> ButtonTokens.iconButtonMediumContainerSize
        IconButtonSize.Large -> ButtonTokens.iconButtonLargeContainerSize
    }

    /** Icon dimension for an [IconButton] at the given [size]. */
    fun iconSize(size: IconButtonSize): Dp = when (size) {
        IconButtonSize.Small -> ButtonTokens.iconButtonSmallIconSize
        IconButtonSize.Medium -> ButtonTokens.iconButtonMediumIconSize
        IconButtonSize.Large -> ButtonTokens.iconButtonLargeIconSize
    }

    /** Border width for an [IconButton] at the given [size]. Returns 0 dp for [IconButtonSize.Small]. */
    fun borderWidth(size: IconButtonSize): Dp = when (size) {
        IconButtonSize.Small -> ButtonTokens.iconButtonSmallBorderWidth
        IconButtonSize.Medium -> ButtonTokens.iconButtonMediumBorderWidth
        IconButtonSize.Large -> ButtonTokens.iconButtonLargeBorderWidth
    }

    /**
     * Returns a rounded-rectangle [Shape] for the given [size], suitable for
     * non-circular icon buttons.
     */
    fun squareShape(size: IconButtonSize): Shape = RoundedCornerShape(
        when (size) {
            IconButtonSize.Small -> ButtonTokens.iconButtonSmallCornerRadius
            IconButtonSize.Medium -> ButtonTokens.iconButtonMediumCornerRadius
            IconButtonSize.Large -> ButtonTokens.iconButtonLargeCornerRadius
        },
    )

    /**
     * Builds a [ButtonColors] set for an icon button of the given [variant].
     *
     * Delegates to [ButtonDefaults.colors] with icon-button-specific overrides
     * for container and content colours:
     * - [ButtonVariant.Primary] / [ButtonVariant.Destructive] — same as text [Button].
     * - [ButtonVariant.Secondary] — filled with [surfaceVariant][androidx.compose.material3.ColorScheme.surfaceVariant],
     *   [onSurface][androidx.compose.material3.ColorScheme.onSurface] content, outlined border.
     * - [ButtonVariant.Ghost] — transparent background, [onSurface][androidx.compose.material3.ColorScheme.onSurface] content,
     *   no border.
     */
    @Composable
    fun colors(variant: ButtonVariant = ButtonVariant.Primary): ButtonColors {
        val cs = MaterialTheme.colorScheme
        return when (variant) {
            ButtonVariant.Secondary -> ButtonDefaults.colors(
                variant = variant,
                containerColor = cs.surfaceVariant,
                contentColor = cs.onSurface,
                borderColor = cs.outline,
            )
            ButtonVariant.Ghost -> ButtonDefaults.colors(
                variant = variant,
                containerColor = Color.Transparent,
                contentColor = cs.onSurface,
                borderColor = Color.Transparent,
            )
            ButtonVariant.Primary -> ButtonDefaults.colors(variant = variant)
            ButtonVariant.Destructive -> ButtonDefaults.colors(variant = variant)
        }
    }
}
