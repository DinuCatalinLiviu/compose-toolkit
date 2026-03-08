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
    val minWidth = 64.dp

    // Size — Large (default, WCAG AA minimum touch target)
    val largeMinHeight = 48.dp
    val largeHorizontalPadding = 24.dp
    val largeVerticalPadding = 12.dp
    val largeContentPadding = PaddingValues(
        horizontal = largeHorizontalPadding,
        vertical = largeVerticalPadding,
    )
    val largeIconContentPadding = PaddingValues(
        start = 16.dp,
        end = largeHorizontalPadding,
        top = largeVerticalPadding,
        bottom = largeVerticalPadding,
    )

    // Size — Small
    val smallMinHeight = 36.dp
    val smallHorizontalPadding = 12.dp
    val smallVerticalPadding = 8.dp
    val smallContentPadding = PaddingValues(
        horizontal = smallHorizontalPadding,
        vertical = smallVerticalPadding,
    )
    val smallIconContentPadding = PaddingValues(
        start = 10.dp,
        end = smallHorizontalPadding,
        top = smallVerticalPadding,
        bottom = smallVerticalPadding,
    )

    // Shared
    val iconSize = 18.dp
    val iconSpacing = 8.dp
    val labelTextSize = 14.sp
}
