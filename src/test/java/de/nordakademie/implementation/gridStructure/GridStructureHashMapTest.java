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
 * Test class for GridStructureHashMap.
 * Contains unit tests to verify the functionality of the GridStructureHashMap class.
 */
public class GridStructureHashMapTest {

    private Cell mockCell;
    private ICellFactory mockCellFactory;
    private GridStructureHashMap grid;

    /**
     * Sets up the test environment before each test.
     * Initializes a mock ICell and a GridStructureHashMap with a mock grid.
     */
    @BeforeEach
    public void setUp() {
        mockCell = mock(Cell.class);
        mockCellFactory = mock(CellFactory.class);
        when(mockCellFactory.createCell(anyInt(), anyInt())).thenReturn(mockCell);

        grid = new GridStructureHashMap(10, 10, mockCellFactory);
    }

    /**
     * Tests the constructor of GridStructureHashMap.
     * Verifies that the grid is initialized with the correct dimensions
     * and that each cell in the grid is an instance of cell.
     */
    @Test
    public void testGridStructureHashMapConstructor() {
        int width = 25;
        int height = 25;
        GridStructureHashMap grid = new GridStructureHashMap(height, width, mockCellFactory);

        // Verify the dimensions of the grid
        assertEquals(height, grid.getHeight());
        assertEquals(width, grid.getWidth());

        // Verify that each cell in the grid exists and is an instance of ICell
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ICell cell = grid.getCell(y, x);
                assertNotNull(cell);
                assertTrue(cell instanceof ICell);
            }
        }
    }

    @Test
    public void testGridStructureHashMapConstructorThrowingExceptionInInitializerError() {
        assertThrows(ExceptionInInitializerError.class, () -> new GridStructureHashMap(-10, 10, mockCellFactory));
        assertThrows(ExceptionInInitializerError.class, () -> new GridStructureHashMap(10, -10, mockCellFactory));
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
     * Verifies that the grid is initialized with the correct number of cells.
     */
    @Test
    public void testGridInitialization() {
        assertEquals(100, grid.getGrid().size()); // 10x10 grid should have 100 cells
        assertTrue(grid.getGrid().containsKey("0,0")); // Verify the key format
        assertTrue(grid.getGrid().containsKey("9,9")); // Verify the last cell exists
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

    /**
     * Tests that the grid structure type is correctly set to HASHMAP.
     */
    @Test
    public void testGridStructureType() {
        assertEquals(GridStructureType.HASHMAP, grid.getType());
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