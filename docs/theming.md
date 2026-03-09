# Theming

`toolkit-core` · `com.yourorg.toolkit.core.theme`

The toolkit theming system maps brand color tokens and a custom font family onto Material 3,
so every toolkit component and every standard M3 component in your app picks up your brand
identity from a single call site.

---

## Quick start

Wrap your root content once with `ToolkitTheme`. Supply your brand tokens; the library
handles light/dark switching automatically.

```kotlin
setContent {
    ToolkitTheme(colorTokens = AcmeColorTokens) {
        AcmeApp()
    }
}
```

> `ToolkitColorTokens.default()` provides neutral, unbranded values so the toolkit renders
> correctly out of the box. **Production apps should always supply their own implementation.**

---

## Color tokens

### Implementing ToolkitColorTokens

`ToolkitColorTokens` is an interface with 36 properties split into three groups.
Implement it once for your brand and pass the instance to `ToolkitTheme`.

```kotlin
object AcmeColorTokens : ToolkitColorTokens {

    // ── Light mode – Primary ──────────────────────────────────────────────
    override val primary              = Color(0xFF1A56DB)
    override val onPrimary            = Color(0xFFFFFFFF)
    override val primaryContainer     = Color(0xFFD1E4FF)
    override val onPrimaryContainer   = Color(0xFF001D36)

    // ── Light mode – Secondary ────────────────────────────────────────────
    override val secondary            = Color(0xFF535F70)
    override val onSecondary          = Color(0xFFFFFFFF)
    override val secondaryContainer   = Color(0xFFD7E3F7)
    override val onSecondaryContainer = Color(0xFF101C2B)

    // ── Light mode – Background / Surface ─────────────────────────────────
    override val background           = Color(0xFFFAFCFF)
    override val onBackground         = Color(0xFF1A1C1E)
    override val surface              = Color(0xFFFAFCFF)
    override val onSurface            = Color(0xFF1A1C1E)
    override val surfaceVariant       = Color(0xFFDFE2EB)
    override val onSurfaceVariant     = Color(0xFF43474E)
    override val outline              = Color(0xFF73777F)

    // ── Semantic (mode-agnostic) ──────────────────────────────────────────
    override val success              = Color(0xFF1A7A4A)
    override val onSuccess            = Color(0xFFFFFFFF)
    override val warning              = Color(0xFFB45300)
    override val onWarning            = Color(0xFFFFFFFF)
    override val error                = Color(0xFFBA1A1A)
    override val onError              = Color(0xFFFFFFFF)

    // ── Dark mode – Primary ───────────────────────────────────────────────
    override val primaryDark              = Color(0xFF9ECAFF)
    override val onPrimaryDark            = Color(0xFF003258)
    override val primaryContainerDark     = Color(0xFF004A77)
    override val onPrimaryContainerDark   = Color(0xFFD1E4FF)

    // ── Dark mode – Secondary ─────────────────────────────────────────────
    override val secondaryDark            = Color(0xFFBBC7DB)
    override val onSecondaryDark          = Color(0xFF253140)
    override val secondaryContainerDark   = Color(0xFF3B4858)
    override val onSecondaryContainerDark = Color(0xFFD7E3F7)

    // ── Dark mode – Background / Surface ──────────────────────────────────
    override val backgroundDark           = Color(0xFF1A1C1E)
    override val onBackgroundDark         = Color(0xFFE2E2E6)
    override val surfaceDark              = Color(0xFF1A1C1E)
    override val onSurfaceDark            = Color(0xFFE2E2E6)
    override val surfaceVariantDark       = Color(0xFF43474E)
    override val onSurfaceVariantDark     = Color(0xFFC3C7CF)
    override val outlineDark              = Color(0xFF8D9199)
}
```

> **Warning — semantic tokens are mode-agnostic.**
> `success`, `warning`, `error` and their `on*` counterparts have no dark variant.
> Both `fromTokens` and `darkFromTokens` read the same six properties.
> If you need different values per mode, use the [direct scheme overload](#toolkittheme--direct-scheme-overload).

### Color roles reference

| Group | Properties | Typical use |
|-------|-----------|-------------|
| **Primary** | `primary`, `onPrimary`, `primaryContainer`, `onPrimaryContainer` | Brand CTAs, filled buttons, FABs |
| **Secondary** | `secondary`, `onSecondary`, `secondaryContainer`, `onSecondaryContainer` | Supporting actions, chips, tabs |
| **Background / Surface** | `background`, `onBackground`, `surface`, `onSurface`, `surfaceVariant`, `onSurfaceVariant`, `outline` | Screen backgrounds, cards, dividers |
| **Semantic** | `success`, `onSuccess`, `warning`, `onWarning`, `error`, `onError` | Status indicators, destructive actions, validation |

---

## Dark mode

`ToolkitTheme` calls `isSystemInDarkTheme()` by default, so dark mode works with no extra
setup. Override `darkTheme` when you need to force a mode:

```kotlin
// User preference toggle
val darkTheme by viewModel.darkThemeEnabled.collectAsStateWithLifecycle()

ToolkitTheme(
    colorTokens = AcmeColorTokens,
    darkTheme = darkTheme,
) {
    AcmeApp()
}
```

```kotlin
// Compose preview — pin to dark
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AcmeAppDarkPreview() {
    ToolkitTheme(colorTokens = AcmeColorTokens, darkTheme = true) {
        AcmeApp()
    }
}
```

> `ToolkitColorScheme.fromTokens` and `darkFromTokens` are called internally by the
> token-based overload. Apps using `ToolkitTheme(colorTokens = ...)` do not need to call
> them directly.

---

## Typography

Pass a `fontFamily` to apply your brand typeface across the entire M3 type scale:

```kotlin
// res/font/inter_regular.ttf, inter_medium.ttf, inter_bold.ttf
val InterFamily = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium,  FontWeight.Medium),
    Font(R.font.inter_bold,    FontWeight.Bold),
)

ToolkitTheme(
    colorTokens = AcmeColorTokens,
    fontFamily = InterFamily,
) {
    AcmeApp()
}
```

The family flows through `MaterialTheme.typography`, so M3 components (`Text`, `TopAppBar`,
`Button`, etc.) also pick it up — no additional configuration required.

### Typography scale reference

| Role | Size | Weight | Line height |
|------|------|--------|-------------|
| `displayLarge` | 57 sp | Normal | 64 sp |
| `headlineLarge` | 32 sp | Normal | 40 sp |
| `titleLarge` | 22 sp | Medium | 28 sp |
| `bodyLarge` | 16 sp | Normal | 24 sp |
| `labelLarge` | 14 sp | Medium | 20 sp |

The full 15-role scale mirrors the [Material 3 type system](https://m3.material.io/styles/typography/type-scale-tokens).

---

## Spacing

`ToolkitSpacing` provides static dp constants aligned to a 4 dp base grid.

| Token | Value |
|-------|-------|
| `ToolkitSpacing.xs` | 4 dp |
| `ToolkitSpacing.sm` | 8 dp |
| `ToolkitSpacing.md` | 16 dp |
| `ToolkitSpacing.lg` | 24 dp |
| `ToolkitSpacing.xl` | 32 dp |
| `ToolkitSpacing.xxl` | 48 dp |

```kotlin
Box(Modifier.padding(ToolkitSpacing.md)) { /* … */ }
```

> `ToolkitSpacing` is **not** theme-aware and is **not** injected through `ToolkitTheme`.
> It is a plain object of compile-time constants; use it anywhere, even outside a
> `ToolkitTheme` subtree.

---

## Reading the current theme in custom components

Access the active color scheme inside any composable via `ToolkitTheme.colorScheme`:

```kotlin
@Composable
fun StatusBadge(isOnline: Boolean) {
    val scheme = ToolkitTheme.colorScheme
    val (background, content) = if (isOnline) {
        scheme.success to scheme.onSuccess
    } else {
        scheme.surfaceVariant to scheme.onSurfaceVariant
    }

    Surface(color = background, contentColor = content, shape = CircleShape) {
        Text(
            text = if (isOnline) "Online" else "Offline",
            modifier = Modifier.padding(horizontal = ToolkitSpacing.sm, vertical = ToolkitSpacing.xs),
        )
    }
}
```

> `ToolkitTheme.colorScheme` is `@ReadOnlyComposable` — it can only be called from a
> composable context and does not trigger recomposition on its own.

---

## API reference

### ToolkitTheme — token overload (recommended)

```kotlin
@Composable
fun ToolkitTheme(
    colorTokens: ToolkitColorTokens = ToolkitColorTokens.default(),
    fontFamily: FontFamily = FontFamily.Default,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
)
```

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `colorTokens` | `ToolkitColorTokens` | `ToolkitColorTokens.default()` | Brand color tokens. The library builds light/dark schemes from them automatically. |
| `fontFamily` | `FontFamily` | `FontFamily.Default` | Font family applied to the full M3 typography scale. |
| `darkTheme` | `Boolean` | `isSystemInDarkTheme()` | When `true` the dark variant of the tokens is used. |
| `content` | `@Composable () -> Unit` | — | Screen content rendered inside the theme. |

---

### ToolkitTheme — direct scheme overload

Use when you need per-mode semantic colors or a fully hand-crafted scheme.

```kotlin
@Composable
fun ToolkitTheme(
    colorScheme: ToolkitColorScheme,
    fontFamily: FontFamily = FontFamily.Default,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
)
```

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `colorScheme` | `ToolkitColorScheme` | — | Fully resolved color scheme for the current mode. |
| `fontFamily` | `FontFamily` | `FontFamily.Default` | Font family applied to the full M3 typography scale. |
| `darkTheme` | `Boolean` | `isSystemInDarkTheme()` | Tells M3 whether to use a dark or light color scheme mapping. |
| `content` | `@Composable () -> Unit` | — | Screen content rendered inside the theme. |

---

### ToolkitColorScheme factory functions

| Function | Description |
|----------|-------------|
| `ToolkitColorScheme.fromTokens(tokens)` | Builds a light `ToolkitColorScheme` from `ToolkitColorTokens`. |
| `ToolkitColorScheme.darkFromTokens(tokens)` | Builds a dark `ToolkitColorScheme` from `ToolkitColorTokens`. |
| `ToolkitColorScheme.default()` | Neutral light scheme using `DefaultToolkitColorTokens`. |
| `ToolkitColorScheme.dark()` | Neutral dark scheme using `DefaultToolkitColorTokens`. |

---

### ToolkitTheme.colorScheme

```kotlin
object ToolkitTheme {
    val colorScheme: ToolkitColorScheme
        @Composable @ReadOnlyComposable get()
}
```

Returns the `ToolkitColorScheme` provided by the nearest `ToolkitTheme` ancestor.
Falls back to `ToolkitColorScheme.default()` if no `ToolkitTheme` is present in the tree.
