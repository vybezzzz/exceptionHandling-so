package de.nordakademie.core.ruleSet;

import de.nordakademie.implementation.cell.ICell;

/**
 * Interface representing a rule set for cellular automata.
 */
public interface IRuleSet {
    int computeNextState(ICell cell);
}
