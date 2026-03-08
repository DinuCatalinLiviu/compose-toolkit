# Button

`toolkit-button` · `com.yourorg.toolkit.button.Button`

A fully themeable button built on top of Material 3, supporting four visual variants that cover the full emphasis range.

---

## Variants

### Primary

High-emphasis filled button. Use for the single primary action on a screen.

![Button Primary](../images/button_primary.png)

```kotlin
Button(onClick = { /* ... */ }) {
    Text("Save")
}
```

---

### Secondary

Medium-emphasis outlined button. Use for secondary or alternative actions.

![Button Secondary](../images/button_secondary.png)

```kotlin
Button(
    onClick = { /* ... */ },
    variant = ButtonVariant.Secondary,
) {
    Text("Cancel")
}
```

---

### Ghost

Low-emphasis text-only button. Use for tertiary or low-priority actions.

![Button Ghost](../images/button_ghost.png)

```kotlin
Button(
    onClick = { /* ... */ },
    variant = ButtonVariant.Ghost,
) {
    Text("Learn more")
}
```

---

### Destructive

High-emphasis filled button using the error color role. Use for irreversible or dangerous actions.

![Button Destructive](../images/button_destructive.png)

```kotlin
Button(
    onClick = onDelete,
    variant = ButtonVariant.Destructive,
) {
    Text("Delete account")
}
```

---

## States

### Disabled

All variants support a disabled state. Pass `enabled = false`.

![Button Disabled](../images/button_disabled.png)

```kotlin
Button(onClick = { }, enabled = false) {
    Text("Unavailable")
}
```

---

## Customisation

### Colors

Override colors per-button without touching the theme:

```kotlin
Button(
    onClick = { /* ... */ },
    colors = ButtonDefaults.colors(variant = ButtonVariant.Primary).copy(
        containerColor = Color(0xFF6200EE),
        contentColor   = Color.White,
    ),
) {
    Text("Custom color")
}
```

### Shape

```kotlin
Button(
    onClick = { /* ... */ },
    shape = RoundedCornerShape(4.dp), // square-ish
) {
    Text("Square button")
}

Button(
    onClick = { /* ... */ },
    shape = CircleShape, // pill
) {
    Text("Pill button")
}
```

### Content padding

```kotlin
Button(
    onClick = { /* ... */ },
    contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
) {
    Text("More padding")
}
```

### Icon + label

```kotlin
Button(onClick = { /* ... */ }) {
    Icon(Icons.Default.Add, contentDescription = null)
    Spacer(Modifier.width(8.dp))
    Text("Add item")
}
```

---

## Theme-level defaults

Button colors are driven by `ToolkitTheme.colorScheme`. Wrap your app once and all buttons pick up your brand colors automatically:

```kotlin
ToolkitTheme(
    colorScheme = ToolkitColorScheme(
        primary   = Color(0xFF6200EE),
        onPrimary = Color.White,
        error     = Color(0xFFB00020),
        onError   = Color.White,
        // ...
    )
) {
    // All Button composables inside here use these colors by default
}
```

---

## Full API reference

```kotlin
@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    variant: ButtonVariant = ButtonVariant.Primary,
    colors: ButtonColors = ButtonDefaults.colors(variant),
    shape: Shape = ButtonDefaults.shape,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
)
```

| Parameter | Type | Default | Description |
|-----------|------|---------|-------------|
| `onClick` | `() -> Unit` | — | Called when the button is tapped |
| `modifier` | `Modifier` | `Modifier` | Applied to the root layout node |
| `enabled` | `Boolean` | `true` | Whether the button can be interacted with |
| `variant` | `ButtonVariant` | `Primary` | Selects the visual style |
| `colors` | `ButtonColors` | `ButtonDefaults.colors(variant)` | Fine-grained color overrides |
| `shape` | `Shape` | `ButtonDefaults.shape` | Corner shape |
| `contentPadding` | `PaddingValues` | `ButtonDefaults.ContentPadding` | Internal padding around content |
| `content` | `@Composable RowScope.() -> Unit` | — | The button's label / icon content |