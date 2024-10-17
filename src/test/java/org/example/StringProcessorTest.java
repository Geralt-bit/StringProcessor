package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringProcessorTest {

    StringProcessor processor = new StringProcessor();

    @Test
    public void testIsStrongPassword() {
        // Valid passwords
        assertTrue(processor.isStrongPassword("Strong1@"));
        assertTrue(processor.isStrongPassword("Abc123!@#"));

        // Invalid passwords
        assertFalse(processor.isStrongPassword("short"));
        assertFalse(processor.isStrongPassword("nouppercase1@"));
        assertFalse(processor.isStrongPassword("NOLOWERCASE1@"));
        assertFalse(processor.isStrongPassword("NoDigits!@"));
        assertFalse(processor.isStrongPassword("NoSpecialChars1"));
    }

    @Test
    public void testCalculateDigits() {
        assertEquals(0, processor.calculateDigits("No digits here!"));
        assertEquals(6, processor.calculateDigits("123abc456"));
        assertEquals(4, processor.calculateDigits("The year is 2023."));
        assertEquals(0, processor.calculateDigits("!@#$%^&*()"));
    }

    @Test
    public void testCalculateWords() {
        assertEquals(0, processor.calculateWords(null));
        assertEquals(0, processor.calculateWords(""));
        assertEquals(1, processor.calculateWords("Hello"));
        assertEquals(6, processor.calculateWords("Hello world! This is a test."));
        assertEquals(5, processor.calculateWords("One  Two   Three Four Five"));
    }

    @Test
    public void testCalculateExpression() {
        assertEquals(7.0, processor.calculateExpression("3 + 4"));
        assertEquals(1.0, processor.calculateExpression("4 - 3"));
        assertEquals(12.0, processor.calculateExpression("3 * 4"));
        assertEquals(3.0, processor.calculateExpression("12 / 4"));
        assertEquals(11.0, processor.calculateExpression("3 + 4 * 2"));
        assertEquals(1.5, processor.calculateExpression("3 / 2"));
        assertThrows(UnsupportedOperationException.class, () -> processor.calculateExpression("3 / 0"));
        assertEquals(1.0, processor.calculateExpression("((2 + 3) - 4) / 1"));
    }
}
