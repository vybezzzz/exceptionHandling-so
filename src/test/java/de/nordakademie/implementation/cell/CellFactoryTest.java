package de.nordakademie.implementation.cell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CellFactoryTest {

    /**
     * Tests the CellFactory to ensure it creates cells correctly.
     */
    @Test
    void testCreateCell() {

        CellFactory cellFactory = new CellFactory();
        Cell cell = cellFactory.createCell(5, 9);
        assertNotNull(cell, "CellFactory should create a non-null cell.");
        assertEquals(5, cell.getYPosition(), "Y position of the cell should be 5.");
        assertEquals(9, cell.getXPosition(), "X position of the cell should be 9.");
    }
}