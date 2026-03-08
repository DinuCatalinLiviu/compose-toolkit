package com.yourorg.toolkit.button

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.Button as M3Button
import androidx.compose.material3.ButtonDefaults as M3ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yourorg.toolkit.core.theme.ToolkitTheme

// ── Variant enum ──────────────────────────────────────────────────────────────

/**
 * Visual variants available for [Button].
 *
 * - [Primary]     — high-emphasis filled button; use for the single primary action.
 * - [Secondary]   — medium-emphasis outlined button; for secondary actions.
 * - [Ghost]       — low-emphasis text-only button; for tertiary / destructive-light actions.
 * - [Destructive] — high-emphasis filled button using the error color role.
 */
enum class ButtonVariant { Primary, Secondary, Ghost, Destructive }

// ── Public composable ─────────────────────────────────────────────────────────

/**
 * A toolkit button that maps to Material 3's [M3Button] and is fully
 * customisable via [colors], [shape], and [contentPadding].
 *
 * ```kotlin
 * Button(onClick = { /* ... */ }) { Text("Save") }
 *
 * Button(
 *     onClick  = onDelete,
 *     variant  = ButtonVariant.Destructive,
 * ) { Text("Delete account") }
 * ```
 *
 * @param onClick        Called when the button is tapped.
 * @param modifier       Applied to the root layout node.
 * @param enabled        Controls whether the button can be interacted with.
 * @param variant        Selects the visual style. Defaults to [ButtonVariant.Primary].
 * @param colors         Fine-grained color overrides. Defaults to [ButtonDefaults.colors].
 * @param shape          Corner shape. Defaults to [ButtonDefaults.shape].
 * @param contentPadding Internal padding around the content. Defaults to [ButtonDefaults.ContentPadding].
 * @param content        The button's label / icon composable(s).
 */
@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    variant: ButtonVariant = ButtonVariant.Primary,
    colors: ButtonColors = ButtonDefaults.colors(variant),
    shape: Shape = ButtonDefaults.shape,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    val resolvedColors = M3ButtonDefaults.buttonColors(
        containerColor         = colors.containerColor,
        contentColor           = colors.contentColor,
        disabledContainerColor = colors.disabledContainerColor,
        disabledContentColor   = colors.disabledContentColor,
    )

    val border = if (colors.borderColor.alpha > 0f) {
        BorderStroke(width = 1.dp, color = if (enabled) colors.borderColor else colors.disabledBorderColor)
    } else null

    M3Button(
        onClick        = onClick,
        modifier       = modifier
            .defaultMinSize(minWidth = ButtonDefaults.MinWidth, minHeight = ButtonDefaults.MinHeight)
            .semantics { role = Role.Button },
        enabled        = enabled,
        shape          = shape,
        colors         = resolvedColors,
        border         = border,
        contentPadding = contentPadding,
        content        = content,
    )
}

// ── Previews ──────────────────────────────────────────────────────────────────

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
