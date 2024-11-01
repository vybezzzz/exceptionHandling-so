package de.nordakademie.view.logger;

import de.nordakademie.implementation.gridStructure.IGridStructure;
import de.nordakademie.implementation.simulationState.ISimulationState;

/**
 * Interface for different output channels for logging during the simulation.
 */
public interface ILogger {

    /**
     * Logs a complete GridStructure for the current simulation step.
     *
     * @param gridStructure GridStructure at the end of the current simulation step
     * @param currentStep   current simulation step
     */
    void log(IGridStructure gridStructure, int currentStep);
    void logEndOfSimulation();
}
