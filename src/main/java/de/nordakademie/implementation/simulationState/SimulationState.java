package de.nordakademie.implementation.simulationState;

import de.nordakademie.core.errorHandler.ErrorCode;
import de.nordakademie.implementation.gridStructure.IGridStructure;

/**
 * Implements the current state of one simulation step.
 *
 * This implementation stores information about the current GridStructure after completely the states of all
 * its cells were changed, the previous GridStructure of the previous simulation step as well as
 * the current simulation step which is currently being performed.
 */
public class SimulationState implements ISimulationState {
    IGridStructure currentGrid;
    IGridStructure previousGrid;
    int currentSimulationStep;

    /**
     * Constructs a new SimulationState.
     *
     * @param currentGrid           current GridStructure at the end of the current simulation step
     * @param previousGrid          GridStructure of the previous simulation step
     * @param currentSimulationStep current step of the simulation
     */
    public SimulationState (IGridStructure currentGrid, IGridStructure previousGrid, int currentSimulationStep) {
        if (currentSimulationStep < 1) {
            throw new ExceptionInInitializerError(ErrorCode.SIMULATION_STATE_INVALID_STEP.getMessage() + " [" + ErrorCode.SIMULATION_STATE_INVALID_STEP.getCode() + "]");
        }
        if (currentGrid == null) {
            throw new ExceptionInInitializerError(ErrorCode.SIMULATION_STATE_CURRENT_GRID_NULL.getMessage() + " [" + ErrorCode.SIMULATION_STATE_CURRENT_GRID_NULL.getCode() + "]");
        }
        if (previousGrid == null) {
            throw new ExceptionInInitializerError(ErrorCode.SIMULATION_STATE_PREVIOUS_GRID_NULL.getMessage() + " [" + ErrorCode.SIMULATION_STATE_PREVIOUS_GRID_NULL.getCode() + "]");
        }

        this.currentGrid = currentGrid;
        this.previousGrid = previousGrid;
        this.currentSimulationStep = currentSimulationStep;
    }

    /**
     * Returns the current GridStructure at the end of the current simulation step.
     *
     * @return current GridStructure at the end of the current simulation step
     */
    public IGridStructure getCurrentGrid() {
        return currentGrid;
    }

    /**
     * Setter method for the currentGrid.
     *
     * @param newCurrentGrid new current Grid of the current simulation step
     */
    @Override
    public void setCurrentGrid(IGridStructure newCurrentGrid) {
        this.currentGrid = newCurrentGrid;
    }

    /**
     * Returns the previous GridStructure at the end of the previous simulation step.
     *
     * @return previous GridStructure at the end of the previous simulation step
     */
    public IGridStructure getPreviousGrid() {
        return previousGrid;
    }

    /**
     * Setter method for the previousGrid.
     *
     * @param newPreviousGrid new previous Grid of the current simulation step. (Old currentGrid)
     */
    @Override
    public void setPreviousGrid(IGridStructure newPreviousGrid) {
        this.previousGrid = newPreviousGrid;
    }

    /**
     * Returns the current simulation step that is currently being performed.
     *
     * @return current simulation step
     */
    public int getCurrentSimulationStep() {
        return currentSimulationStep;
    }

    /**
     * Setter method for the currentSimulationStep.
     *
     * @param newCurrentSimulationStep new current simulation step.
     */
    @Override
    public void setCurrentSimulationStep(int newCurrentSimulationStep) {
        if (newCurrentSimulationStep < 1) {
            throw new IllegalArgumentException(ErrorCode.SIMULATION_STATE_INVALID_STEP.getMessage() + " [" + ErrorCode.SIMULATION_STATE_INVALID_STEP.getCode() + "]");
        }
        this.currentSimulationStep = newCurrentSimulationStep;
    }
}
