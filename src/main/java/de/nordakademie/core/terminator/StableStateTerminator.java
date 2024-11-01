package de.nordakademie.core.terminator;

import de.nordakademie.core.errorHandler.ErrorCode;
import de.nordakademie.implementation.gridStructure.IGridStructure;
import de.nordakademie.implementation.simulationState.ISimulationState;

/**
 * FixedStepTerminator is used to validate the termination after a fixed number of steps.
 * This class implements the ITerminator interface and provides a mechanism to stop
 * a simulation after a predetermined number of steps have been executed.
 */
public class StableStateTerminator implements ITerminator {

    /**
     * Determines whether the simulation should terminate based on the currentGrid and previousGrid of the current
     * SimulationState.
     *
     * @param simulationState The current state of the simulation, containing the currentGrid and previousGrid.
     * @return true if the state eof every Cell of currentGrid is the same as the states of every Cell of previousGrid
     *         step is greater than or equal to the maximum steps,
     *         false otherwise.
     */
    public boolean shouldTerminate(ISimulationState simulationState) throws IllegalStateException{
        boolean result;
        IGridStructure currentGrid = simulationState.getCurrentGrid();
        IGridStructure previousGrid = simulationState.getPreviousGrid();

        //ExceptionHandling
        if (currentGrid == null) {
            throw new IllegalStateException(ErrorCode.TERMINATOR_CURRENT_GRID_NULL.getMessage() + " [" + ErrorCode.TERMINATOR_CURRENT_GRID_NULL.getCode() + "]");
        }
        if (previousGrid == null) {
            throw new IllegalStateException(ErrorCode.TERMINATOR_PREVIOUS_GRID_NULL.getMessage() + " [" + ErrorCode.TERMINATOR_PREVIOUS_GRID_NULL.getCode() + "]");
        }

        for (int y = 0; y < currentGrid.getHeight(); y++) {
            for (int x = 0; x < currentGrid.getWidth(); x++) {
                int currentStateOfCell = currentGrid.getCellState(y, x);
                int previousStateOfCell = previousGrid.getCellState(y, x);

                result = currentStateOfCell == previousStateOfCell;
                if (!result) {
                    return false;
                }
            }
        }

        return true;
    }
}
