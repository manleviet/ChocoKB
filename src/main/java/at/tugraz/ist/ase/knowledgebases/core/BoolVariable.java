/*
 * ChocoKB: Constraint-based Knowledge Bases for Choco Solver
 *
 * Copyright (c) 2021.
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.knowledgebases.core;

import lombok.*;
import org.chocosolver.solver.variables.BoolVar;

@EqualsAndHashCode(callSuper = true)
@Getter
public class BoolVariable extends Variable implements Cloneable {
    private BoolVar chocoVar;

    @Builder
    public BoolVariable(@NonNull String name, @NonNull Domain domain, @NonNull BoolVar chocoVar) {
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
    public BoolVariable clone() {
        BoolVariable clone = (BoolVariable) super.clone();
        clone.chocoVar = chocoVar;
        return clone;
    }
}
