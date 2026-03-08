package com.yourorg.toolkit.button

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
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
 * A toggleable icon button that switches between [checkedIcon] and
 * [uncheckedIcon] based on the [checked] state.
 *
 * Uses [Role.Checkbox] semantics and `.toggleable()` modifier, matching
 * Material 3's `IconToggleButton` pattern. The caller owns the `checked`
 * state (stateless component).
 *
 * Supports the same interaction states (pressed, focused, hovered) as
 * [IconButton], with animated color transitions, a focus ring, and bounded
 * ripple feedback.
 *
 * @param checked Whether the toggle is currently in the checked state.
 * @param onCheckedChange Called when the toggle state changes.
 * @param checkedIcon The icon displayed when [checked] is true.
 * @param uncheckedIcon The icon displayed when [checked] is false.
 * @param contentDescription Accessibility label — always required for icon-only controls.
 * @param modifier Applied to the root layout node.
 * @param enabled Controls whether the button responds to interaction.
 * @param variant Selects the visual style for the checked state. Defaults to [ButtonVariant.Primary].
 * @param size Controls the outer container and icon dimensions. Defaults to [IconButtonSize.Large].
 * @param shape Container shape. Defaults to [IconButtonDefaults.shape] (circle).
 * @param label Optional text label displayed below the icon.
 * @param customIconSize Overrides the default icon size for the given [size].
 * @param colors Fine-grained color overrides. Defaults to [ToggleIconButtonDefaults.colors].
 * @param interactionSource Optional interaction source for observing interaction state.
 */
@Composable
fun ToggleIconButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    checkedIcon: ImageVector,
    uncheckedIcon: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    variant: ButtonVariant = ButtonVariant.Primary,
    size: IconButtonSize = IconButtonSize.Large,
    shape: Shape = IconButtonDefaults.shape,
    label: String? = null,
    customIconSize: Dp? = null,
    colors: ToggleIconButtonColors = ToggleIconButtonDefaults.colors(variant),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val activeColors = if (checked) colors.checkedColors else colors.uncheckedColors

    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isHovered by interactionSource.collectIsHoveredAsState()

    // Resolve effective container color: disabled > pressed > focused > hovered > default
    val targetContainerColor = when {
        !enabled -> activeColors.disabledContainerColor
        isPressed -> resolveStateColor(activeColors.pressedContainerColor, activeColors.containerColor)
        isFocused -> resolveStateColor(activeColors.focusedContainerColor, activeColors.containerColor)
        isHovered -> resolveStateColor(activeColors.hoveredContainerColor, activeColors.containerColor)
        else -> activeColors.containerColor
    }
    val targetContentColor = when {
        !enabled -> activeColors.disabledContentColor
        isPressed -> resolveStateColor(activeColors.pressedContentColor, activeColors.contentColor)
        isFocused -> resolveStateColor(activeColors.focusedContentColor, activeColors.contentColor)
        isHovered -> resolveStateColor(activeColors.hoveredContentColor, activeColors.contentColor)
        else -> activeColors.contentColor
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
    val borderModifier = if (borderWidthDp > 0.dp && activeColors.borderColor != Color.Transparent) {
        val resolvedBorderColor = if (enabled) activeColors.borderColor else activeColors.disabledBorderColor
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
                .toggleable(
                    value = checked,
                    interactionSource = interactionSource,
                    indication = ripple(bounded = true, color = activeColors.contentColor),
                    enabled = enabled,
                    role = Role.Checkbox,
                    onValueChange = onCheckedChange,
                )
                .semantics {
                    this.contentDescription = contentDescription
                    role = Role.Checkbox
                },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = if (checked) checkedIcon else uncheckedIcon,
                // contentDescription is set on the container via semantics
                contentDescription = null,
                tint = animatedContentColor,
                modifier = Modifier.size(iconDp),
            )
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

@Preview(name = "ToggleIconButton — Checked (light)", showBackground = true)
@Composable
private fun ToggleIconButtonCheckedPreview() {
    ToolkitTheme {
        ToggleIconButton(
            checked = true,
            onCheckedChange = {},
            checkedIcon = Icons.Filled.Favorite,
            uncheckedIcon = Icons.Filled.FavoriteBorder,
            contentDescription = "Favorite",
        )
    }
}

@Preview(name = "ToggleIconButton — Unchecked (light)", showBackground = true)
@Composable
private fun ToggleIconButtonUncheckedPreview() {
    ToolkitTheme {
        ToggleIconButton(
            checked = false,
            onCheckedChange = {},
            checkedIcon = Icons.Filled.Favorite,
            uncheckedIcon = Icons.Filled.FavoriteBorder,
            contentDescription = "Favorite",
        )
    }
}

@Preview(name = "ToggleIconButton — Secondary Checked (light)", showBackground = true)
@Composable
private fun ToggleIconButtonSecondaryCheckedPreview() {
    ToolkitTheme {
        ToggleIconButton(
            checked = true,
            onCheckedChange = {},
            checkedIcon = Icons.Filled.Star,
            uncheckedIcon = Icons.Outlined.Star,
            contentDescription = "Star",
            variant = ButtonVariant.Secondary,
        )
    }
}

@Preview(name = "ToggleIconButton — With Label (light)", showBackground = true)
@Composable
private fun ToggleIconButtonWithLabelPreview() {
    ToolkitTheme {
        ToggleIconButton(
            checked = true,
            onCheckedChange = {},
            checkedIcon = Icons.Filled.Favorite,
            uncheckedIcon = Icons.Filled.FavoriteBorder,
            contentDescription = "Favorite",
            label = "Favorite",
        )
    }
}

@Preview(name = "ToggleIconButton — Small (light)", showBackground = true)
@Composable
private fun ToggleIconButtonSmallPreview() {
    ToolkitTheme {
        ToggleIconButton(
            checked = true,
            onCheckedChange = {},
            checkedIcon = Icons.Filled.Favorite,
            uncheckedIcon = Icons.Filled.FavoriteBorder,
            contentDescription = "Favorite",
            size = IconButtonSize.Small,
            label = "Small",
        )
    }
}

@Preview(name = "ToggleIconButton — Disabled (light)", showBackground = true)
@Composable
private fun ToggleIconButtonDisabledPreview() {
    ToolkitTheme {
        ToggleIconButton(
            checked = true,
            onCheckedChange = {},
            checkedIcon = Icons.Filled.Favorite,
            uncheckedIcon = Icons.Filled.FavoriteBorder,
            contentDescription = "Favorite",
            enabled = false,
        )
    }
}

@Preview(name = "ToggleIconButton — Square (light)", showBackground = true)
@Composable
private fun ToggleIconButtonSquarePreview() {
    ToolkitTheme {
        ToggleIconButton(
            checked = false,
            onCheckedChange = {},
            checkedIcon = Icons.Filled.Star,
            uncheckedIcon = Icons.Outlined.Star,
            contentDescription = "Star",
            variant = ButtonVariant.Secondary,
            shape = IconButtonDefaults.squareShape(IconButtonSize.Large),
        )
    }
}

@Preview(name = "ToggleIconButton — Checked (dark)", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ToggleIconButtonCheckedDarkPreview() {
    ToolkitTheme {
        ToggleIconButton(
            checked = true,
            onCheckedChange = {},
            checkedIcon = Icons.Filled.Favorite,
            uncheckedIcon = Icons.Filled.FavoriteBorder,
            contentDescription = "Favorite",
        )
    }
}
