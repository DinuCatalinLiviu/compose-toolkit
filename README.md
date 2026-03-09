# compose-toolkit

A reusable Jetpack Compose UI component library with a customisable Material 3 theming layer.

## Setup

### Local (personal projects)

```bash
./gradlew publishToMavenLocal
```

```kotlin
// settings.gradle.kts
repositories {
    google()
    mavenCentral()
    mavenLocal()
}
```

```kotlin
// build.gradle.kts
implementation("com.yourorg.toolkit:toolkit-core:0.1.0")
implementation("com.yourorg.toolkit:toolkit-button:0.1.0")
```

### JitPack

```kotlin
// settings.gradle.kts
repositories {
    google()
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}
```

```kotlin
// build.gradle.kts
implementation("com.github.DinuCatalinLiviu.compose-toolkit:toolkit-core:VERSION")
implementation("com.github.DinuCatalinLiviu.compose-toolkit:toolkit-button:VERSION")
```

---

## Components

| Module | Artifact | Docs |
|--------|----------|------|
| `toolkit-core` | `com.yourorg.toolkit:toolkit-core` | [Theming](docs/theming.md) |
| `toolkit-button` | `com.yourorg.toolkit:toolkit-button` | [Button](docs/components/button.md) |

---

## Theming

All toolkit components read their colors, typography, and spacing from `ToolkitTheme`.
Wrap your app once and every component picks up your brand identity automatically:

```kotlin
ToolkitTheme(colorTokens = AcmeColorTokens) {
    AcmeApp()
}
```

`AcmeColorTokens` is your implementation of `ToolkitColorTokens` — a simple interface
where you declare one set of light colors and one set of dark colors. The library handles
switching based on the system setting.

See the full guide at **[docs/theming.md](docs/theming.md)**.