package de.nordakademie.view.logger;

import de.nordakademie.implementation.gridStructure.IGridStructure;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

/**
 * Test class for NullLogger.
 * Contains unit tests to verify the functionality of the NullLogger class.
 */
public class NullLoggerTest {

    /**
     * Tests whether the NullLogger correctly logs a GridStructure.
     * The NullLogger does nothing, so this test is only for coverage.
     */
    @Test
    public void testLog() {

        NullLogger nullLogger = new NullLogger();
        IGridStructure mockGridStructure = mock(IGridStructure.class);
        int currentStep = 1;


        nullLogger.log(mockGridStructure, currentStep);

    }
}