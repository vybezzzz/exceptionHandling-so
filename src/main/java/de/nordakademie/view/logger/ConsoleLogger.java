package de.nordakademie.view.logger;

import de.nordakademie.core.errorHandler.ErrorCode;
import de.nordakademie.implementation.gridStructure.IGridStructure;
import de.nordakademie.implementation.simulationState.ISimulationState;

public class ConsoleLogger implements ILogger {

    /**
     * Logs a complete GridStructure for the current simulation step.
     *
     * @param gridStructure GridStructure at the end of the current simulation step
     * @param currentStep   current simulation step
     */
    @Override
    public void log(IGridStructure gridStructure, int currentStep) throws IllegalArgumentException {
        // ExceptionHandling
        if (gridStructure == null) {
            throw new IllegalArgumentException(ErrorCode.LOGGER_GRID_NULL.getMessage() + " [" + ErrorCode.LOGGER_GRID_NULL.getCode() + "]");
        }
        if (currentStep < 0) {
            throw new IllegalArgumentException(ErrorCode.LOGGER_CURRENT_STEP.getMessage() + " [" + ErrorCode.LOGGER_CURRENT_STEP.getCode() + "]");
        }

        System.out.println("### " + currentStep);
        for (int y = 0; y < gridStructure.getHeight(); y++) {
            for (int x = 0; x < gridStructure.getWidth(); x++) {
                System.out.print(gridStructure.getCellState(y,x));
            }
            System.out.println();
        };
    }

    public void logEndOfSimulation() {
                System.out.println("END OF SIMULATION" + System.lineSeparator());
    }
}
