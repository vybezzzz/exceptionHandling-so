package de.nordakademie.core.terminator;

import de.nordakademie.implementation.simulationState.ISimulationState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for the FixedStepTerminator:
 * This class contains unit tests to verify the behavior of the FixedStepTerminator.
 */

public class FixedStepTerminatorTest {
    private FixedStepTerminator terminator;

    @BeforeEach
    public void setUp() {
        terminator = new FixedStepTerminator(10);
    }


    /**
     * Test case: Termination condition before reaching max steps
     * Expected: The method should return false when the current step is less than the max steps
     */
    @Test
    public void testShouldTerminateReturnsFalseBeforeMaxSteps() {
        // Arrange
        ISimulationState simulationState = mock(ISimulationState.class);
        when(simulationState.getCurrentSimulationStep()).thenReturn(5);

        // Act
        boolean result = terminator.shouldTerminate(simulationState);

        // Assert
        assertFalse(result);
    }

    /**
     * Test case: Termination condition at max steps
     * Expected: The method should return true when the current step exceeds the max steps
     */
    @Test
    public void testShouldTerminateReturnsTrueAtMaxSteps() {
        // Arrange
        ISimulationState simulationState = mock(ISimulationState.class);
        when(simulationState.getCurrentSimulationStep()).thenReturn(11);

        // Act
        boolean result = terminator.shouldTerminate(simulationState);

        // Assert
        assertTrue(result);
    }

    /**
     * Test case: Termination condition after max steps
     * Expected: The method should return true when the current step exceeds the max steps
     */
    @Test
    public void testShouldTerminateReturnsTrueAfterMaxSteps() {
        // Arrange
        ISimulationState simulationState = mock(ISimulationState.class);
        when(simulationState.getCurrentSimulationStep()).thenReturn(15);

        // Act
        boolean result = terminator.shouldTerminate(simulationState);

        // Assert
        assertTrue(result);
    }

    /**
     * Test case: Constructor with invalid max steps (zero or negative)
     * Expected: The constructor should throw an IllegalArgumentException for invalid inputs
     */
    @Test
    public void testConstructorInvalidMaxStepsThrowsException() {
        // Arrange, Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new FixedStepTerminator(0));
        assertThrows(IllegalArgumentException.class, () -> new FixedStepTerminator(-5));
    }
}
