package de.nordakademie.core.terminator;

import de.nordakademie.implementation.gridStructure.IGridStructure;
import de.nordakademie.implementation.simulationState.ISimulationState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Test class for the StableStateTerminator:
 * This class contains unit tests to verify the behavior of the StableStateTerminator.
 */
public class StableStateTerminatorTest {

    private StableStateTerminator stableStateTerminator;
    private IGridStructure currentGrid;
    private IGridStructure previousGrid;
    private ISimulationState simulationState;

    /**
     * Sets up the test environment before each test.
     * Initializes two mock IGridStructures, a mock ISimulationState as well as a StableStateTerminator.
     */
    @BeforeEach
    public void setUp() {
        this.stableStateTerminator = new StableStateTerminator();
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
     * Tests whether the StableStateTerminator gets the currentGrid and previousGrid from the SimulationState.
     */
    @Test
    public void testReadingCurrentAndPreviousGrid() {
        stableStateTerminator.shouldTerminate(simulationState);

        verify(simulationState).getCurrentGrid();
        verify(simulationState).getPreviousGrid();
    }

    /**
     * Tests whether the StableStateTerminator reads the state of every Cell of the previousGrid.
     */
    @Test
    public void testReadingEveryStateOfCurrentAndPreviousGrid() {
        when(previousGrid.getCellState(0,0)).thenReturn(0);
        when(previousGrid.getCellState(0,1)).thenReturn(1);
        when(previousGrid.getCellState(0,2)).thenReturn(0);
        when(previousGrid.getCellState(1,0)).thenReturn(0);
        when(previousGrid.getCellState(1,1)).thenReturn(1);
        when(previousGrid.getCellState(1,2)).thenReturn(0);
        when(previousGrid.getCellState(2,0)).thenReturn(1);
        when(previousGrid.getCellState(2,1)).thenReturn(1);
        when(previousGrid.getCellState(2,2)).thenReturn(0);
        stableStateTerminator.shouldTerminate(simulationState);

        verify(currentGrid, times(9)).getCellState(any(Integer.class), any(Integer.class));
        verify(previousGrid, times(9)).getCellState(any(Integer.class), any(Integer.class));
    }

    /**
     * Tests whether the StableStateTerminator correctly returns true, if the states of every cell of the currentGrid
     * and previousGrid are equal.
     */
    @Test
    public void testReturnTrueIfCurrentGridAndPreviousGridAreEqual() {
        when(previousGrid.getCellState(0,0)).thenReturn(0);
        when(previousGrid.getCellState(0,1)).thenReturn(1);
        when(previousGrid.getCellState(0,2)).thenReturn(0);
        when(previousGrid.getCellState(1,0)).thenReturn(0);
        when(previousGrid.getCellState(1,1)).thenReturn(1);
        when(previousGrid.getCellState(1,2)).thenReturn(0);
        when(previousGrid.getCellState(2,0)).thenReturn(1);
        when(previousGrid.getCellState(2,1)).thenReturn(1);
        when(previousGrid.getCellState(2,2)).thenReturn(0);

        boolean result = stableStateTerminator.shouldTerminate(simulationState);

        assertEquals(true, result);
    }

    /**
     * Tests whether the StableStateTerminator correctly returns false, if the state of one cell of the currentGrid
     * and previousGrid are not equal in the fourth and last cell.
     */
    @Test
    public void testReturnFalseIfCurrentGridAndPreviousGridAreNotEqual() {
        when(previousGrid.getCellState(0,0)).thenReturn(0);
        when(previousGrid.getCellState(0,1)).thenReturn(1);
        when(previousGrid.getCellState(0,2)).thenReturn(0);
        when(previousGrid.getCellState(1,0)).thenReturn(1);
        when(previousGrid.getCellState(1,1)).thenReturn(1);
        when(previousGrid.getCellState(1,2)).thenReturn(0);
        when(previousGrid.getCellState(2,0)).thenReturn(1);
        when(previousGrid.getCellState(2,1)).thenReturn(1);
        when(previousGrid.getCellState(2,2)).thenReturn(1);

        boolean result = stableStateTerminator.shouldTerminate(simulationState);

        verify(currentGrid, times(4)).getCellState(any(Integer.class), any(Integer.class));
        verify(previousGrid, times(4)).getCellState(any(Integer.class), any(Integer.class));
        assertEquals(false, result);
    }

    @Test
    void testThrowingIllegalStateExceptionWhenCurrentGridIsNull() {
        when(simulationState.getCurrentGrid()).thenReturn(null);
        assertThrows(IllegalStateException.class, () -> stableStateTerminator.shouldTerminate(simulationState));
    }

    @Test
    void testThrowingIllegalStateExceptionWhenPreviousGridIsNull() {
        when(simulationState.getPreviousGrid()).thenReturn(null);
        assertThrows(IllegalStateException.class, () -> stableStateTerminator.shouldTerminate(simulationState));
    }
}
