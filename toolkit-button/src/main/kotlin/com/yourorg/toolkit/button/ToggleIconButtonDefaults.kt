package com.yourorg.toolkit.button

import androidx.compose.runtime.Composable

/**
 * Default values for [ToggleIconButton].
 *
 * Reuses [IconButtonDefaults] for sizing, shape, and border tokens, and
 * [ButtonDefaults.colors] for variant-based color resolution.
 * Sizing and shape helpers are accessed via [IconButtonDefaults] directly —
 * no duplication here.
 */
object ToggleIconButtonDefaults {

    /**
     * Builds a [ToggleIconButtonColors] set for the given [variant].
     *
     * - **checked** — uses the requested variant's colors (filled/emphasized).
     * - **unchecked** — uses [ButtonVariant.Ghost] colors (transparent, muted).
     *
     * @param variant The visual style applied in the checked state.
     */
    @Composable
    fun colors(variant: ButtonVariant = ButtonVariant.Primary): ToggleIconButtonColors =
        ToggleIconButtonColors(
            checkedColors = IconButtonDefaults.colors(variant),
            uncheckedColors = IconButtonDefaults.colors(ButtonVariant.Ghost),
        )
}
