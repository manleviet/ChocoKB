/*
 * ChocoKB: Constraint-based Knowledge Bases for Choco Solver
 *
 * Copyright (c) 2021.
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.knowledgebases.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstraintTest {
    static Constraint c1;
    static Constraint c2;
    static Constraint c3;

    @BeforeAll
    static void setUp() {
        c1 = new Constraint("c1");
        c1.addChocoConstraint("choco1");
        c1.addChocoConstraint("choco2");
        c1.addNegChocoConstraint("choco3");
        c1.addNegChocoConstraint("choco4");

        c2 = new Constraint("c1");
        c3 = new Constraint("c2");
    }

    @Test
    void testConstraint() {
        assertAll(() -> assertEquals("c1", c1.getConstraint()),
                () -> assertEquals("choco1", c1.getChocoConstraints().get(0)),
                () -> assertEquals("choco2", c1.getChocoConstraints().get(1)),
                () -> assertEquals(2, c1.getChocoConstraints().size()),
                () -> assertEquals("choco3", c1.getNegChocoConstraints().get(0)),
                () -> assertEquals("choco4", c1.getNegChocoConstraints().get(1)),
                () -> assertEquals(2, c1.getNegChocoConstraints().size()),
                () -> assertTrue(c1.contains("choco1")),
                () -> assertFalse(c1.contains("choco5")),
                () -> assertEquals(c1, c2),
                () -> assertNotEquals(c1, c3));
    }
}