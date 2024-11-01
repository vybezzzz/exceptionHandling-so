package de.nordakademie.core.ruleSet;

import de.nordakademie.core.errorHandler.ErrorCode;
import de.nordakademie.implementation.cell.ICell;
/**
 * ParityRuleSet calculates the next state of a cell based on the parity of the number of living neighbors.
 */
public class ParityRuleSet implements IRuleSet{

    @Override
    public int computeNextState(ICell cell){
        int nextState;

        //cell.setState(1);
        int aliveNeighbors = cell.getAliveNeighbors();

        //ExceptionHandling
        if (aliveNeighbors < 0 || aliveNeighbors > 4) {
            throw new IllegalArgumentException(ErrorCode.RULESET_INVALID_ALIVE_NEIGHBORS.getMessage() + aliveNeighbors + " [" + ErrorCode.RULESET_INVALID_ALIVE_NEIGHBORS.getCode() + "]");
        }

        nextState = (aliveNeighbors % 2 == 1) ? 1 : 0;
        return nextState;
    }
}
