package com.yourorg.toolkit.button

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

import com.yourorg.toolkit.core.theme.ToolkitTheme

private const val STATE_ANIMATION_DURATION_MS = 120

/**
 * Resolves a state color: if [stateColor] is [Color.Unspecified], falls back
 * to [fallback].
 */
private fun resolveStateColor(stateColor: Color, fallback: Color): Color =
    if (stateColor == Color.Unspecified) fallback else stateColor

/**
 * An icon-only button that renders [imageVector] inside a shaped container
 * with an optional [label] displayed below.
 *
 * Supports the same interaction states (pressed, focused, hovered) as the
 * text [Button], with animated color transitions and a focus ring.
 * The container [shape] defaults to [CircleShape][IconButtonDefaults.shape]
 * and the [size] controls both the outer container and the icon dimensions.
 *
 * @param imageVector The icon to render inside the button.
 * @param contentDescription Accessibility label — always required for icon-only controls.
 * @param onClick Called when the icon button is tapped. No-op while [isLoading] is true.
 * @param modifier Applied to the root layout node.
 * @param enabled Controls whether the button responds to interaction.
 * @param variant Selects the visual style. Defaults to [ButtonVariant.Primary].
 * @param size Controls the outer container and icon dimensions. Defaults to [IconButtonSize.Large].
 * @param shape Container shape. Defaults to [IconButtonDefaults.shape] (circle).
 * @param label Optional text label displayed below the icon.
 * @param isLoading When true, shows a spinner and blocks interaction.
 * @param customIconSize Overrides the default icon size for the given [size].
 * @param colors Fine-grained color overrides. Defaults to [IconButtonDefaults.colors].
 * @param interactionSource Optional interaction source for observing interaction state.
 */
@Composable
fun IconButton(
    imageVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    variant: ButtonVariant = ButtonVariant.Primary,
    size: IconButtonSize = IconButtonSize.Large,
    shape: Shape = IconButtonDefaults.shape,
    label: String? = null,
    isLoading: Boolean = false,
    customIconSize: Dp? = null,
    colors: ButtonColors = IconButtonDefaults.colors(variant),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    // Resolve effective container color: disabled > pressed > focused > hovered > default
    val targetContainerColor = when {
        !enabled -> colors.disabledContainerColor
        isPressed -> resolveStateColor(colors.pressedContainerColor, colors.containerColor)
        isFocused -> resolveStateColor(colors.focusedContainerColor, colors.containerColor)
        isHovered -> resolveStateColor(colors.hoveredContainerColor, colors.containerColor)
        else -> colors.containerColor
    }
    val targetContentColor = when {
        !enabled -> colors.disabledContentColor
        isPressed -> resolveStateColor(colors.pressedContentColor, colors.contentColor)
        isFocused -> resolveStateColor(colors.focusedContentColor, colors.contentColor)
        isHovered -> resolveStateColor(colors.hoveredContentColor, colors.contentColor)
        else -> colors.contentColor
    }

    val animSpec = tween<Color>(durationMillis = STATE_ANIMATION_DURATION_MS)
    val animatedContainerColor by animateColorAsState(targetContainerColor, animSpec)
    val animatedContentColor by animateColorAsState(targetContentColor, animSpec)

    val containerDp = IconButtonDefaults.containerSize(size)
    val iconDp = customIconSize ?: IconButtonDefaults.iconSize(size)
    val borderWidthDp = IconButtonDefaults.borderWidth(size)

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

    // Border modifier — only applied when borderWidth > 0 and borderColor is visible
    val borderModifier = if (borderWidthDp > 0.dp && colors.borderColor != Color.Transparent) {
        val resolvedBorderColor = if (enabled) colors.borderColor else colors.disabledBorderColor
        Modifier.border(BorderStroke(borderWidthDp, resolvedBorderColor), shape)
    } else {
        Modifier
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .then(focusRingModifier)
                .size(containerDp)
                .clip(shape)
                .background(color = animatedContainerColor)
                .then(borderModifier)
                .clickable(
                    interactionSource = interactionSource,
                    indication = ripple(bounded = true, color = colors.contentColor),
                    enabled = enabled && !isLoading,
                    role = Role.Button,
                    onClick = onClick,
                )
                .semantics {
                    this.contentDescription = contentDescription
                    role = Role.Button
                },
            contentAlignment = Alignment.Center,
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(iconDp),
                    color = colors.contentColor,
                    strokeWidth = 2.dp,
                )
            } else {
                Icon(
                    imageVector = imageVector,
                    // contentDescription is set on the container via semantics
                    contentDescription = null,
                    tint = animatedContentColor,
                    modifier = Modifier.size(iconDp),
                )
            }
        }

        if (label != null) {
            Spacer(modifier = Modifier.height(IconButtonDefaults.labelSpacing))
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = animatedContentColor,
            )
        }
    }
}

// Previews

@Preview(name = "IconButton — Primary (light)", showBackground = true)
@Composable
private fun IconButtonPrimaryPreview() {
    ToolkitTheme {
        IconButton(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favorite",
            onClick = {},
        )
    }
}

@Preview(name = "IconButton — Secondary (light)", showBackground = true)
@Composable
private fun IconButtonSecondaryPreview() {
    ToolkitTheme {
        IconButton(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favorite",
            onClick = {},
            variant = ButtonVariant.Secondary,
        )
    }
}

@Preview(name = "IconButton — Ghost (light)", showBackground = true)
@Composable
private fun IconButtonGhostPreview() {
    ToolkitTheme {
        IconButton(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favorite",
            onClick = {},
            variant = ButtonVariant.Ghost,
        )
    }
}

@Preview(name = "IconButton — With Label (light)", showBackground = true)
@Composable
private fun IconButtonWithLabelPreview() {
    ToolkitTheme {
        IconButton(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favorite",
            onClick = {},
            variant = ButtonVariant.Secondary,
            label = "Label",
        )
    }
}

@Preview(name = "IconButton — Square (light)", showBackground = true)
@Composable
private fun IconButtonSquarePreview() {
    ToolkitTheme {
        IconButton(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add",
            onClick = {},
            variant = ButtonVariant.Secondary,
            shape = IconButtonDefaults.squareShape(IconButtonSize.Large),
        )
    }
}

@Preview(name = "IconButton — Disabled (light)", showBackground = true)
@Composable
private fun IconButtonDisabledPreview() {
    ToolkitTheme {
        IconButton(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favorite",
            onClick = {},
            enabled = false,
        )
    }
}

@Preview(name = "IconButton — Loading (light)", showBackground = true)
@Composable
private fun IconButtonLoadingPreview() {
    ToolkitTheme {
        IconButton(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Loading",
            onClick = {},
            isLoading = true,
        )
    }
}

@Preview(name = "IconButton — Small (light)", showBackground = true)
@Composable
private fun IconButtonSmallPreview() {
    ToolkitTheme {
        IconButton(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favorite",
            onClick = {},
            size = IconButtonSize.Small,
            label = "Small",
        )
    }
}

@Preview(name = "IconButton — Medium (light)", showBackground = true)
@Composable
private fun IconButtonMediumPreview() {
    ToolkitTheme {
        IconButton(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favorite",
            onClick = {},
            size = IconButtonSize.Medium,
            label = "Medium",
        )
    }
}

@Preview(name = "IconButton — Primary (dark)", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun IconButtonPrimaryDarkPreview() {
    ToolkitTheme {
        IconButton(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "Favorite",
            onClick = {},
        )
    }
}