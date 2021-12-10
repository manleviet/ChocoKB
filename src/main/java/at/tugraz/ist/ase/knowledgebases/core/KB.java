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

import java.util.List;

public interface KB {
    void defineVariables();
    void defineConstraints();

    Model getModelKB();
    void reset();

    int getNumDomains();
    List<Domain> getDomainList();
    Domain getDomain(String variable);

    int getNumVariables();
    List<Variable> getVariableList();
    Variable getVariable(int index);
    Variable getVariable(String name);

    int getNumChocoVars();
    IntVar[] getChocoVars();
    IntVar getIntVar(String variable);

    int getChocoValue(String variable, String value);
    String getValue(String var, int index);

    int getNumConstraints();
    List<Constraint> getConstraintList();
    Constraint getConstraint(int index);

    int getNumChocoConstraints();

    double getConfigurationSize();
}
