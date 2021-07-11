/*
 * ChocoKB: Constraint-based Knowledge Bases for Choco Solver
 *
 * Copyright (c) 2021.
 *
 * Author: Le Viet Man
 * Email: manleviet@gmail.com
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

    void defineVariables();
    void defineConstraints();

    int [][] getDomains();
}
