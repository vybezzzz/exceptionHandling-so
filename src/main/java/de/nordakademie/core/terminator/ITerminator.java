package de.nordakademie.core.terminator;

import de.nordakademie.implementation.simulationState.ISimulationState;
/**
 * Interface representing a termination condition for the simulation.
 */

public interface ITerminator {
    boolean shouldTerminate(ISimulationState simulationState);
}
