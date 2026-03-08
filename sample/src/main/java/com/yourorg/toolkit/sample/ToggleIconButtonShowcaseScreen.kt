package com.yourorg.toolkit.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.yourorg.toolkit.button.ButtonVariant
import com.yourorg.toolkit.button.IconButtonDefaults
import com.yourorg.toolkit.button.IconButtonSize
import com.yourorg.toolkit.button.ToggleIconButton
import com.yourorg.toolkit.core.theme.ToolkitSpacing
import com.yourorg.toolkit.core.theme.ToolkitTheme

@Composable
fun ToggleIconButtonShowcaseScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(ToolkitSpacing.md),
        verticalArrangement = Arrangement.spacedBy(ToolkitSpacing.sm),
    ) {
        // Circle — Variants by size (interactive)
        ToggleSection(title = "Circle — Variants (tap to toggle)") {
            val variants = listOf(ButtonVariant.Primary, ButtonVariant.Secondary, ButtonVariant.Ghost)
            IconButtonSize.entries.forEach { size ->
                Row(horizontalArrangement = Arrangement.spacedBy(ToolkitSpacing.sm)) {
                    variants.forEach { variant ->
                        var checked by remember { mutableStateOf(false) }
                        ToggleIconButton(
                            checked = checked,
                            onCheckedChange = { checked = it },
                            checkedIcon = Icons.Filled.Favorite,
                            uncheckedIcon = Icons.Filled.FavoriteBorder,
                            contentDescription = "${variant.name} ${size.name}",
                            variant = variant,
                            size = size,
                            label = variant.name,
                        )
                    }
                    Text(
                        text = size.name,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(start = ToolkitSpacing.xs),
                    )
                }
            }
        }

        // Square — Variants by size
        ToggleSection(title = "Square — Variants") {
            val variants = listOf(ButtonVariant.Primary, ButtonVariant.Secondary, ButtonVariant.Ghost)
            IconButtonSize.entries.forEach { size ->
                Row(horizontalArrangement = Arrangement.spacedBy(ToolkitSpacing.sm)) {
                    variants.forEach { variant ->
                        var checked by remember { mutableStateOf(true) }
                        ToggleIconButton(
                            checked = checked,
                            onCheckedChange = { checked = it },
                            checkedIcon = Icons.Filled.Star,
                            uncheckedIcon = Icons.Outlined.Star,
                            contentDescription = "${variant.name} ${size.name}",
                            variant = variant,
                            size = size,
                            shape = IconButtonDefaults.squareShape(size),
                        )
                    }
                    Text(
                        text = size.name,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(start = ToolkitSpacing.xs),
                    )
                }
            }
        }

        // Checked vs Unchecked comparison
        ToggleSection(title = "Checked vs Unchecked") {
            Row(horizontalArrangement = Arrangement.spacedBy(ToolkitSpacing.sm)) {
                ToggleIconButton(
                    checked = true,
                    onCheckedChange = {},
                    checkedIcon = Icons.Filled.Favorite,
                    uncheckedIcon = Icons.Filled.FavoriteBorder,
                    contentDescription = "Checked",
                    label = "Checked",
                )
                ToggleIconButton(
                    checked = false,
                    onCheckedChange = {},
                    checkedIcon = Icons.Filled.Favorite,
                    uncheckedIcon = Icons.Filled.FavoriteBorder,
                    contentDescription = "Unchecked",
                    label = "Unchecked",
                )
            }
        }

        // Disabled
        ToggleSection(title = "Disabled") {
            Row(horizontalArrangement = Arrangement.spacedBy(ToolkitSpacing.sm)) {
                ToggleIconButton(
                    checked = true,
                    onCheckedChange = {},
                    checkedIcon = Icons.Filled.Favorite,
                    uncheckedIcon = Icons.Filled.FavoriteBorder,
                    contentDescription = "Disabled Checked",
                    enabled = false,
                    label = "Checked",
                )
                ToggleIconButton(
                    checked = false,
                    onCheckedChange = {},
                    checkedIcon = Icons.Filled.Favorite,
                    uncheckedIcon = Icons.Filled.FavoriteBorder,
                    contentDescription = "Disabled Unchecked",
                    enabled = false,
                    label = "Unchecked",
                )
            }
        }

        Spacer(Modifier.height(ToolkitSpacing.lg))
    }
}

@Composable
private fun ToggleSection(
    title: String,
    content: @Composable () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(ToolkitSpacing.xs)) {
        Text(text = title, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        HorizontalDivider()
        Spacer(Modifier.height(2.dp))
        content()
    }
}

@Preview(showBackground = true)
@Composable
private fun ToggleIconButtonShowcaseScreenPreview() {
    ToolkitTheme {
        ToggleIconButtonShowcaseScreen()
    }
}
