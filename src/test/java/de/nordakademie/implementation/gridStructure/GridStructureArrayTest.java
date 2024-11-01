package de.nordakademie.implementation.gridStructure;

import de.nordakademie.implementation.cell.Cell;
import de.nordakademie.implementation.cell.CellFactory;
import de.nordakademie.implementation.cell.ICell;
import de.nordakademie.implementation.cell.ICellFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for GridStructureArray.
 * Contains unit tests to verify the functionality of the GridStructureArray class.
 */
public class GridStructureArrayTest {

    private Cell mockCell;
    private ICellFactory mockCellFactory;
    private GridStructureArray grid;

    /**
     * Sets up the test environment before each test.
     * Initializes a mock ICell and a GridStructureArray with a mock grid.
     */
    @BeforeEach
    public void setUp() {

        mockCell = mock(Cell.class);
        mockCellFactory = mock(CellFactory.class);
        when(mockCellFactory.createCell(anyInt(), anyInt())).thenReturn(mockCell);

        grid = new GridStructureArray(10, 10, mockCellFactory);
    }

    /**
     * Tests the constructor of GridStructureArray.
     * Verifies that the grid is initialized with the correct dimensions
     * and that each cell in the grid is an instance of cell.
     */
    @Test
    public void testGridStructureArrayConstructor() {

        int width = 25;
        int height = 25;
        GridStructureArray grid = new GridStructureArray(height, width, mockCellFactory);

        // Verify the dimensions of the grid
        assertEquals(height, grid.getHeight());
        assertEquals(width, grid.getWidth());

        // Verify that each cell in the grid is an instance of cell
        ICell[][] internalGrid = grid.getGrid();
        assertEquals(height, internalGrid.length);
        assertEquals(width, internalGrid[0].length);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                assertNotNull(internalGrid[y][x]);
                assertTrue(internalGrid[y][x] instanceof ICell);
            }
        }
    }

    @Test
    public void testGridStructureConstructorHashMapThrowingExceptionInInitializerError() {
        assertThrows(ExceptionInInitializerError.class, () -> new GridStructureArray(-10, 10, mockCellFactory));
        assertThrows(ExceptionInInitializerError.class, () -> new GridStructureArray(10, -10, mockCellFactory));
    }

    /**
     * Tests the getCellState method.
     * Verifies that the state of a cell at specified coordinates is returned correctly.
     */
    @Test
    public void testGetCellState() {

        when(mockCell.getState()).thenReturn(1);
        int cellState = grid.getCellState(0, 0);
        assertEquals(1, cellState);
    }

    /**
     * Tests the setCellState method.
     * Verifies that the state of a cell at specified coordinates is set correctly.
     */
    @Test
    public void testSetCellState() {

        grid.setCellState(0, 0, 1);
        verify(mockCell).setState(1);
    }

    /**
     * Tests the initialization of the grid.
     * Verifies that the grid is initialized with the correct width and height.
     */
    @Test
    public void testGridInitialization() {

        ICell[][] internalGrid = grid.getGrid();
        assertEquals(10, internalGrid.length);
        assertEquals(10, internalGrid[0].length);
    }

    /**
     * Tests the behavior of the mock across multiple cells.
     * Verifies that the state of each cell in the grid is returned correctly.
     */
    @Test
    public void testMockBehaviorAcrossMultipleCells() {

        when(mockCell.getState()).thenReturn(0);
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                int state = grid.getCellState(y, x);
                assertEquals(0, state);
            }
        }
    }

    @Test
    void testGetCellStateThrowingIllegalArgumentExceptionWhenPositionAreOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> grid.getCellState(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> grid.getCellState(0, -1));
    }

    @Test
    void testSetCellThrowingIllegalArgumentExceptionWhenPositionAreOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> grid.setCellState(-1, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> grid.setCellState(0, -1, 1));
    }

    @Test
    void testGetCellThrowingIllegalArgumentExceptionWhenPositionAreOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> grid.getCell(-1, 0));
        assertThrows(IllegalArgumentException.class, () -> grid.getCell(0, -1));
    }

    @Test
    void testSetAliveNeighborsThrowingIllegalArgumentExceptionWhenPositionAreOutOfRange() {
        assertThrows(IllegalArgumentException.class, () -> grid.setCellAliveNeighbors(-1,0,1));
        assertThrows(IllegalArgumentException.class, () -> grid.setCellAliveNeighbors(0,-1,1));
    }
}