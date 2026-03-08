package com.yourorg.toolkit.button

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Color roles for a single [Button] instance.
 *
 * Obtain the default set from [ButtonDefaults.colors] and override only
 * the fields you need:
 *
 * ```kotlin
 * Button(
 *     colors = ButtonDefaults.colors(containerColor = MyBrand.Red),
 * ) { Text("Delete") }
 * ```
 */
@Immutable
data class ButtonColors(
    /** Background of the button in its enabled state. */
    val containerColor: Color,
    /** Foreground (text / icon) colour when enabled. */
    val contentColor: Color,
    /** Background when the button is disabled. */
    val disabledContainerColor: Color,
    /** Foreground when the button is disabled. */
    val disabledContentColor: Color,
    /** Border colour (used by [ButtonVariant.Secondary] and [ButtonVariant.Ghost]). */
    val borderColor: Color = Color.Transparent,
    /** Border colour when disabled. */
    val disabledBorderColor: Color = Color.Transparent,
    /** Background when pressed / active. [Color.Unspecified] uses a computed default. */
    val pressedContainerColor: Color = Color.Unspecified,
    /** Foreground when pressed / active. [Color.Unspecified] uses a computed default. */
    val pressedContentColor: Color = Color.Unspecified,
    /** Background when focused. [Color.Unspecified] uses a computed default. */
    val focusedContainerColor: Color = Color.Unspecified,
    /** Foreground when focused. [Color.Unspecified] uses a computed default. */
    val focusedContentColor: Color = Color.Unspecified,
    /** Background when hovered. [Color.Unspecified] uses a computed default. */
    val hoveredContainerColor: Color = Color.Unspecified,
    /** Foreground when hovered. [Color.Unspecified] uses a computed default. */
    val hoveredContentColor: Color = Color.Unspecified,
)
