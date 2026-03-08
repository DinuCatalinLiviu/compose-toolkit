package com.yourorg.toolkit.button

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button as M3Button
import androidx.compose.material3.ButtonDefaults as M3ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yourorg.toolkit.core.theme.ToolkitTheme

/**
 * Visual variants available for [Button].
 *
 * - [Primary] — high-emphasis filled button; use for the single primary action.
 * - [Secondary] — medium-emphasis outlined button; for secondary actions.
 * - [Ghost] — low-emphasis text-only button; for tertiary / destructive-light actions.
 * - [Destructive] — high-emphasis filled button using the error color role.
 */
enum class ButtonVariant { Primary, Secondary, Ghost, Destructive }

/**
 * Size variants available for [Button].
 *
 * - [Large] — default; 48 dp min-height, meets WCAG AA touch target.
 * - [Small] — 36 dp min-height; use in dense layouts where space is constrained.
 */
enum class ButtonSize { Small, Large }

private const val STATE_ANIMATION_DURATION_MS = 120

/**
 * Resolves a state color: if the provided [stateColor] is [Color.Unspecified],
 * falls back to [fallback].
 */
private fun resolveStateColor(stateColor: Color, fallback: Color): Color =
    if (stateColor == Color.Unspecified) fallback else stateColor

/**
 * A toolkit button that maps to Material 3's [M3Button] and is fully
 * customisable via [colors], [shape], [size], and [contentPadding].
 *
 * Interaction states (pressed, focused, hovered) are animated automatically
 * based on the [ButtonColors] state color properties. When state colors are
 * [Color.Unspecified] the button falls back to the base enabled colors.
 *
 * @param onClick Called when the button is tapped. No-op while [isLoading] is true.
 * @param modifier Applied to the root layout node.
 * @param enabled Controls whether the button can be interacted with.
 * @param variant Selects the visual style. Defaults to [ButtonVariant.Primary].
 * @param size Controls the button height and padding. Defaults to [ButtonSize.Large].
 * @param isLoading When true, replaces content with a progress indicator and blocks interaction.
 * @param colors Fine-grained color overrides. Defaults to [ButtonDefaults.colors].
 * @param shape Corner shape. Defaults to [ButtonDefaults.shape].
 * @param leadingIcon Optional composable rendered before [content] with standard icon spacing.
 * @param contentPadding Internal padding around the content. Derived from [size] and [leadingIcon] by default.
 * @param content The button's label / icon composable(s).
 */
@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    variant: ButtonVariant = ButtonVariant.Primary,
    size: ButtonSize = ButtonSize.Large,
    isLoading: Boolean = false,
    colors: ButtonColors = ButtonDefaults.colors(variant),
    shape: Shape = ButtonDefaults.shape,
    leadingIcon: @Composable (() -> Unit)? = null,
    contentPadding: PaddingValues = ButtonDefaults.contentPadding(size, hasLeadingIcon = leadingIcon != null),
    content: @Composable RowScope.() -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    // Resolve effective container color: pressed > focused > hovered > default
    val targetContainerColor = when {
        isPressed -> resolveStateColor(colors.pressedContainerColor, colors.containerColor)
        isFocused -> resolveStateColor(colors.focusedContainerColor, colors.containerColor)
        isHovered -> resolveStateColor(colors.hoveredContainerColor, colors.containerColor)
        else -> colors.containerColor
    }
    val targetContentColor = when {
        isPressed -> resolveStateColor(colors.pressedContentColor, colors.contentColor)
        isFocused -> resolveStateColor(colors.focusedContentColor, colors.contentColor)
        isHovered -> resolveStateColor(colors.hoveredContentColor, colors.contentColor)
        else -> colors.contentColor
    }

    val animSpec = tween<Color>(durationMillis = STATE_ANIMATION_DURATION_MS)
    val animatedContainerColor by animateColorAsState(targetContainerColor, animSpec)
    val animatedContentColor by animateColorAsState(targetContentColor, animSpec)

    val resolvedColors = M3ButtonDefaults.buttonColors(
        containerColor = animatedContainerColor,
        contentColor = animatedContentColor,
        disabledContainerColor = colors.disabledContainerColor,
        disabledContentColor = colors.disabledContentColor,
    )

    val border = if (colors.borderColor.alpha > 0f) {
        BorderStroke(width = 1.dp, color = if (enabled) colors.borderColor else colors.disabledBorderColor)
    } else null

    // Focus ring modifier
    val focusRingModifier = if (isFocused && enabled) {
        Modifier
            .padding(ButtonTokens.focusRingOffset)
            .border(
                width = ButtonTokens.focusRingWidth,
                color = animatedContentColor,
                shape = shape,
            )
    } else {
        Modifier
    }

    M3Button(
        onClick = onClick,
        modifier = modifier
            .then(focusRingModifier)
            .defaultMinSize(minWidth = ButtonDefaults.minWidth, minHeight = ButtonDefaults.minHeight(size))
            .semantics { role = Role.Button },
        enabled = enabled && !isLoading,
        shape = shape,
        colors = resolvedColors,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(ButtonTokens.iconSize),
                color = colors.contentColor,
                strokeWidth = 2.dp,
            )
        } else {
            if (leadingIcon != null) {
                leadingIcon()
                Spacer(Modifier.width(ButtonDefaults.iconSpacing))
            }
            content()
        }
    }
}

// Previews

@Preview(name = "Button — Primary (light)", showBackground = true)
@Composable
private fun ButtonPrimaryPreview() {
    ToolkitTheme {
        Button(onClick = {}) { Text("Primary") }
    }
}

@Preview(name = "Button — Secondary (light)", showBackground = true)
@Composable
private fun ButtonSecondaryPreview() {
    ToolkitTheme {
        Button(onClick = {}, variant = ButtonVariant.Secondary) { Text("Secondary") }
    }
}

@Preview(name = "Button — Ghost (light)", showBackground = true)
@Composable
private fun ButtonGhostPreview() {
    ToolkitTheme {
        Button(onClick = {}, variant = ButtonVariant.Ghost) { Text("Ghost") }
    }
}

@Preview(name = "Button — Destructive (light)", showBackground = true)
@Composable
private fun ButtonDestructivePreview() {
    ToolkitTheme {
        Button(onClick = {}, variant = ButtonVariant.Destructive) { Text("Delete") }
    }
}

@Preview(name = "Button — Disabled (light)", showBackground = true)
@Composable
private fun ButtonDisabledPreview() {
    ToolkitTheme {
        Button(onClick = {}, enabled = false) { Text("Disabled") }
    }
}

@Preview(name = "Button — Primary (dark)", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ButtonPrimaryDarkPreview() {
    ToolkitTheme {
        Button(onClick = {}) { Text("Primary") }
    }
}

@Preview(name = "Button — Disabled (dark)", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ButtonDisabledDarkPreview() {
    ToolkitTheme {
        Button(onClick = {}, enabled = false) { Text("Disabled") }
    }
}

@Preview(name = "Button — Small", showBackground = true)
@Composable
private fun ButtonSmallPreview() {
    ToolkitTheme {
        Button(onClick = {}, size = ButtonSize.Small) { Text("Small") }
    }
}

@Preview(name = "Button — Small Secondary", showBackground = true)
@Composable
private fun ButtonSmallSecondaryPreview() {
    ToolkitTheme {
        Button(onClick = {}, size = ButtonSize.Small, variant = ButtonVariant.Secondary) { Text("Small") }
    }
}

@Preview(name = "Button — Loading (Large)", showBackground = true)
@Composable
private fun ButtonLoadingLargePreview() {
    ToolkitTheme {
        Button(onClick = {}, isLoading = true) { Text("Submit") }
    }
}

@Preview(name = "Button — Loading (Small)", showBackground = true)
@Composable
private fun ButtonLoadingSmallPreview() {
    ToolkitTheme {
        Button(onClick = {}, size = ButtonSize.Small, isLoading = true) { Text("Submit") }
    }
}

@Preview(name = "Button — Leading Icon", showBackground = true)
@Composable
private fun ButtonLeadingIconPreview() {
    ToolkitTheme {
        Button(
            onClick = {},
            leadingIcon = {
                // Replace with an Icon composable using your project's icon set
                Text("+", modifier = Modifier.size(ButtonTokens.iconSize))
            },
        ) { Text("Add item") }
    }
}
