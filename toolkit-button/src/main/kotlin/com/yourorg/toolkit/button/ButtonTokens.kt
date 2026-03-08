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

    // Size — Large (default, WCAG AA minimum touch target)
    val LargeMinHeight        = 48.dp
    val LargeHorizontalPadding = 24.dp
    val LargeVerticalPadding   = 12.dp
    val LargeContentPadding    = PaddingValues(
        horizontal = LargeHorizontalPadding,
        vertical   = LargeVerticalPadding,
    )
    val LargeIconContentPadding = PaddingValues(
        start  = 16.dp,
        end    = LargeHorizontalPadding,
        top    = LargeVerticalPadding,
        bottom = LargeVerticalPadding,
    )

    // Size — Small
    val SmallMinHeight        = 36.dp
    val SmallHorizontalPadding = 12.dp
    val SmallVerticalPadding   = 8.dp
    val SmallContentPadding    = PaddingValues(
        horizontal = SmallHorizontalPadding,
        vertical   = SmallVerticalPadding,
    )
    val SmallIconContentPadding = PaddingValues(
        start  = 10.dp,
        end    = SmallHorizontalPadding,
        top    = SmallVerticalPadding,
        bottom = SmallVerticalPadding,
    )

    // Shared
    val IconSize      = 18.dp
    val IconSpacing   = 8.dp
    val LabelTextSize = 14.sp

    // Legacy aliases — kept so existing ButtonDefaults references compile
    val MinHeight         get() = LargeMinHeight
    val HorizontalPadding get() = LargeHorizontalPadding
    val VerticalPadding   get() = LargeVerticalPadding
    val ContentPadding    get() = LargeContentPadding
    val IconContentPadding get() = LargeIconContentPadding
}
