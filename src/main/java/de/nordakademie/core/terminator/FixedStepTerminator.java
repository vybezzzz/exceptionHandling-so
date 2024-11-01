package de.nordakademie.core.terminator;


import de.nordakademie.core.errorHandler.ErrorCode;
import de.nordakademie.implementation.simulationState.ISimulationState;

/**
 * FixedStepTerminator is used to validate the termination after a fixed number of steps.
 * This class implements the ITerminator interface and provides a mechanism to stop
 * a simulation after a predetermined number of steps have been executed.
 */

public class FixedStepTerminator implements ITerminator{
    // maximum number of steps after which simulation should terminate
    private final int maxSteps;

    /**
     * constructor for FixedStepTerminator
     *
     * @param maxSteps The maximum number of steps before termination. Must be positive.
     * @throws IllegalArgumentException if maxSteps is not positive.
     */
    public FixedStepTerminator(int maxSteps) {
        if (maxSteps <= 0) {
            throw new IllegalArgumentException(ErrorCode.TERMINATOR_MAX_STEPS.getMessage() + " [" + ErrorCode.TERMINATOR_MAX_STEPS.getCode() + "]");
        }

        this.maxSteps = maxSteps;
    }

    /**
     * Determines whether the simulation should terminate based on the current step.
     *
     * @param simulationState The current state of the simulation.
     * @return true if the current step is greater than or equal to the maximum steps,
     *         false otherwise.
     */
    @Override
    public boolean shouldTerminate(ISimulationState simulationState) {
        return simulationState.getCurrentSimulationStep() > maxSteps;
    }

}
