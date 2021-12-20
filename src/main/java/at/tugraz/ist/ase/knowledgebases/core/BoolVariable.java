/*
 * ChocoKB: Constraint-based Knowledge Bases for Choco Solver
 *
 * Copyright (c) 2021.
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.knowledgebases.core;

import at.tugraz.ist.ase.common.LoggerUtils;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.chocosolver.solver.variables.BoolVar;

@EqualsAndHashCode(callSuper = true)
@Getter
@Slf4j
public class BoolVariable extends Variable implements Cloneable {
    private BoolVar chocoVar;

    @Builder
    public BoolVariable(@NonNull String name, @NonNull Domain domain, @NonNull BoolVar chocoVar) {
        super(name, domain);
        this.chocoVar = chocoVar;

        log.trace("{}Created BoolVariable [var={}]", LoggerUtils.tab, this);
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

        log.trace("{}Cloned BoolVariable [var={}]", LoggerUtils.tab, clone);
        return clone;
    }
}
