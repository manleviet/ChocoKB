/*
 * ChocoKB: Constraint-based Knowledge Bases for Choco Solver
 *
 * Copyright (c) 2021.
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.common;

import at.tugraz.ist.ase.knowledgebases.core.Constraint;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public final class ConstraintUtils {

    public String convertToString(@NonNull Set<Constraint> ac) {
        return ac.stream().map(Constraint::toString).collect(Collectors.joining("\n"));
    }

    public String convertToStringWithMessage(@NonNull List<Set<Constraint>> allDiag, @NonNull String mess) {
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

    public boolean isMinimal(Set<Constraint> diag, List<Set<Constraint>> allDiag) {
        return allDiag.stream().noneMatch(diag::contains);
    }

    public boolean containsAll(List<Set<Constraint>> allDiag, Set<Constraint> diag) {
        return allDiag.stream().anyMatch(adiag -> adiag.containsAll(diag));
    }
}