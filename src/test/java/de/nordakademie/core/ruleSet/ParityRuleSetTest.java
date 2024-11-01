package de.nordakademie.core.ruleSet;

import de.nordakademie.implementation.cell.ICell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Test in which the next state of a cell is determined by the parity of the living neighbors.
 */

public class ParityRuleSetTest {

    private ParityRuleSet parityRuleSet;
    private ICell cellMock;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testComputeNextStateCellBecomesAliveWithOddAliveNeighbors() {
        this.parityRuleSet = new ParityRuleSet();
        this.cellMock = Mockito.mock(ICell.class);
        // Arrange
        when(cellMock.getAliveNeighbors()).thenReturn(1);

        // Act
        int result = parityRuleSet.computeNextState(cellMock);

        // Assert
        assertEquals(1, result);
    }

    @Test
    public void testComputeNextStateCellBecomesAliveWithEvenAliveNeighbors() {
        this.parityRuleSet = new ParityRuleSet();
        this.cellMock = Mockito.mock(ICell.class);

        // Arrange
        when(cellMock.getAliveNeighbors()).thenReturn(2);

        // Act
        int result = parityRuleSet.computeNextState(cellMock);

        // Assert
        assertEquals(0, result);
    }


    @Test
    public void testComputeNextStateCellWithNegativeAliveNeighbors() {
        this.parityRuleSet = new ParityRuleSet();
        this.cellMock = Mockito.mock(ICell.class);
        // Arrange
        ICell cell = mock(ICell.class);
        when(cell.getAliveNeighbors()).thenReturn(-1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> parityRuleSet.computeNextState(cell));
    }
    @Test
    public void testComputeNextStateCellWithAliveNeighborsBeyondMax() {
        this.parityRuleSet = new ParityRuleSet();
        this.cellMock = Mockito.mock(ICell.class);
        // Arrange
        ICell cell = mock(ICell.class);
        when(cell.getAliveNeighbors()).thenReturn(5);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> parityRuleSet.computeNextState(cell));
    }

}
