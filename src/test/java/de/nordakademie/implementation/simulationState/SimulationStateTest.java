package de.nordakademie.implementation.simulationState;

import de.nordakademie.implementation.gridStructure.IGridStructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;

/**
 * Test class for SimulationState.
 * Contains unit tests to verify the functionality of the SimulationState class.
 */
public class SimulationStateTest {
    private SimulationState simulationState;
    private IGridStructure currentGrid;
    private IGridStructure previousGrid;

    /**
     * Sets up the test environment before each test.
     * Initializes a mock IGridStructure and a SimulationState with a mock GridStructure.
     */
    @BeforeEach
    void setUp() {
        currentGrid = mock(IGridStructure.class);
        previousGrid = mock(IGridStructure.class);
        simulationState = new SimulationState(currentGrid, previousGrid, 10);
    }

    @Test
    void testSimulationStateConstuctorThrowingExceptionInInitializerError() {
        assertThrows(ExceptionInInitializerError.class, () -> new SimulationState(null, previousGrid, 10));
        assertThrows(ExceptionInInitializerError.class, () -> new SimulationState(currentGrid, null, 10));
        assertThrows(ExceptionInInitializerError.class, () -> new SimulationState(currentGrid, previousGrid, -1));
    }
    /**
     * Tests whether a newly created SimulationState has stored and returns the correct currentGrid, without changing
     * said currentGrid.
     */
    @Test
    void testGetCurrentGrid() {
        IGridStructure simulationStateCurrentGrid = simulationState.getCurrentGrid();
        assertEquals(currentGrid, simulationStateCurrentGrid);
    }

    /**
     * Tests whether the currentGrid of a simulationState can be set to a new current grid.
     */
    @Test
    void testSetCurrentGrid() {
        IGridStructure newCurrentGrid = mock(IGridStructure.class);
        simulationState.setCurrentGrid(newCurrentGrid);
        IGridStructure simulationStateCurrentGrid = simulationState.getCurrentGrid();
        assertEquals(newCurrentGrid, simulationStateCurrentGrid);
    }

    /**
     * Tests whether a newly created SimulationState has stored and returns the correct previousGrid, without changing
     * said previousGrid.
     */
    @Test
    void testGetPreviousGrid() {
        IGridStructure simulationStatePreviousGrid = simulationState.getPreviousGrid();
        assertEquals(previousGrid, simulationStatePreviousGrid);
    }

    /**
     * Tests whether the previousGrid of a simulationState can be set to a new previous grid.
     */
    @Test
    void testSetPreviousGrid() {
        IGridStructure newPreviousGrid = mock(IGridStructure.class);
        simulationState.setPreviousGrid(newPreviousGrid);
        IGridStructure simulationStatePreviousGrid = simulationState.getPreviousGrid();
        assertEquals(newPreviousGrid, simulationStatePreviousGrid);
    }

    /**
     * Tests whether a newly created SimulationState has stored and returns the correct currentSimulationStep,
     * without changing said currentsSimulationStep.
     */
    @Test
    void testGetCurrentSimulationStep() {
        int simulationStateCurrentStep = simulationState.getCurrentSimulationStep();
        assertEquals(10, simulationStateCurrentStep);
    }

    /**
     * Tests whether the previousGrid of a simulationState can be set to a new previous grid.
     */
    @Test
    void testSetCurrentSimulationStep() {
        simulationState.setCurrentSimulationStep(11);
        int simulationStateCurrentSimulationStep = simulationState.getCurrentSimulationStep();
        assertEquals(11, simulationStateCurrentSimulationStep);
    }

    @Test
    void testSetCurrentSimulationStepThrowingIllegalArgumentExceptionWhenStepIsSmallerThan1() {
        assertThrows(IllegalArgumentException.class, () -> simulationState.setCurrentSimulationStep(0));
    }
}
