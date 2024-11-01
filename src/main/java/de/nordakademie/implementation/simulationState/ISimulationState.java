package de.nordakademie.implementation.simulationState;

import de.nordakademie.implementation.gridStructure.IGridStructure;

/**
 * Implements an Interface to the state of one simulation step
 */
public interface ISimulationState {

    /**
     * Returns the current GridStructure at the end of the current simulation step.
     *
     * @return current GridStructure at the end of the current simulation step
     */
    IGridStructure getCurrentGrid();

    /**
     * Setter method for the currentGrid.
     *
     * @param newCurrentGrid new current Grid of the current simulation step
     */
    void setCurrentGrid(IGridStructure newCurrentGrid);

    /**
     * Returns the previous GridStructure at the end of the previous simulation step.
     *
     * @return previous GridStructure at the end of the previous simulation step
     */
    IGridStructure getPreviousGrid();

    /**
     * Setter method for the previousGrid.
     *
     * @param newPreviousGrid new previous Grid of the current simulation step. (Old currentGrid)
     */
    void setPreviousGrid(IGridStructure newPreviousGrid);

    /**
     * Returns the current simulation step that is currently being performed.
     *
     * @return current simulation step
     */
    int getCurrentSimulationStep();

    /**
     * Setter method for the currentSimulationStep.
     *
     * @param newCurrentSimulationStep new current simulation step.
     */
    void setCurrentSimulationStep(int newCurrentSimulationStep);
}
