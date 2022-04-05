/*
 * ChocoKB: Constraint-based Knowledge Bases for Choco Solver
 *
 * Copyright (c) 2021-2022
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.common;

import at.tugraz.ist.ase.knowledgebases.core.Constraint;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.chocosolver.solver.Model;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static at.tugraz.ist.ase.eval.evaluator.PerformanceEvaluator.incrementCounter;

@UtilityClass
@Slf4j
public final class ConstraintUtils {

    // for evaluation
    public final String COUNTER_UNPOST_CONSTRAINT = "The number of unpost constraints:";
    public final String COUNTER_POST_CONSTRAINT = "The number of post constraints:";
    public final String COUNTER_CONSTAINS_CONSTRAINT = "The number of contains calls:";

    public String convertToString(@NonNull Set<Constraint> ac) {
        return ac.stream().map(Constraint::toString).collect(Collectors.joining("\n"));
    }

    public String convertToStringWithMessage(@NonNull List<Set<Constraint>> allDiag, @NonNull String mess) {
        if (allDiag.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (Set<Constraint> diag : allDiag) {
            count++;

            sb.append(mess).append(" ").append(count).append(":\n");
//            System.out.println(mess + " " + count + ":");
//            sb.append(String.join("\n", diag));
            sb.append(convertToString(diag)).append("\n");
//            diag.forEach(System.out::println);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public void postConstraints(Collection<Constraint> C, Model toModel) {
        for (Constraint c : C) {
            c.getChocoConstraints().forEach(toModel::post);
            incrementCounter(COUNTER_POST_CONSTRAINT, c.getChocoConstraints().size());
        }
        log.trace("{}Posted constraints", LoggerUtils.tab);
    }

    public void postConstraint(Constraint cstr, Model toModel, boolean negative) {
        if (negative) {
            cstr.getNegChocoConstraints().forEach(toModel::post);
            incrementCounter(COUNTER_POST_CONSTRAINT, cstr.getNegChocoConstraints().size());
        } else {
            cstr.getChocoConstraints().forEach(toModel::post);
            incrementCounter(COUNTER_POST_CONSTRAINT, cstr.getChocoConstraints().size());
        }
        log.trace("{}Posted constraints", LoggerUtils.tab);
    }

    public boolean isMinimal(Set<Constraint> diag, List<Set<Constraint>> allDiag) {
        return allDiag.parallelStream().noneMatch(diag::containsAll);
    }

    public boolean containsAll(List<Set<Constraint>> allDiag, Set<Constraint> diag) {
        return allDiag.parallelStream().anyMatch(adiag -> adiag.containsAll(diag));
    }

    public boolean hasIntersection(Collection<Constraint> col1, Collection<Constraint> col2) {
        return col1.parallelStream().anyMatch(col2::contains);
        /*
        for (Constraint c : col1) {
            if (col2.contains(c)) return true;
        }
        return false;
        */
    }
}
