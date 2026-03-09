package com.yourorg.toolkit.button

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ButtonVariantTest {

    @Test
    fun buttonVariant_allValuesPresent() {
        val variants = ButtonVariant.entries
        assertEquals(4, variants.size)
        assertTrue(ButtonVariant.Primary in variants)
        assertTrue(ButtonVariant.Secondary in variants)
        assertTrue(ButtonVariant.Ghost in variants)
        assertTrue(ButtonVariant.Destructive in variants)
    }
}
