/*
 * ChocoKB: Constraint-based Knowledge Bases for Choco Solver
 *
 * Copyright (c) 2021.
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.knowledgebases.core;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

public interface KB {
    Model getModelKB();
    void setModelKB(Model m);

    int getNumberOfVariables();
    void setNumberOfVariables(int n);

    IntVar[] getVars();
    void setVars(IntVar[] v);

    void defineDomains();
    void defineVariables();
    void defineConstraints();

    int [][] getDomains();
}
