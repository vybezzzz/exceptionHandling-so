package de.nordakademie.core.ruleSet;

import de.nordakademie.implementation.cell.ICell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Test class for GameOfLifeRuleSet.
 * Contains unit tests to verify the functionality of the GameOfLifeRuleSet class.
 */

public class GameOfLifeRuleSetTest {

    private GameOfLifeRuleSet gameOfLifeRuleSet;
    private ICell cellMock;

    /**
     * Sets up the test environment before each test.
     * Initializes a mock ICell and a GameOfLifeRuleSet.
     */
    @BeforeEach
    public void setUp() {

        this.gameOfLifeRuleSet = new GameOfLifeRuleSet();
        this.cellMock = Mockito.mock(ICell.class);
    }

    /**
     * Tests the next state of a cell with two alive neighbors.
     * Verifies that the cell remains alive.
     */
    @Test
    public void testComputeNextStateCellStaysAliveWithTwoAliveNeighbors() {

        when(cellMock.getState()).thenReturn(1);
        when(cellMock.getAliveNeighbors()).thenReturn(2);
        int result = gameOfLifeRuleSet.computeNextState(cellMock);
        assertEquals(1, result);
    }

    /**
     * Tests the next state of a cell with three alive neighbors.
     * Verifies that the cell remains alive.
     */
    @Test
    public void testComputeNextStateCellStaysAliveWithThreeAliveNeighbors() {

        when(cellMock.getState()).thenReturn(1);
        when(cellMock.getAliveNeighbors()).thenReturn(3);
        int result = gameOfLifeRuleSet.computeNextState(cellMock);
        assertEquals(1, result);
    }

    /**
     * Tests the next state of a cell with less than two alive neighbors.
     * Verifies that the cell dies.
     */
    @Test
    public void testComputeNextStateCellDiesWithLessThanTwoAliveNeighbors() {

        when(cellMock.getState()).thenReturn(1);
        when(cellMock.getAliveNeighbors()).thenReturn(1);
        int result = gameOfLifeRuleSet.computeNextState(cellMock);
        assertEquals(0, result);
    }

    /**
     * Tests the next state of a cell with more than three alive neighbors.
     * Verifies that the cell dies.
     */
    @Test
    public void testComputeNextStateCellDiesWithMoreThanThreeAliveNeighbors() {

        when(cellMock.getState()).thenReturn(1);
        when(cellMock.getAliveNeighbors()).thenReturn(4);
        int result = gameOfLifeRuleSet.computeNextState(cellMock);
        assertEquals(0, result);
    }

    /**
     * Tests the next state of a dead cell with exactly three alive neighbors.
     * Verifies that the cell becomes alive.
     */
    @Test
    public void testComputeNextStateDeadCellBecomesAliveWithExactlyThreeAliveNeighbors() {

        when(cellMock.getState()).thenReturn(0);
        when(cellMock.getAliveNeighbors()).thenReturn(3);
        int result = gameOfLifeRuleSet.computeNextState(cellMock);
        assertEquals(1, result);
    }

    /**
     * Tests the next state of a dead cell with not exactly three alive neighbors.
     * Verifies that the cell remains dead.
     */
    @Test
    public void testComputeNextStateDeadCellStaysDeadWithNotExactlyThreeAliveNeighbors() {

        when(cellMock.getState()).thenReturn(0);
        when(cellMock.getAliveNeighbors()).thenReturn(2);
        int result = gameOfLifeRuleSet.computeNextState(cellMock);
        assertEquals(0, result);
    }

    /**
     * Tests the next state of a cell with negative alive neighbors.
     * Verifies that an IllegalArgumentException is thrown.
     */
    @Test
    public void testComputeNextStateWithAliveNeighborsBeyondMax() {

        when(cellMock.getAliveNeighbors()).thenReturn(9);
        assertThrows(IllegalArgumentException.class, () -> gameOfLifeRuleSet.computeNextState(cellMock));
    }

    /**
     * Tests the next state of a cell with negative alive neighbors.
     * Verifies that an IllegalArgumentException is thrown.
     */
    @Test
    public void testComputeNextStateWithNegativeAliveNeighbors() {

        when(cellMock.getAliveNeighbors()).thenReturn(-1);
        assertThrows(IllegalArgumentException.class, () -> gameOfLifeRuleSet.computeNextState(cellMock));
    }

    /**
     * Tests the next state of a cell with negative alive neighbors.
     * Verifies that an IllegalArgumentException is thrown.
     */
    @Test
    public void testComputeNextStateWithStateBeyond1() {

        when(cellMock.getState()).thenReturn(9);
        assertThrows(IllegalArgumentException.class, () -> gameOfLifeRuleSet.computeNextState(cellMock));
    }

    /**
     * Tests the next state of a cell with negative alive neighbors.
     * Verifies that an IllegalArgumentException is thrown.
     */
    @Test
    public void testComputeNextStateWithNegativeState() {

        when(cellMock.getState()).thenReturn(-1);
        assertThrows(IllegalArgumentException.class, () -> gameOfLifeRuleSet.computeNextState(cellMock));
    }
}
