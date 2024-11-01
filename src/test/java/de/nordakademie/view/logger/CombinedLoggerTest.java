package de.nordakademie.view.logger;

import de.nordakademie.implementation.gridStructure.IGridStructure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Test class for CombinedLogger.
 * Contains unit tests to verify the functionality of the CombinedLogger class.
 */
public class CombinedLoggerTest {

    private CombinedLogger combinedLogger;
    private IGridStructure mockGridStructure;
    private String logFilePath;
    ByteArrayOutputStream outputStreamCaptor;

    /**
     * Sets up the test environment before each test.
     * Initializes a mock GridStructure and a CombinedLogger.
     */
    @BeforeEach
    void setUp() {

        combinedLogger = new CombinedLogger();
        logFilePath = combinedLogger.getFileLogger().getFilename();
        mockGridStructure = mock(IGridStructure.class);
        when(mockGridStructure.getHeight()).thenReturn(3);
        when(mockGridStructure.getWidth()).thenReturn(3);
        when(mockGridStructure.getCellState(0, 0)).thenReturn(0);
        when(mockGridStructure.getCellState(0, 1)).thenReturn(1);
        when(mockGridStructure.getCellState(0, 2)).thenReturn(0);
        when(mockGridStructure.getCellState(1, 0)).thenReturn(0);
        when(mockGridStructure.getCellState(1, 1)).thenReturn(1);
        when(mockGridStructure.getCellState(1, 2)).thenReturn(0);
        when(mockGridStructure.getCellState(2, 0)).thenReturn(1);
        when(mockGridStructure.getCellState(2, 1)).thenReturn(1);
        when(mockGridStructure.getCellState(2, 2)).thenReturn(0);

        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * Cleans up after each test by deleting the log file.
     */
    @AfterEach
    void tearDown() {

        File logFile = new File(logFilePath);
        if (logFile.exists()) {
            logFile.delete();
        }
    }

    /**
     * Tests if the log file is created.
     */
    @Test
    void testLogFileCreation() {

        combinedLogger.log(mockGridStructure, 1);
        File logFile = new File(logFilePath);
        Assertions.assertTrue(logFile.exists(), "Log file should be created.");
    }

    /**
     * Tests whether the CombinedLogger correctly gets a value of the top left cell of a mock IGridStructure.
     */
    @Test
    void testGettingValueOfACell() {

        combinedLogger.log(mockGridStructure, 1);
        verify(mockGridStructure, times(2)).getCellState(0, 0);
    }

    /**
     * Tests if the log file is empty before logging.
     */
    @Test
    void testLogFileIsEmptyBeforeLogging() throws IOException {

        File logFile = new File(logFilePath);
        Assertions.assertTrue(logFile.length() == 0, "Log file should be empty before logging.");
    }

    /**
     * Tests whether the CombinedLogger correctly gets a value from all cells of a mock gridStructure.
     */
    @Test
    void testGettingEveryValueOfAGridStructure() {

        combinedLogger.log(mockGridStructure, 1);
        verify(mockGridStructure, times(2)).getCellState(0, 0);
        verify(mockGridStructure, times(2)).getCellState(0, 1);
        verify(mockGridStructure, times(2)).getCellState(0, 2);
        verify(mockGridStructure, times(2)).getCellState(1, 0);
        verify(mockGridStructure, times(2)).getCellState(1, 1);
        verify(mockGridStructure, times(2)).getCellState(1, 2);
        verify(mockGridStructure, times(2)).getCellState(2, 0);
        verify(mockGridStructure, times(2)).getCellState(2, 1);
        verify(mockGridStructure, times(2)).getCellState(2, 2);
    }

    /**
     * Tests whether the CombinedLogger correctly logs the grid structure to both the console and a file.
     */
    @Test
    void testLoggingTheValueOfAGridStructureInFile() throws IOException {

        combinedLogger.log(mockGridStructure, 1);
        File logFile = new File(logFilePath);
        List<String> lines = Files.readAllLines(logFile.toPath());
        Assertions.assertEquals("### 1", lines.get(0));
        Assertions.assertEquals("010", lines.get(1));
        Assertions.assertEquals("010", lines.get(2));
        Assertions.assertEquals("110", lines.get(3));
    }

    /**
     * Tests whether the CombinedLogger correctly logs the end of the simulation to both the console and a file.
     */
    @Test
    void testLoggingEndOfSimulationInFile() throws IOException {

        combinedLogger.log(mockGridStructure, 1);
        combinedLogger.logEndOfSimulation();
        File logFile = new File(logFilePath);
        List<String> lines = Files.readAllLines(logFile.toPath());
        Assertions.assertEquals("END OF SIMULATION", lines.get(lines.size() - 1));
    }

    /**
     * Tests whether the CombinedLogger correctly logs a value from the top left cell of a mock gridStructure.
     */
    @Test
    void testLoggingTheValueOfAGridStructureInConsole() {
        combinedLogger.log(mockGridStructure,1);
        Assertions.assertEquals("### 1" + System.lineSeparator() + 
                "010" + System.lineSeparator() + 
                "010" + System.lineSeparator() + 
                "110" + System.lineSeparator(), outputStreamCaptor.toString());
    }

    /**
     * Tests whether the CombinedLogger correctly logs a value from the top left cell of a mock gridStructure.
     */
    @Test
    void testLoggingTheValueOfAGridStructureForEndOfSimulationInConsole() {
        combinedLogger.logEndOfSimulation();
        Assertions.assertEquals(
                "END OF SIMULATION" + System.lineSeparator() + System.lineSeparator(), outputStreamCaptor.toString());
    }

    @Test
    void testThrowingIllegalArgumentExceptionWhenGridIsNull() {
        assertThrows(IllegalArgumentException.class, () -> combinedLogger.log(null, 1));
    }

    @Test
    void testThrowingIllegalArgumentExceptionWhenCurrentStepIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> combinedLogger.log(mockGridStructure, -1));
    }
}