package com.yourorg.toolkit.button

import androidx.compose.runtime.Immutable

/**
 * Color configuration for [ToggleIconButton].
 *
 * Composes two [ButtonColors] instances — one for the checked state and one
 * for the unchecked state. The composable selects the appropriate set based
 * on [ToggleIconButton]'s `checked` parameter and applies the same animated
 * color resolution logic as [IconButton].
 *
 * @param checkedColors Colors applied when the toggle is in the checked state.
 * @param uncheckedColors Colors applied when the toggle is in the unchecked state.
 */
@Immutable
data class ToggleIconButtonColors(
    val checkedColors: ButtonColors,
    val uncheckedColors: ButtonColors,
)
