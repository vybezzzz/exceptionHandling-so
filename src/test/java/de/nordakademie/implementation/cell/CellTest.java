package de.nordakademie.implementation.cell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CellTest {

    /**
     * Tests whether the constructor of the cell sets the x and y position correctly.
     */
    @Test
    void testConstructor() {

        Cell cell = new Cell(29, 3);
        assertEquals(29, cell.getYPosition(), "Y position of the cell should be 29.");
        assertEquals(3, cell.getXPosition(), "X position of the cell should be 3.");

    }

    @Test
    void testConstructorThrowingIllegalArgumentExceptionWhenPositionsAreNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Cell(-1, 3));
        assertThrows(IllegalArgumentException.class, () -> new Cell(0, -3));
    }

    /**
     * Tests whether the x position of the cell is set correctly.
     */
    @Test
    void testGetXPosition() {

        Cell cell = new Cell(2, 1);
        assertEquals(1, cell.getXPosition(), "X position of the cell should be 1.");
    }

    /**
     * Tests whether the y position of the cell is set correctly.
     */
    @Test
    void testGetYPosition() {

        Cell cell = new Cell(6, 1);
        assertEquals(6, cell.getYPosition(), "Y position of the cell should be 6.");
    }

    /**
     * Tests whether a newly created cell has the initial state 0.
     */
    @Test
    void testInitialState() {

        Cell cell = new Cell(0, 0);
        assertEquals(0, cell.getState(), "Initial state of the cell should be 0.");
    }

    /**
     * Tests whether the state of the cell can be set to 1.
     */
    @Test
    void testSetState() {

        Cell cell = new Cell(0, 0);
        cell.setState(1);
        assertEquals(1, cell.getState(), "State of the cell should be 1 after it has been set.");
    }

    @Test
    void testSetStateThrowingIllegalArgumentExceptionWhenStateIsNegativeOrBeyond1() {
        Cell cell = new Cell(0, 0);
        assertThrows(IllegalArgumentException.class, () -> cell.setState(-1));
        assertThrows(IllegalArgumentException.class, () -> cell.setState(2));
    }

    /**
     * Tests whether a newly created cell has the initial state 0.
     */
    @Test
    void testInitialNeighbors() {

        Cell cell = new Cell(0, 0);
        assertEquals(0, cell.getAliveNeighbors(), "The initial number of alive neighbors of this cell should be 0.");
    }

    /**
     * Tests whether the state of the cell can be set to 1.
     */
    @Test
    void testSetAliveNeighbors() {

        Cell cell = new Cell(0, 0);
        cell.setAliveNeighbors(4);
        assertEquals(4, cell.getAliveNeighbors(), "Number of alive neighbors of this cell should be 4 after it has been set.");
    }

    @Test
    void testSetAliveNeighborsThrowingIllegalArgumentExceptionWhenStateIsNegativeOrBeyond8() {
        Cell cell = new Cell(0, 0);
        assertThrows(IllegalArgumentException.class, () -> cell.setAliveNeighbors(-1));
        assertThrows(IllegalArgumentException.class, () -> cell.setAliveNeighbors(9));
    }
}
