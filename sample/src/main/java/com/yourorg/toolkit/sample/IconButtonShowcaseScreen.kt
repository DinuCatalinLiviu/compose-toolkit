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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yourorg.toolkit.button.ButtonVariant
import com.yourorg.toolkit.button.IconButton
import com.yourorg.toolkit.button.IconButtonDefaults
import com.yourorg.toolkit.button.IconButtonSize
import com.yourorg.toolkit.core.theme.ToolkitSpacing
import com.yourorg.toolkit.core.theme.ToolkitTheme

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun IconButtonShowcaseScreen(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    var loadingKey by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(ToolkitSpacing.md),
        verticalArrangement = Arrangement.spacedBy(ToolkitSpacing.sm),
    ) {
        // Circle — Variants by size
        IconButtonSection(title = "Circle — Variants") {
            val variants = listOf(ButtonVariant.Primary, ButtonVariant.Secondary, ButtonVariant.Ghost)
            IconButtonSize.entries.forEach { size ->
                Row(horizontalArrangement = Arrangement.spacedBy(ToolkitSpacing.sm)) {
                    variants.forEach { variant ->
                        IconButton(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "${variant.name} ${size.name}",
                            onClick = {},
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
        IconButtonSection(title = "Square — Variants") {
            val variants = listOf(ButtonVariant.Primary, ButtonVariant.Secondary, ButtonVariant.Ghost)
            IconButtonSize.entries.forEach { size ->
                Row(horizontalArrangement = Arrangement.spacedBy(ToolkitSpacing.sm)) {
                    variants.forEach { variant ->
                        IconButton(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "${variant.name} ${size.name}",
                            onClick = {},
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

        // Disabled
        IconButtonSection(title = "Disabled") {
            Row(horizontalArrangement = Arrangement.spacedBy(ToolkitSpacing.sm)) {
                for (variant in listOf(ButtonVariant.Primary, ButtonVariant.Secondary, ButtonVariant.Ghost)) {
                    IconButton(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Disabled ${variant.name}",
                        onClick = {},
                        variant = variant,
                        enabled = false,
                    )
                }
            }
        }

        // Loading
        IconButtonSection(title = "Loading — tap to trigger") {
            Row(horizontalArrangement = Arrangement.spacedBy(ToolkitSpacing.sm)) {
                for (variant in listOf(ButtonVariant.Primary, ButtonVariant.Secondary, ButtonVariant.Ghost)) {
                    val key = "loading-${variant.name}"
                    IconButton(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Loading ${variant.name}",
                        onClick = {
                            scope.launch {
                                loadingKey = key
                                delay(2000)
                                loadingKey = null
                            }
                        },
                        variant = variant,
                        isLoading = loadingKey == key,
                    )
                }
            }
        }

        Spacer(Modifier.height(ToolkitSpacing.lg))
    }
}

@Composable
private fun IconButtonSection(
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
private fun IconButtonShowcaseScreenPreview() {
    ToolkitTheme {
        IconButtonShowcaseScreen()
    }
}