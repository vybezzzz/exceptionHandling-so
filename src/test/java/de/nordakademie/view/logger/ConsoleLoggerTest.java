package de.nordakademie.view.logger;

import de.nordakademie.implementation.gridStructure.IGridStructure;
import de.nordakademie.implementation.simulationState.ISimulationState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Test class for ConsoleLogger.
 * Contains unit tests to verify the functionality of the ConsoleLogger class.
 */
public class ConsoleLoggerTest {
    ConsoleLogger consoleLogger;
    IGridStructure mockGridStructure;
    ISimulationState mockSimulationState;
    ByteArrayOutputStream outputStreamCaptor;

    /**
     * Sets up the test environment before each test.
     * Initializes a mock GridStructure, a PrintStream with a ByteArrayOutputStream for asserting the console output
     * and a ConsoleLogger.
     */
    @BeforeEach
    void setUp() {
        consoleLogger = new ConsoleLogger();
        mockGridStructure = mock(IGridStructure.class);
        when(mockGridStructure.getHeight()).thenReturn(3);
        when(mockGridStructure.getWidth()).thenReturn(3);
        when(mockGridStructure.getCellState(0,0)).thenReturn(0);
        when(mockGridStructure.getCellState(0,1)).thenReturn(1);
        when(mockGridStructure.getCellState(0,2)).thenReturn(0);
        when(mockGridStructure.getCellState(1,0)).thenReturn(0);
        when(mockGridStructure.getCellState(1,1)).thenReturn(1);
        when(mockGridStructure.getCellState(1,2)).thenReturn(0);
        when(mockGridStructure.getCellState(2,0)).thenReturn(1);
        when(mockGridStructure.getCellState(2,1)).thenReturn(1);
        when(mockGridStructure.getCellState(2,2)).thenReturn(0);

        mockSimulationState = mock(ISimulationState.class);
        when(mockSimulationState.getCurrentGrid()).thenReturn(mockGridStructure);
        when(mockSimulationState.getCurrentSimulationStep()).thenReturn(1);


        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * Tests whether the ConsoleLogger correctly gets a value of the top left cell of a mock IGridStructure.
     */
    @Test
    void testGettingValueOfACell() {
        consoleLogger.log(mockGridStructure, 1);
        verify(mockGridStructure).getCellState(0,0);
    }

    /**
     * Tests whether the ConsoleLogger correctly gets a value from all cells of a mock gridStructure.
     */
    @Test
    void testGettingEveryValueOfAGridStructure() {
        consoleLogger.log(mockGridStructure, 1);
        verify(mockGridStructure).getCellState(0,0);
        verify(mockGridStructure).getCellState(0,1);
        verify(mockGridStructure).getCellState(0,2);
        verify(mockGridStructure).getCellState(1,0);
        verify(mockGridStructure).getCellState(1,1);
        verify(mockGridStructure).getCellState(1,2);
        verify(mockGridStructure).getCellState(2,0);
        verify(mockGridStructure).getCellState(2,1);
        verify(mockGridStructure).getCellState(2,2);
    }

    /**
     * Tests whether the ConsoleLogger correctly logs a value from the top left cell of a mock gridStructure.
     */
    @Test
    void testLoggingTheValueOfAGridStructure() {
        consoleLogger.log(mockGridStructure,1);
        Assertions.assertEquals("### 1" + System.lineSeparator() +
                "010" + System.lineSeparator() +
                "010" + System.lineSeparator() +
                "110" + System.lineSeparator(), outputStreamCaptor.toString());
    }

    /**
     * Tests whether the ConsoleLogger correctly logs a value from the top left cell of a mock gridStructure.
     */
    @Test
    void testLoggingTheValueOfAGridStructureForEndOfSimulation() {
        consoleLogger.logEndOfSimulation();
        Assertions.assertEquals(
                "END OF SIMULATION" + System.lineSeparator() + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    void testThrowingIllegalArgumentExceptionWhenGridIsNull() {
        assertThrows(IllegalArgumentException.class, () -> consoleLogger.log(null, 1));
    }

    @Test
    void testThrowingIllegalArgumentExceptionWhenCurrentStepIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> consoleLogger.log(mockGridStructure, -1));
    }
}
