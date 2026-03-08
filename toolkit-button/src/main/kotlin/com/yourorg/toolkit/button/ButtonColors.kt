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
)
