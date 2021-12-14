/*
 * ChocoKB: Constraint-based Knowledge Bases for Choco Solver
 *
 * Copyright (c) 2021.
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.knowledgebases.core;

import lombok.Getter;
import lombok.NonNull;
import org.chocosolver.solver.Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Getter
public class Constraint {
    private final String constraint;
    private final List<org.chocosolver.solver.constraints.Constraint> chocoConstraints;
    private final List<org.chocosolver.solver.constraints.Constraint> negChocoConstraints;

    public Constraint(@NonNull String constraint) {
        this.constraint = constraint;
        chocoConstraints = new LinkedList<>();
        negChocoConstraints = new LinkedList<>();
    }

    public void addChocoConstraints(Model model, int startIdx, int endIdx, boolean hasNegativeConstraints) {
        org.chocosolver.solver.constraints.Constraint[] constraints = model.getCstrs();

        int index = startIdx;
        while (index <= endIdx - 2) {
            addChocoConstraint(constraints[index]);
            if (hasNegativeConstraints) {
                addNegChocoConstraint(constraints[index]);
            }
            index++;
        }

        addChocoConstraint(constraints[index]);
        if (hasNegativeConstraints) {
            addNegChocoConstraint(constraints[index + 1]);
        }
    }

    public void addChocoConstraint(@NonNull org.chocosolver.solver.constraints.Constraint constraint) {
        chocoConstraints.add(constraint);
    }

    public void addNegChocoConstraint(@NonNull org.chocosolver.solver.constraints.Constraint constraint) {
        negChocoConstraints.add(constraint);
    }

    public boolean contains(@NonNull org.chocosolver.solver.constraints.Constraint constraint) {
        return chocoConstraints.contains(constraint);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Constraint that)) return false;
        return Objects.equals(constraint, that.constraint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(constraint);
    }

    @Override
    public String toString() {
        return constraint;
    }
}
