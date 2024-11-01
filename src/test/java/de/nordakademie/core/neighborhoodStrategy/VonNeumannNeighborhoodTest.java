package de.nordakademie.core.neighborhoodStrategy;

import de.nordakademie.implementation.gridStructure.IGridStructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VonNeumannNeighborhoodTest {

    private VonNeumannNeighborhood neighborhood;
    private IGridStructure grid;

    @BeforeEach
    void setUp() {
        neighborhood = new VonNeumannNeighborhood();
        grid = mock(IGridStructure.class);
        // setting up a default 5x5 size for the grid structure
        when(grid.getWidth()).thenReturn(5);
        when(grid.getHeight()).thenReturn(5);
    }

    /**
     * Test case: All neighboring cells are dead
     * Expected: The method should return 0 alive neighbors
     */
    @Test
    void testGetAliveNeighbors_AllDead() {
        when(grid.getCellState(anyInt(), anyInt())).thenReturn(0);
        assertEquals(0, neighborhood.computeAliveNeighbors(grid, 1, 1));
    }

    /**
     * Test case: All neighboring cells are alive
     * Expected: The method should return 4 alive neighbors
     */
    @Test
    void testGetAliveNeighbors_AllAlive() {
        when(grid.getCellState(anyInt(), anyInt())).thenReturn(1);
        assertEquals(4, neighborhood.computeAliveNeighbors(grid, 1, 1));
    }

    /**
     * Test case: Mixed state of neighboring cells
     * Expected: The method should return the correct count of 2 alive neighbors
     */
    @Test
    void testGetAliveNeighbors_MixedStates() {
        when(grid.getCellState(2, 1)).thenReturn(1);
        when(grid.getCellState(1, 2)).thenReturn(1);
        when(grid.getCellState(3, 2)).thenReturn(0);
        when(grid.getCellState(2, 3)).thenReturn(0);
        assertEquals(2, neighborhood.computeAliveNeighbors(grid, 2, 2));
    }

    /**
     * Test case: Cells at the top-left corner of the grid
     * Expected: The method should correctly count only two valid neighbors, handling the edge case
     */
    @Test
    void testGetAliveNeighbors_TopLeftCorner() {
        when(grid.getCellState(0, 1)).thenReturn(1);
        when(grid.getCellState(1, 0)).thenReturn(1);
        assertEquals(2, neighborhood.computeAliveNeighbors(grid, 0, 0));
    }

    /**
     * Test case: Cell at the bottom-right corner of the grid
     * Expected: The method should correctly count only two valid neighbors, handling the edge case
     */
    @Test
    void testGetAliveNeighbors_BottomRightCorner() {
        when(grid.getCellState(3, 4)).thenReturn(1);
        when(grid.getCellState(4, 3)).thenReturn(1);
        assertEquals(2, neighborhood.computeAliveNeighbors(grid, 4, 4));
    }

    /**
     * Test case: Cell on the top edge of the grid
     * Expected: The method should correctly count only valid neighbors
     */
    @Test
    void testGetAliveNeighbors_TopEdge() {
        when(grid.getCellState(1, 0)).thenReturn(1);
        when(grid.getCellState(3, 0)).thenReturn(1);
        when(grid.getCellState(2, 1)).thenReturn(1);
        assertEquals(3, neighborhood.computeAliveNeighbors(grid, 2, 0));
    }

    /**
     * Test case: Ensure that method doesn't count the central cell itself
     * Expected: The method should not include the state of the central cell
     */
    @Test
    void testGetAliveNeighbors_DoesNotCountSelf() {
        when(grid.getCellState(2, 2)).thenReturn(1);  // Central cell
        when(grid.getCellState(2, 1)).thenReturn(1);
        when(grid.getCellState(2, 3)).thenReturn(1);
        assertEquals(2, neighborhood.computeAliveNeighbors(grid, 2, 2));
    }

    /**
     * Test case: Ensure that method doesn't count diagonal neighbors
     * Expected: The method should correctly exclude diagonal neighbors, counting only 2 valid neighbors
     */
    @Test
    void testGetAliveNeighbors_DoesNotCountDiagonal() {
        // cells diagonal to (2,2)
        when(grid.getCellState(1, 1)).thenReturn(0);
        when(grid.getCellState(1, 3)).thenReturn(0);
        when(grid.getCellState(3, 1)).thenReturn(0);
        when(grid.getCellState(1, 3)).thenReturn(0);
        // valid neighbors of (2,2)
        when(grid.getCellState(1,2)).thenReturn(1);
        when(grid.getCellState(3,2)).thenReturn(1);
        assertEquals(2, neighborhood.computeAliveNeighbors(grid, 2, 2));
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
