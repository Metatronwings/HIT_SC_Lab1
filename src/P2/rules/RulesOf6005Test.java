/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.rules;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * JUnit tests for RulesOf6005.
 */
class RulesOf6005Test {
    
    /**
     * Tests the mayUseCodeInAssignment method.
     */
    @Test
    void testMayUseCodeInAssignment() {
        assertFalse(RulesOf6005.mayUseCodeInAssignment(false, true, false, false, false),
                "Expected false: un-cited publicly-available code");
        assertTrue(RulesOf6005.mayUseCodeInAssignment(true, false, true, true, true),
                "Expected true: self-written required code");
    }
}
