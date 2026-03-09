package com.yourorg.toolkit.button

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.yourorg.toolkit.core.theme.ToolkitTheme

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
