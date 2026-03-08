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
| `toolkit-button` | `com.yourorg.toolkit:toolkit-button` | [Button](docs/components/button.md) |