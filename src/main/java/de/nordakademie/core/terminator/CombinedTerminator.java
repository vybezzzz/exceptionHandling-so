package de.nordakademie.core.terminator;

import de.nordakademie.implementation.simulationState.ISimulationState;
/**
 * CombinedTerminator class checks if the simulation should terminate either by reaching a fixed number of steps
 * or when the simulation state becomes stable (unchanging).
 * This class combines the functionality of FixedStepTerminator and StableStateTerminator to provide a dual termination criterion.
 */
public class CombinedTerminator implements ITerminator{
    FixedStepTerminator fixedStepTerminator;
    StableStateTerminator stableStateTerminator;

    /**
     * Constructs a CombinedTerminator with a specified maximum number of steps for termination.
     *
     * @param maxSteps The maximum number of steps after which the simulation should terminate if not already stable.
     */
    public CombinedTerminator(int maxSteps) throws IllegalArgumentException {
        this.fixedStepTerminator = new FixedStepTerminator(maxSteps);
        this.stableStateTerminator = new StableStateTerminator();
    }

    /**
     * Determines whether the simulation should terminate based on two criteria:
     * a fixed number of steps or a stable state.
     *
     * @param simulationState The current state of the simulation, containing the grid structure.
     * @return true if the simulation should terminate based on either the fixed step count or stable state,
     * false otherwise.
     */
    @Override
    public boolean shouldTerminate(ISimulationState simulationState) throws IllegalStateException {
        boolean resultFixedStep = fixedStepTerminator.shouldTerminate(simulationState);
        boolean resultStableState = stableStateTerminator.shouldTerminate(simulationState);

        return resultFixedStep || resultStableState;
    }
}
