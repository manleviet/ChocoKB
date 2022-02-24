/*
 * ChocoKB: Constraint-based Knowledge Bases for Choco Solver
 *
 * Copyright (c) 2021-2022
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.knowledgebases.core;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.BoolVar;
import org.chocosolver.solver.variables.IntVar;

import java.util.List;
import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkElementIndex;

@Getter
@ToString
public abstract class KB {
    protected final String name;
    protected final String source;

    @ToString.Exclude
    protected final boolean hasNegativeConstraints;

    @ToString.Exclude
    protected Model modelKB;

    // Variables
    @ToString.Exclude
    protected List<Variable> variableList;

    // Domains
    @ToString.Exclude
    protected List<Domain> domainList;

    // Constraints
    @ToString.Exclude
    protected List<Constraint> constraintList;

    protected KB(String name, String source, boolean hasNegativeConstraints) {
        this.name = name;
        this.source = source;
        this.hasNegativeConstraints = hasNegativeConstraints;
    }

    public abstract void reset(boolean hasNegativeConstraints);

    public abstract void defineVariables();
    public abstract void defineConstraints(boolean hasNegativeConstraints);

    // Domains
    public int getNumDomains() {
        return domainList.size();
    }

    public Domain getDomain(@NonNull String variable) {
        for (Domain domain : domainList) {
            if (domain.isDomainOf(variable)) {
                return domain;
            }
        }
        throw new IllegalArgumentException("Variable " + variable + " does not exist");
    }

    // Variables
    public int getNumVariables() {
        return variableList.size();
    }

    public Variable getVariable(int index) {
        checkElementIndex(index, variableList.size(), "Index out of bounds");

        return variableList.get(index);
    }

    public Variable getVariable(@NonNull String name) {
        for (Variable var: variableList) {
            if (var.getName().equals(name)) {
                return var;
            }
        }
        throw new IllegalArgumentException("Variable " + name + " does not exist");
    }

    // Choco vars
    public int getNumChocoVars() {
        return getModelKB().getNbVars();
    }

    public abstract IntVar[] getIntVars();
    public abstract IntVar getIntVar(@NonNull String variable);

    public abstract BoolVar[] getBoolVars();
    public abstract BoolVar getBoolVar(@NonNull String variable);

    // Choco value
    public abstract int getIntValue(@NonNull String var, @NonNull String value);
    public abstract boolean getBoolValue(@NonNull String var, @NonNull String value);

    public String getValue(@NonNull String var, int index) {
        Domain domain = getDomain(var);

        return domain.getValue(index);
    }

    // Constraints
    public int getNumConstraints() {
        return constraintList.size();
    }

    public Constraint getConstraint(int index) {
        checkElementIndex(index, constraintList.size(), "Index out of bounds");

        return constraintList.get(index);
    }

    // Choco constraints
    public int getNumChocoConstraints() {
        return modelKB.getNbCstrs();
    }

    // Configuration size
    public double getConfigurationSize() {
        return IntStream.range(0, getNumVariables()).mapToDouble(i -> domainList.get(i).size()).reduce(1, (a, b) -> a * b);
    }
}
