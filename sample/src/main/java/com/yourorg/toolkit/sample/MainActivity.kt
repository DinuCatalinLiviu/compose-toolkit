package com.yourorg.toolkit.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import com.yourorg.toolkit.button.Button
import com.yourorg.toolkit.core.theme.ToolkitColorTokens
import com.yourorg.toolkit.core.theme.ToolkitSpacing
import com.yourorg.toolkit.core.theme.ToolkitTheme

enum class ShowcaseDestination(val label: String) {
    Button("Button Showcase"),
}

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToolkitTheme(colorTokens = ToolkitColorTokens.default()) {
                var current by remember { mutableStateOf<ShowcaseDestination?>(null) }

                // Handle system back press to return to the home screen
                onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        if (current != null) current = null else finish()
                    }
                })

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text(current?.label ?: "Toolkit Showcase") },
                            navigationIcon = {
                                if (current != null) {
                                    IconButton(onClick = { current = null }) {
                                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                                    }
                                }
                            },
                        )
                    },
                ) { innerPadding ->
                    when (current) {
                        ShowcaseDestination.Button -> ButtonShowcaseScreen(
                            modifier = Modifier.padding(innerPadding),
                        )
                        null -> HomeScreen(
                            modifier = Modifier.padding(innerPadding),
                            onNavigate = { current = it },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    onNavigate: (ShowcaseDestination) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(ToolkitSpacing.md),
        verticalArrangement = Arrangement.spacedBy(ToolkitSpacing.sm),
    ) {
        ShowcaseDestination.entries.forEach { destination ->
            Button(
                onClick = { onNavigate(destination) },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(destination.label)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    ToolkitTheme {
        HomeScreen(onNavigate = {})
    }
}
