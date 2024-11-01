package de.nordakademie.core.ruleSet;

import de.nordakademie.core.errorHandler.ErrorCode;
import de.nordakademie.implementation.cell.ICell;

/**
 * GameOfLifeRuleSet calculates the next state of a cell based on the rules of Game of Life.
 */
public class GameOfLifeRuleSet implements IRuleSet {

    /**
     * Computes the next state of a cell based on the rules of Game of Life:
     * 1. A living cell remains alive if it has exactly 2 or 3 living neighbours. Otherwise, it dies.
     * 2. A dead cell remains dead until it has exactly 3 living neighbours.
     *
     * @param cell the cell which state has been changed (0 or 1)
     * @return the nextState of the Cell
     */
    @Override
    public int computeNextState(ICell cell) {
        int aliveNeighbors = cell.getAliveNeighbors();
        int currentState = cell.getState();
        int nextState;

        //ExceptionHandling
        if (aliveNeighbors < 0 || aliveNeighbors > 8) {
            throw new IllegalArgumentException(ErrorCode.RULESET_INVALID_ALIVE_NEIGHBORS.getMessage() + aliveNeighbors + " [" + ErrorCode.RULESET_INVALID_ALIVE_NEIGHBORS.getCode() + "]");
        }
        if (currentState < 0 || currentState > 1) {
            throw new IllegalArgumentException(ErrorCode.RULESET_INVALID_CURRENT_STATE.getMessage() + currentState + " [" + ErrorCode.RULESET_INVALID_CURRENT_STATE.getCode() + "]");
        }

        if (currentState == 1) {
            nextState = (aliveNeighbors == 2 || aliveNeighbors == 3) ? 1 : 0;
        } else {
            nextState = (aliveNeighbors == 3) ? 1 : 0;
        }

        return nextState;
    }
}