/*
 * ChocoKB: Constraint-based Knowledge Bases for Choco Solver
 *
 * Copyright (c) 2021.
 *
 * @author: Viet-Man Le (vietman.le@ist.tugraz.at)
 */

package at.tugraz.ist.ase.knowledgebases.core;

import lombok.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Getter
@EqualsAndHashCode
public class Domain implements Cloneable {
    private final String name;
    private final DomainType type;

    private List<String> values;
    private List<Integer> chocoValues;

    /**
     * Constructor for a boolean domain
     *
     * @param name name of the domain
     */
    public Domain(@NonNull String name) {
        this(name, DomainType.BOOL, List.of("false", "true"), List.of(0, 1));
    }

    /**
     * Constructor for a integer domain with a list of values
     *
     * @param name name of the domain
     * @param values list of values
     */
    public Domain(@NonNull String name, @NonNull List<String> values) {
        this(name, DomainType.INT, values, Collections.emptyList());

        initChocoValuesWithDefaultValues();
    }

    /**
     * Constructor for a integer domain with a list of values and a list of choco values
     * @param name name of the domain
     * @param values list of values
     * @param chocoValues list of choco values
     */
    public Domain(@NonNull String name, @NonNull List<String> values, @NonNull List<Integer> chocoValues) {
        this(name, DomainType.INT, values, chocoValues);
    }

    @Builder
    public Domain(@NonNull String name, DomainType type, List<String> values, List<Integer> chocoValues) {
        this.name = name;

        if (type == null) {
            if (values == null) {
                this.type = DomainType.BOOL;
            } else {
                this.type = DomainType.INT;
            }
        } else {
            this.type = type;
        }

        if (values != null) {
            this.values = values;

            if (chocoValues == null) {
                initChocoValuesWithDefaultValues();
            } else {
                this.chocoValues = chocoValues;
            }
        } else {
            this.values = List.of("false", "true");
            this.chocoValues = List.of(0, 1);
        }
    }

    private void initChocoValuesWithDefaultValues() {
        this.chocoValues = new LinkedList<>();
        for (int i = 0; i < this.values.size(); i++) {
            this.chocoValues.add(i);
        }
    }

    public int size() {
        return values.size();
    }

    public int indexOf(@NonNull String value) {
        return values.indexOf(value);
    }

    public String getValue(int chocoValue) {
        if (contains(chocoValue)) {
            return values.get(chocoValues.indexOf(chocoValue));
        }
        return null;
    }

    public int getChocoValue(@NonNull String value) {
        if (contains(value)) {
            return chocoValues.get(values.indexOf(value));
        }
        return -1;
    }

    public int[] getIntValues() {
        return chocoValues.stream().mapToInt(Integer::intValue).toArray();
    }

    public boolean isDomainOf(@NonNull String variable) {
        return variable.equals(name);
    }

    public boolean contains(@NonNull String value) {
        return values.contains(value);
    }

    public boolean contains(int chocoValue) {
        return chocoValues.contains(chocoValue);
    }

    @Override
    public String toString() {
        return name + " = [ " + String.join(", ", values) + " ]";
    }

    @Override
    public Domain clone() {
        try {
            Domain clone = (Domain) super.clone();
            clone.values = new LinkedList<>(values);
            clone.chocoValues = new LinkedList<>(chocoValues);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
