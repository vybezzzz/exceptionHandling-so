package de.nordakademie.core.neighborhoodStrategy;

import de.nordakademie.implementation.gridStructure.IGridStructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MooreNeighborhoodTest {

    private MooreNeighborhood neighborhood;
    private IGridStructure grid;

    @BeforeEach
    void setUp() {
        neighborhood = new MooreNeighborhood();
        grid = mock(IGridStructure.class);
        // setting up a default 5x5 size for the grid structure
        when(grid.getWidth()).thenReturn(5);
        when(grid.getHeight()).thenReturn(5);
    }

    /**
     * Test case: All neighbor cells are dead
     * Expectation: The method should return 0 living neighbors
     */

    @Test
    void testGetAliveNeighborsAllDead() {
        when(grid.getCellState(anyInt(), anyInt())).thenReturn(0);
        assertEquals(0, neighborhood.computeAliveNeighbors(grid, 2, 2));
    }


    /**
     * Test case: All neighbor cells are alive
     * Expectation: The method should return 8 living neighbors
     */
    @Test
    void testGetAliveNeighborsAllAlive() {
        when(grid.getCellState(anyInt(), anyInt())).thenReturn(1);
        assertEquals(8, neighborhood.computeAliveNeighbors(grid, 2, 2));
    }


    /**
     * Test case: Mixed state of the neighbor cells
     * Expectation: The method should return the correct number of 3 living neighbors
     */
    @Test
    void testGetAliveNeighborsMixedStates() {
        when(grid.getCellState(1, 1)).thenReturn(1);
        when(grid.getCellState(1, 2)).thenReturn(0);
        when(grid.getCellState(1, 3)).thenReturn(1);
        when(grid.getCellState(2, 3)).thenReturn(0);
        when(grid.getCellState(3, 3)).thenReturn(1);
        when(grid.getCellState(3, 2)).thenReturn(0);
        when(grid.getCellState(3, 1)).thenReturn(0);
        when(grid.getCellState(2, 1)).thenReturn(0);
        assertEquals(3, neighborhood.computeAliveNeighbors(grid, 2, 2));
    }

    /**
     * Test case: Cell in the upper left corner of the grid
     * Expectation: The method should correctly count only three valid neighbors
     */
    @Test
    void testGetAliveNeighborsTopLeftCorner() {
        when(grid.getCellState(0, 1)).thenReturn(1);
        when(grid.getCellState(1, 0)).thenReturn(1);
        when(grid.getCellState(1, 1)).thenReturn(1);
        assertEquals(3, neighborhood.computeAliveNeighbors(grid, 0, 0));
    }

    /**
     * Test case: Cell in the lower right corner of the grid
     * Expectation: The method should correctly count only three valid neighbors
     */
    @Test
    void testGetAliveNeighborsBottomRightCorner() {
        when(grid.getCellState(3, 4)).thenReturn(1);
        when(grid.getCellState(3, 3)).thenReturn(1);
        when(grid.getCellState(4, 3)).thenReturn(1);
        assertEquals(3, neighborhood.computeAliveNeighbors(grid, 4, 4));
    }

    /** LOOK OVER HERE AGAIN
     * Test case: cell at the top of the grid
     * Expectation: The method should correctly count only valid neighbors
     */
    @Test
    void testGetAliveNeighborsTopEdge() {
        when(grid.getCellState(1, 0)).thenReturn(1);
        when(grid.getCellState(3, 0)).thenReturn(1);
        when(grid.getCellState(1, 1)).thenReturn(1);
        when(grid.getCellState(3, 1)).thenReturn(1);
        when(grid.getCellState(2, 1)).thenReturn(1);
        assertEquals(5, neighborhood.computeAliveNeighbors(grid, 2, 0));
    }

    /**
     * Test case: Ensure that the method does not count the central cell itself
     * Expectation: The method should not include the state of the central cell
     */
    @Test
    void testGetAliveNeighborsDoesNotCountSelf() {
        when(grid.getCellState(2, 2)).thenReturn(1); // Central cell
        assertEquals(0, neighborhood.computeAliveNeighbors(grid, 2, 2));
    }

    @Test
    void testThrowingNullPointerExceptionWhenGridIsNull() {
        assertThrows(NullPointerException.class, () -> neighborhood.computeAliveNeighbors(null, 2, 2));
    }

    @Test
    void testThrowingIllegalArgumentExceptionWhenPositionCoordinatesAreNotInGridsRange() {
        assertThrows(IllegalArgumentException.class, () -> neighborhood.computeAliveNeighbors(grid, -1, 2));
        assertThrows(IllegalArgumentException.class, () -> neighborhood.computeAliveNeighbors(grid, 2, -1));
    }
}
