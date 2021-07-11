/*
 * ChocoKB: Constraint-based Knowledge Bases for Choco Solver
 *
 * Copyright (c) 2021.
 *
 * Author: Le Viet Man
 * Email: manleviet@gmail.com
 */

package at.tugraz.ist.ase.knowledgebases;

import at.tugraz.ist.ase.knowledgebases.renault.RenaultKB;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RenaultKBTest {
    RenaultKB renault;

    @BeforeMethod
    public void setUp() {
        renault = new RenaultKB();
    }

    @Test
    public void testRenaultKB() {
//        for (Constraint c: renault.getModelKB().getCstrs()) {
//            System.out.println(c);
//        }

        boolean isConsistent = renault.getModelKB().getSolver().solve();
        assert isConsistent;

//        if (isConsistent) {
//            System.out.println("OK");
//        } else {
//            System.out.println("NOT OK");
//        }
    }

//    @Test
//    public void testDefineVariableValues() {
//    }
//
//    @Test
//    public void testDefineVariables() {
//    }
//
//    @Test
//    public void testDefineConstraints() {
//    }
//
//    @Test
//    public void testGetModelKB() {
//    }
//
//    @Test
//    public void testSetModelKB() {
//    }

    @Test
    public void testGetNumberOfVariables() {
//        System.out.println(renault.getModelKB().getNbVars());
        assert renault.getModelKB().getNbVars() == 588472;
    }

    @Test
    public void testSetNumberOfVariables() {
//        System.out.println(renault.getModelKB().getNbCstrs());
        assert renault.getModelKB().getNbCstrs() == 392603;
    }

//    @Test
//    public void testGetVars() {
//    }
//
//    @Test
//    public void testSetVars() {
//    }
//
//    @Test
//    public void testArrayToList() {
//    }
//
//    @Test
//    public void testGetDomains() {
//    }
//
//    @Test
//    public void testGetDomainSizes() {
//    }
//
//    @Test
//    public void testGetIndexVariable() {
//    }
//
//    @Test
//    public void testGetIndexValue() {
//    }
}