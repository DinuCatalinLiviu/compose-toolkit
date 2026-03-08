package com.yourorg.toolkit.button

import org.junit.Assert.assertEquals
import org.junit.Test

class ButtonVariantTest {

    @Test
    fun buttonVariant_allValuesPresent() {
        val variants = ButtonVariant.values()
        assertEquals(4, variants.size)
        assert(ButtonVariant.Primary in variants)
        assert(ButtonVariant.Secondary in variants)
        assert(ButtonVariant.Ghost in variants)
        assert(ButtonVariant.Destructive in variants)
    }
}
