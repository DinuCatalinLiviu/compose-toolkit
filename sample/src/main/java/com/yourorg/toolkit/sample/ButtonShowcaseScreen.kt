package com.yourorg.toolkit.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

import com.yourorg.toolkit.button.Button
import com.yourorg.toolkit.button.ButtonSize
import com.yourorg.toolkit.button.ButtonVariant
import com.yourorg.toolkit.core.theme.ToolkitSpacing
import com.yourorg.toolkit.core.theme.ToolkitTheme

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ButtonShowcaseScreen(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    var loadingVariant by remember { mutableStateOf<ButtonVariant?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(ToolkitSpacing.md),
        verticalArrangement = Arrangement.spacedBy(ToolkitSpacing.sm),
    ) {
        ShowcaseSection(title = "Large — Variants") {
            ButtonVariant.entries.forEach { variant ->
                Button(
                    onClick = {},
                    variant = variant,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(variant.name)
                }
            }
        }

        ShowcaseSection(title = "Large — Disabled") {
            ButtonVariant.entries.forEach { variant ->
                Button(
                    onClick = {},
                    variant = variant,
                    enabled = false,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(variant.name)
                }
            }
        }

        ShowcaseSection(title = "Small — Variants") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(ToolkitSpacing.sm),
            ) {
                ButtonVariant.entries.forEach { variant ->
                    Button(
                        onClick = {},
                        variant = variant,
                        size = ButtonSize.Small,
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(variant.name, fontSize = 11.sp)
                    }
                }
            }
        }

        ShowcaseSection(title = "Small — Disabled") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(ToolkitSpacing.sm),
            ) {
                ButtonVariant.entries.forEach { variant ->
                    Button(
                        onClick = {},
                        variant = variant,
                        size = ButtonSize.Small,
                        enabled = false,
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(variant.name, fontSize = 11.sp)
                    }
                }
            }
        }

        ShowcaseSection(title = "Loading State — tap to trigger") {
            ButtonVariant.entries.forEach { variant ->
                Button(
                    onClick = {
                        scope.launch {
                            loadingVariant = variant
                            delay(2000)
                            loadingVariant = null
                        }
                    },
                    variant = variant,
                    isLoading = loadingVariant == variant,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(variant.name)
                }
            }
        }

        ShowcaseSection(title = "Leading Icon") {
            ButtonVariant.entries.forEach { variant ->
                Button(
                    onClick = {},
                    variant = variant,
                    leadingIcon = { Text("★") },
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(variant.name)
                }
            }
        }

        Spacer(Modifier.height(ToolkitSpacing.lg))
    }
}

@Composable
private fun ShowcaseSection(
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
private fun ButtonShowcaseScreenPreview() {
    ToolkitTheme {
        ButtonShowcaseScreen()
    }
}
