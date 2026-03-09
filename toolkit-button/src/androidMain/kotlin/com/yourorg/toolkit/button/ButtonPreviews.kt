package com.yourorg.toolkit.button

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yourorg.toolkit.core.theme.ToolkitTheme

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
