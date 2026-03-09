package com.yourorg.toolkit.button

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.yourorg.toolkit.core.theme.ToolkitTheme

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
