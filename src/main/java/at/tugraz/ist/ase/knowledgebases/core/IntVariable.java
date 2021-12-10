/*
 * ChocoKB: Constraint-based Knowledge Bases for Choco Solver
 *
 * Copyright (c) 2021.
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.knowledgebases.core;

import lombok.*;
import org.chocosolver.solver.variables.IntVar;

@EqualsAndHashCode(callSuper = true)
@Getter
public class IntVariable extends Variable implements Cloneable {
    private IntVar chocoVar;

    @Builder
    public IntVariable(@NonNull String name, @NonNull Domain domain, @NonNull IntVar chocoVar) {
        super(name, domain);
        this.chocoVar = chocoVar;
    }

    public String getValue() {
        return domain.getValue(getChocoValue());
    }

    public int getChocoValue() {
        return chocoVar.getValue();
    }

    @Override
    public IntVariable clone() {
        IntVariable clone = (IntVariable) super.clone();
        clone.chocoVar = chocoVar;
        return clone;
    }
}
