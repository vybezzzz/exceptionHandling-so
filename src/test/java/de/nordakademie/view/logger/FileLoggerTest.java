package de.nordakademie.view.logger;

import de.nordakademie.implementation.gridStructure.IGridStructure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Test class for FileLogger.
 * Contains unit tests to verify the functionality of the FileLogger class.
 */
public class FileLoggerTest {

    FileLogger fileLogger;
    IGridStructure mockGridStructure;
    String logFilePath;

    /**
     * Sets up the test environment before each test.
     * Initializes a mock GridStructure and a FileLogger.
     */
    @BeforeEach
    void setUp() {

        fileLogger = new FileLogger();
        logFilePath = fileLogger.getFilename();
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
        fileLogger.log(mockGridStructure, 1);

        File logFile = new File(logFilePath);
        Assertions.assertTrue(logFile.exists(), "Log file should be created.");
    }

    /**
     * Tests whether the FileLogger correctly gets a value of the top left cell of a mock IGridStructure.
     */
    @Test
    void testGettingValueOfACell() {

        fileLogger.log(mockGridStructure, 1);
        verify(mockGridStructure).getCellState(0,0);
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
     * Tests whether the FileLogger correctly gets a value from all cells of a mock gridStructure.
     */
    @Test
    void testGettingEveryValueOfAGridStructure() {

        fileLogger.log(mockGridStructure, 1);
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
     * Tests whether the FileLogger correctly logs the grid structure to a file.
     */
    @Test
    void testLoggingTheValueOfAGridStructure() throws IOException {

        fileLogger.log(mockGridStructure, 1);
        File logFile = new File(logFilePath);
        List<String> lines = Files.readAllLines(logFile.toPath());
        Assertions.assertEquals("### 1", lines.get(0));
        Assertions.assertEquals("010", lines.get(1));
        Assertions.assertEquals("010", lines.get(2));
        Assertions.assertEquals("110", lines.get(3));
    }

    /**
     * Tests whether the FileLogger correctly logs the end of the simulation.
     */
    @Test
    void testLoggingEndOfSimulation() throws IOException {
        fileLogger.logEndOfSimulation();
        File logFile = new File(logFilePath);
        List<String> lines = Files.readAllLines(logFile.toPath());
        Assertions.assertEquals("END OF SIMULATION", lines.get(lines.size() - 1));
    }

    @Test
    void testThrowingIllegalArgumentExceptionWhenGridIsNull() {
        assertThrows(IllegalArgumentException.class, () -> fileLogger.log(null, 1));
    }

    @Test
    void testThrowingIllegalArgumentExceptionWhenCurrentStepIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> fileLogger.log(mockGridStructure, -1));
    }
}