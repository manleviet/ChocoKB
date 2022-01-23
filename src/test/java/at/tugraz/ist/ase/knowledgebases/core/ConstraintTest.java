/*
 * ChocoKB: Constraint-based Knowledge Bases for Choco Solver
 *
 * Copyright (c) 2021-2022
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.knowledgebases.core;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstraintTest {
    static Constraint c1;
    static Constraint c2;
    static Constraint c3;

    static org.chocosolver.solver.constraints.Constraint chocoCstr1;
    static org.chocosolver.solver.constraints.Constraint chocoCstr2;
    static org.chocosolver.solver.constraints.Constraint chocoCstr3;
    static org.chocosolver.solver.constraints.Constraint chocoCstr4;

    @BeforeAll
    static void setUp() {
        Model model = new Model();

        IntVar x = model.intVar("x", 0, 10);

        chocoCstr1 = model.arithm(x, "=", 1);
        chocoCstr2 = model.arithm(x, "=", 2);
        chocoCstr3 = model.arithm(x, "=", 3);
        chocoCstr4 = model.arithm(x, "=", 4);

        c1 = new Constraint("c1");
        c1.addChocoConstraint(chocoCstr1);
        c1.addChocoConstraint(chocoCstr2);
        c1.addNegChocoConstraint(chocoCstr3);
        c1.addNegChocoConstraint(chocoCstr4);

        c2 = new Constraint("c1");
        c3 = new Constraint("c2");
    }

    @Test
    void testConstraint() {
        assertAll(() -> assertEquals("c1", c1.getConstraint()),
                () -> assertEquals("ARITHM ([x = 1])", c1.getChocoConstraints().get(0).toString()),
                () -> assertEquals("ARITHM ([x = 2])", c1.getChocoConstraints().get(1).toString()),
                () -> assertEquals(2, c1.getChocoConstraints().size()),
                () -> assertEquals("ARITHM ([x = 3])", c1.getNegChocoConstraints().get(0).toString()),
                () -> assertEquals("ARITHM ([x = 4])", c1.getNegChocoConstraints().get(1).toString()),
                () -> assertEquals(2, c1.getNegChocoConstraints().size()),
                () -> assertTrue(c1.contains(chocoCstr1)),
                () -> assertFalse(c1.contains(chocoCstr3)),
                () -> assertEquals(c1, c2),
                () -> assertNotEquals(c1, c3));
    }
}