/*
 * ChocoKB: Constraint-based Knowledge Bases for Choco Solver
 *
 * Copyright (c) 2021.
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.knowledgebases.pc;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PCKBTest {
    PCKB pckb = new PCKB();

    @BeforeMethod
    public void setUp() {
    }

    @Test
    public void testPCKB() {
        boolean isConsistent = pckb.getModelKB().getSolver().solve();
        assert isConsistent;
    }

    @Test
    public void testDefineVariables() {
//        System.out.println("Number of variables: " + pckb.getNumberOfVariables());
        assert pckb.getNumberOfVariables() == 45;
    }

    @Test
    public void testDefineConstraints() {
//        System.out.println("Number of constraints: " + pckb.getModelKB().getNbCstrs());
        assert pckb.getModelKB().getNbCstrs() == 642;
    }
}