package de.nordakademie.core.terminator;

import de.nordakademie.implementation.gridStructure.IGridStructure;
import de.nordakademie.implementation.simulationState.ISimulationState;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/**
 * Test class for the CombinedTerminator.
 * This class contains unit tests to verify the behavior of the CombinedTerminator.
 */
public class CombinedTerminatorTest {
    CombinedTerminator combinedTerminator;
    private IGridStructure currentGrid;
    private IGridStructure previousGrid;
    private ISimulationState simulationState;

    /**
     * Sets up the test environment before each test.
     * Initializes two mock IGridStructures, a mock ISimulationState as well as a StableStateTerminator.
     */
    @BeforeEach
    public void setUp() {
        combinedTerminator = new CombinedTerminator(10);
        this.currentGrid = mock(IGridStructure.class);
        when(currentGrid.getHeight()).thenReturn(3);
        when(currentGrid.getWidth()).thenReturn(3);
        when(currentGrid.getCellState(0,0)).thenReturn(0);
        when(currentGrid.getCellState(0,1)).thenReturn(1);
        when(currentGrid.getCellState(0,2)).thenReturn(0);
        when(currentGrid.getCellState(1,0)).thenReturn(0);
        when(currentGrid.getCellState(1,1)).thenReturn(1);
        when(currentGrid.getCellState(1,2)).thenReturn(0);
        when(currentGrid.getCellState(2,0)).thenReturn(1);
        when(currentGrid.getCellState(2,1)).thenReturn(1);
        when(currentGrid.getCellState(2,2)).thenReturn(0);

        this.previousGrid = mock(IGridStructure.class);
        when(previousGrid.getHeight()).thenReturn(3);
        when(previousGrid.getWidth()).thenReturn(3);

        this.simulationState = mock(ISimulationState.class);
        when(simulationState.getCurrentGrid()).thenReturn(currentGrid);
        when(simulationState.getPreviousGrid()).thenReturn(previousGrid);
    }

    /**
     * Tests the termination criterion when the step count is below the maximum and the grids are not equal.
     * Verifies that the CombinedTerminator returns false in this case.
     */
    @Test
    public void testReturnFalseBeforeExceedingMaxStepsAndIfCurrentGridAndPreviousGridAreNotEqual() {
        when(previousGrid.getCellState(0,0)).thenReturn(0);
        when(previousGrid.getCellState(0,1)).thenReturn(1);
        when(previousGrid.getCellState(0,2)).thenReturn(0);
        when(previousGrid.getCellState(1,0)).thenReturn(1);
        when(previousGrid.getCellState(1,1)).thenReturn(1);
        when(previousGrid.getCellState(1,2)).thenReturn(0);
        when(previousGrid.getCellState(2,0)).thenReturn(1);
        when(previousGrid.getCellState(2,1)).thenReturn(1);
        when(previousGrid.getCellState(2,2)).thenReturn(1);

        when(simulationState.getCurrentSimulationStep()).thenReturn(5);

        boolean result = combinedTerminator.shouldTerminate(simulationState);

        verify(currentGrid, times(4)).getCellState(any(Integer.class), any(Integer.class));
        verify(previousGrid, times(4)).getCellState(any(Integer.class), any(Integer.class));
        assertFalse(result);
    }

    /**
     * Tests the termination criterion when the step count exceeds the maximum while the grids are not equal.
     * Verifies that the CombinedTerminator returns true in this case.
     */
    @Test
    public void testReturnTrueAfterExceedingMaxStepsAndIfCurrentGridAndPreviousGridAreNotEqual() {
        when(previousGrid.getCellState(0,0)).thenReturn(0);
        when(previousGrid.getCellState(0,1)).thenReturn(1);
        when(previousGrid.getCellState(0,2)).thenReturn(0);
        when(previousGrid.getCellState(1,0)).thenReturn(1);
        when(previousGrid.getCellState(1,1)).thenReturn(1);
        when(previousGrid.getCellState(1,2)).thenReturn(0);
        when(previousGrid.getCellState(2,0)).thenReturn(1);
        when(previousGrid.getCellState(2,1)).thenReturn(1);
        when(previousGrid.getCellState(2,2)).thenReturn(1);

        when(simulationState.getCurrentSimulationStep()).thenReturn(11);

        boolean result = combinedTerminator.shouldTerminate(simulationState);

        verify(currentGrid, times(4)).getCellState(any(Integer.class), any(Integer.class));
        verify(previousGrid, times(4)).getCellState(any(Integer.class), any(Integer.class));
        assertTrue(result);
    }

    /**
     * Tests the termination criterion when the step count is below the maximum and the grids are equal.
     * Verifies that the CombinedTerminator returns true due to grid stability.
     */
    @Test
    public void testReturnTrueBeforeExceedingMaxStepsAndIfCurrentGridAndPreviousGridAreEqual() {
        when(previousGrid.getCellState(0,0)).thenReturn(0);
        when(previousGrid.getCellState(0,1)).thenReturn(1);
        when(previousGrid.getCellState(0,2)).thenReturn(0);
        when(previousGrid.getCellState(1,0)).thenReturn(0);
        when(previousGrid.getCellState(1,1)).thenReturn(1);
        when(previousGrid.getCellState(1,2)).thenReturn(0);
        when(previousGrid.getCellState(2,0)).thenReturn(1);
        when(previousGrid.getCellState(2,1)).thenReturn(1);
        when(previousGrid.getCellState(2,2)).thenReturn(0);

        when(simulationState.getCurrentSimulationStep()).thenReturn(9);

        boolean result = combinedTerminator.shouldTerminate(simulationState);

        verify(currentGrid, times(9)).getCellState(any(Integer.class), any(Integer.class));
        verify(previousGrid, times(9)).getCellState(any(Integer.class), any(Integer.class));
        assertTrue(result);
    }

    /**
     * Tests the termination criterion when the step count exceeds the maximum and the grids are equal.
     * Verifies that the CombinedTerminator returns true in this case.
     */
    @Test
    public void testReturnTrueAfterExceedingMaxStepsAndIfCurrentGridAndPreviousGridAreEqual() {
        when(previousGrid.getCellState(0,0)).thenReturn(0);
        when(previousGrid.getCellState(0,1)).thenReturn(1);
        when(previousGrid.getCellState(0,2)).thenReturn(0);
        when(previousGrid.getCellState(1,0)).thenReturn(0);
        when(previousGrid.getCellState(1,1)).thenReturn(1);
        when(previousGrid.getCellState(1,2)).thenReturn(0);
        when(previousGrid.getCellState(2,0)).thenReturn(1);
        when(previousGrid.getCellState(2,1)).thenReturn(1);
        when(previousGrid.getCellState(2,2)).thenReturn(0);

        when(simulationState.getCurrentSimulationStep()).thenReturn(11);

        boolean result = combinedTerminator.shouldTerminate(simulationState);

        verify(currentGrid, times(9)).getCellState(any(Integer.class), any(Integer.class));
        verify(previousGrid, times(9)).getCellState(any(Integer.class), any(Integer.class));
        assertTrue(result);
    }

    @Test
    void testThrowingIllegalStateExceptionWhenCurrentGridIsNull() {
        when(simulationState.getCurrentGrid()).thenReturn(null);
        Assert.assertThrows(IllegalStateException.class, () -> combinedTerminator.shouldTerminate(simulationState));
    }

    @Test
    void testThrowingIllegalStateExceptionWhenPreviousGridIsNull() {
        when(simulationState.getPreviousGrid()).thenReturn(null);
        Assert.assertThrows(IllegalStateException.class, () -> combinedTerminator.shouldTerminate(simulationState));
    }

    /**
     * Test case: Constructor with invalid max steps (zero or negative)
     * Expected: The constructor should throw an IllegalArgumentException for invalid inputs
     */
    @Test
    public void testConstructorInvalidMaxStepsThrowsException() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new FixedStepTerminator(0));
        assertThrows(IllegalArgumentException.class, () -> new FixedStepTerminator(-5));
    }
}
