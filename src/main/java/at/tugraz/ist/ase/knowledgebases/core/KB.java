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
    public Model getModelKB();
    public void setModelKB(Model m);

    public int getNumberOfVariables();
    public void setNumberOfVariables(int n);

    public IntVar[] getVars();
    public void setVars(IntVar[] v);

    public void defineVariables();
    public void defineConstraints();

    public int [][] getDomains();
}
