package de.nordakademie.view.logger;

import de.nordakademie.implementation.gridStructure.IGridStructure;

/**
 * NullLogger class.
 * Does nothing when log is called.
 */
public class NullLogger implements ILogger {

    /**
     * Does nothing.
     *
     * @param gridStructure GridStructure at the end of the current simulation step
     * @param currentStep   current simulation step
     */
    @Override
    public void log(IGridStructure gridStructure, int currentStep) {

        // does nothing
    }

    @Override
    public void logEndOfSimulation() {

        // does nothing
    }
}
