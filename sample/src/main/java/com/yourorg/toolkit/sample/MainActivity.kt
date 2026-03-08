package com.yourorg.toolkit.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yourorg.toolkit.button.Button
import com.yourorg.toolkit.button.ButtonVariant
import com.yourorg.toolkit.core.theme.ToolkitTheme
import com.yourorg.toolkit.core.theme.ToolkitSpacing

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToolkitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SampleScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun SampleScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(ToolkitSpacing.md),
        verticalArrangement = Arrangement.spacedBy(ToolkitSpacing.sm),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Toolkit Button Samples")

        Button(onClick = {}, variant = ButtonVariant.Primary) {
            Text("Primary")
        }

        Button(onClick = {}, variant = ButtonVariant.Secondary) {
            Text("Secondary")
        }

        Button(onClick = {}, variant = ButtonVariant.Ghost) {
            Text("Ghost")
        }

        Button(onClick = {}, variant = ButtonVariant.Destructive) {
            Text("Destructive")
        }

        Button(onClick = {}, enabled = false) {
            Text("Disabled")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SampleScreenPreview() {
    ToolkitTheme {
        SampleScreen()
    }
}
