package com.yourorg.toolkit.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Internal design-token constants for the Button component.
 *
 * These constants are intentionally `internal` — consuming apps customise
 * the button through [ButtonDefaults] and the [ButtonColors] override, not
 * by reaching into tokens directly.
 */
internal object ButtonTokens {
    val MinWidth       = 64.dp
    val MinHeight      = 48.dp   // WCAG AA minimum touch target
    val IconSize       = 18.dp
    val HorizontalPadding = 24.dp
    val VerticalPadding   = 12.dp
    val ContentPadding = PaddingValues(
        horizontal = HorizontalPadding,
        vertical   = VerticalPadding,
    )
    val IconContentPadding = PaddingValues(
        start  = 16.dp,
        end    = 24.dp,
        top    = VerticalPadding,
        bottom = VerticalPadding,
    )
    val IconSpacing    = 8.dp
    val LabelTextSize  = 14.sp
}
