package de.nordakademie.core.simulationController;

import de.nordakademie.core.neighborhoodStrategy.INeighborhoodStrategy;
import de.nordakademie.core.neighborhoodStrategy.VonNeumannNeighborhood;
import de.nordakademie.core.ruleSet.IRuleSet;
import de.nordakademie.core.ruleSet.ParityRuleSet;
import de.nordakademie.core.terminator.FixedStepTerminator;
import de.nordakademie.core.terminator.ITerminator;
import de.nordakademie.implementation.cell.CellFactory;
import de.nordakademie.implementation.cell.ICell;
import de.nordakademie.implementation.gridStructure.*;
import de.nordakademie.implementation.simulationState.ISimulationState;
import de.nordakademie.view.logger.ConsoleLogger;
import de.nordakademie.view.logger.ILogger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Test class for SimulationController.
 * Contains unit tests to verify the functionality of the SimulationController class.
 */
public class SimulationControllerTest {
    IGridStructure mockGridStructure;
    IGridStructureFactory gridStructureFactory;
    IRuleSet ruleSet;
    INeighborhoodStrategy neighborhoodStrategy;
    ISimulationState simulationState;
    ITerminator terminator;
    ILogger logger;
    SimulationController simulationController;
    ByteArrayOutputStream outputStreamCaptor;

    /**
     * Sets up the test environment before each test.
     * Initializes a mock ICell, a mock IGridStructure, a mock IRuleSet, a mock INeighborhoodStrategy, a mock ITerminator, a mock
     * ILogger as well as a SimulationController containing the mocks.
     */
    @BeforeEach
    void setUp() {
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
        when(mockGridStructure.getType()).thenReturn(GridStructureType.ARRAY);

        gridStructureFactory = mock(IGridStructureFactory.class);
        when(gridStructureFactory.createGridStructure(mockGridStructure)).thenReturn(mock(GridStructureArray.class));

        ruleSet = mock(IRuleSet.class);
        neighborhoodStrategy = mock(INeighborhoodStrategy.class);
        simulationState = mock(ISimulationState.class);
        when(simulationState.getCurrentGrid()).thenReturn(mockGridStructure);
        when(simulationState.getPreviousGrid()).thenReturn(mockGridStructure);
        when(simulationState.getCurrentSimulationStep()).thenReturn(1);
        when(mockGridStructure.getCell(anyInt(), anyInt())).thenReturn(mock(ICell.class));

        terminator = mock(ITerminator.class);
        when(terminator.shouldTerminate(simulationState)).thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true);

        logger = mock(ILogger.class);

        simulationController = new SimulationController(mockGridStructure, gridStructureFactory,
                ruleSet, neighborhoodStrategy, terminator, logger, simulationState);

        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    /**
     * Tests whether the SimulationController invokes the counting of all aliveNeighbors of the top left Cell of a mock
     * GridStructure.
     */
    @Test
    void testCountingTheAliveNeighborsOfTheTopLeftCellOfAGridStructure() {
        simulationController.runSimulation();

        verify(neighborhoodStrategy, atLeast(1)).computeAliveNeighbors(mockGridStructure, 0, 0);
    }

    /**
     * Tests whether the SimulationController invokes the counting of all aliveNeighbors of every cell of a mock
     * GridStructure and only of the cells inside the mock GridStructure.
     */
    @Test
    void testCountingTheAliveNeighborsEveryCellOfAGridStructure() {
        simulationController.runSimulation();

        verify(neighborhoodStrategy, atLeast(1)).computeAliveNeighbors(mockGridStructure, 0, 0);
        verify(neighborhoodStrategy, atLeast(1)).computeAliveNeighbors(mockGridStructure, 1, 0);
        verify(neighborhoodStrategy, atLeast(1)).computeAliveNeighbors(mockGridStructure, 2, 0);
        verify(neighborhoodStrategy, atLeast(1)).computeAliveNeighbors(mockGridStructure, 0, 1);
        verify(neighborhoodStrategy, atLeast(1)).computeAliveNeighbors(mockGridStructure, 1, 1);
        verify(neighborhoodStrategy, atLeast(1)).computeAliveNeighbors(mockGridStructure, 2, 1);
        verify(neighborhoodStrategy, atLeast(1)).computeAliveNeighbors(mockGridStructure, 0, 2);
        verify(neighborhoodStrategy, atLeast(1)).computeAliveNeighbors(mockGridStructure, 1, 2);
        verify(neighborhoodStrategy, atLeast(1)).computeAliveNeighbors(mockGridStructure, 2, 2);
        verify(neighborhoodStrategy, never()).computeAliveNeighbors(mockGridStructure, 3, 0);
        verify(neighborhoodStrategy, never()).computeAliveNeighbors(mockGridStructure, 3, 3);
        verify(neighborhoodStrategy, never()).computeAliveNeighbors(mockGridStructure, 6, 2);
        verify(neighborhoodStrategy, never()).computeAliveNeighbors(mockGridStructure, 4, 1);
    }

    /**
     * Tests whether the SimulationController invokes the computing of a new state of every mock Cell inside a mock
     * GridStructure.
     */
    @Test
    void testComputingTheNewStateOfEveryCellInsideAGridStructure() {
        simulationController.runSimulation();

        verify(ruleSet, atLeast(9)).computeNextState(any(ICell.class));
    }

    /**
     * Tests whether the SimulationController correctly updates the current step of the SimulationState, as well as
     * reads previous grid and current grid
     */
    @Test
    void testUpdatingTheSimulationState() {
        simulationController.runSimulation();

        verify(simulationState, atLeast(1)).setCurrentSimulationStep(simulationState.getCurrentSimulationStep() + 1);
        verify(simulationState, atLeast(2)).getPreviousGrid();
        verify(simulationState, atLeast(4)).getCurrentGrid();
    }

    /**
     * Tests whether the SimulationController invokes the checking of the terminationCondition.
     */
    @Test
    void testCheckingTerminationCondition() {
        simulationController.runSimulation();

        verify(terminator, atLeast(1)).shouldTerminate(simulationState);
    }

    /**
     * Tests whether the SimulationController invokes the logging of the simulation step result via a mock Logger.
     */
    @Test
    void testLoggingTheResultOfTheCurrentSimulationStep() {
        simulationController.runSimulation();

        verify(logger, atLeast(1)).log(simulationState.getCurrentGrid(), simulationState.getCurrentSimulationStep());
    }

    /**
     * Tests whether the SimulationController invokes the logging of the starting GridStructure via a mock Logger.
     */
    @Test
    void testLoggingTheStartingGridStructure() {
        simulationController.runSimulation();

        verify(logger, times(1)).log(mockGridStructure, 0);
    }

    /**
     * Tests whether the SimulationController invokes the separate simulation tasks in the correct order.
     */
    @Test
    void testCorrectOrderOfSimulationTasks() {
        simulationController.runSimulation();

        InOrder inOrder = inOrder(logger, neighborhoodStrategy, simulationState, mockGridStructure, ruleSet, terminator, mockGridStructure,
                logger, simulationState);

        inOrder.verify(logger, times(1)).log(simulationState.getCurrentGrid(), 0);
        inOrder.verify(neighborhoodStrategy, atLeast(1)).computeAliveNeighbors(eq(mockGridStructure), anyInt(), anyInt());
        inOrder.verify(mockGridStructure, atLeast(1)).setCellAliveNeighbors(anyInt(), anyInt(), anyInt());
        inOrder.verify(ruleSet, atLeast(1)).computeNextState(mockGridStructure.getCell(anyInt(), anyInt()));
        inOrder.verify(terminator, atLeast(1)).shouldTerminate(simulationState);
        inOrder.verify(mockGridStructure, atLeast(1)).setCellState(anyInt(), anyInt(), anyInt());
        // logs the result of a simulation step, if this simulation step does not terminate the simulation
        if (!terminator.shouldTerminate(simulationState)) {
            inOrder.verify(logger).log(simulationState.getCurrentGrid(),  simulationState.getCurrentSimulationStep());
        }
        inOrder.verify(simulationState, atLeast(1)).setCurrentSimulationStep(simulationState.getCurrentSimulationStep() + 1);

    }


    /**
     * Tests whether the SimulationController run a Simulation for multiple simulation steps.
     */
    @Test
    void testRunningSimulationForMultipleSimulationSteps() {
        simulationController.runSimulation();

        verify(logger, times(1)).log(mockGridStructure, 0);
        verify(neighborhoodStrategy, atLeast(2)).computeAliveNeighbors(simulationState.getPreviousGrid(), 0, 0);
        verify(simulationState, atLeast(2)).getPreviousGrid();
        verify(mockGridStructure, atLeast(1)).setCellAliveNeighbors(anyInt(), anyInt(), anyInt());
        verify(ruleSet, atLeast(18)).computeNextState(any(ICell.class));
        verify(simulationState, atLeast(55)).getCurrentGrid();
        verify(mockGridStructure, atLeast(18)).setCellState(anyInt(), anyInt(), anyInt());
        verify(terminator, atLeast(2)).shouldTerminate(simulationState);
        verify(logger, atLeast(2)).log(eq(mockGridStructure), any(Integer.class));
        verify(simulationState, atLeast(2)).setCurrentSimulationStep(any(Integer.class));
    }

    /**
     * Tests whether the SimulationController terminates the running simulation when the mock Terminator returns true
     * after multiple steps.
     */
    @Test
    void testTerminatingTheRunningSimulationAfterMultipleSteps() {
        simulationController.runSimulation();

        verify(neighborhoodStrategy, times(5)).computeAliveNeighbors(simulationState.getPreviousGrid(), 0, 0);
        // value of 181 because of the previous verify statement
        verify(simulationState, times(181)).getPreviousGrid();
        verify(mockGridStructure, times(5)).setCellAliveNeighbors(eq(0), eq(0), anyInt());
        verify(ruleSet, times(45)).computeNextState(any(ICell.class));
        verify(simulationState, times(94)).getCurrentGrid();
        verify(mockGridStructure, times(90)).setCellState(anyInt(), anyInt(), anyInt());
        verify(terminator, times(5)).shouldTerminate(simulationState);
        verify(logger, times(5)).log(eq(mockGridStructure), any(Integer.class));
        verify(simulationState, times(5)).setCurrentSimulationStep(any(Integer.class));
    }

    /**
     * Tests whether the SimulationController correctly invokes logging of every simulation step and that said logging
     * then is actually performed correctly.
     *
     * This test might be moved to an integration test class.
     */
    @Test
    void testLoggingWithConsoleLogger() {
        ConsoleLogger consoleLogger = new ConsoleLogger();
        SimulationController simulationController1 = new SimulationController(mockGridStructure, gridStructureFactory,
                ruleSet, neighborhoodStrategy, terminator, consoleLogger, simulationState);
        simulationController1.runSimulation();

        verify(neighborhoodStrategy, times(5)).computeAliveNeighbors(simulationState.getPreviousGrid(), 0, 0);
        // value of 181 because of the previous verify statement
        verify(simulationState, times(181)).getPreviousGrid();
        verify(mockGridStructure, times(5)).setCellAliveNeighbors(eq(0), eq(0), anyInt());
        verify(ruleSet, times(45)).computeNextState(any(ICell.class));
        verify(simulationState, times(94)).getCurrentGrid();
        verify(mockGridStructure, times(90)).setCellState(anyInt(), anyInt(), anyInt());
        verify(terminator, times(5)).shouldTerminate(simulationState);
        verify(simulationState, times(5)).setCurrentSimulationStep(any(Integer.class));

        Assertions.assertEquals("### 0" + System.lineSeparator() + 
                "010" + System.lineSeparator() + 
                "010" + System.lineSeparator() + 
                "110" + System.lineSeparator() + 
                "### 1" + System.lineSeparator() + 
                "010" + System.lineSeparator() + 
                "010" + System.lineSeparator() + 
                "110" + System.lineSeparator() + 
                "### 1" + System.lineSeparator() + 
                "010" + System.lineSeparator() + 
                "010" + System.lineSeparator() + 
                "110" + System.lineSeparator() + 
                "### 1" + System.lineSeparator() + 
                "010" + System.lineSeparator() + 
                "010" + System.lineSeparator() + 
                "110" + System.lineSeparator() + 
                "### 1" + System.lineSeparator() + 
                "010" + System.lineSeparator() + 
                "010" + System.lineSeparator() + 
                "110" + System.lineSeparator() + 
                "END OF SIMULATION" + System.lineSeparator() + System.lineSeparator(), outputStreamCaptor.toString());
    }

    /**
     * Tests whether the first five steps of the simulation using SimulationController containing a GridStructureArray,
     * a ParityRuleSet, vonNeumannNeighborhood a FixedStepTerminator and a ConsoleLogger are correct.
     *
     * This test might be moved to an integration test class.
     */
    @Test
    void testFirstThreeStepsWithGridStructureArrayParityVonNeumannFixedStepTerminatorAndConsoleLogger(){
        GridStructureArray gridStructureArray = new GridStructureArray(3,3, new CellFactory());
        gridStructureArray.setCellState(0,1,1);
        gridStructureArray.setCellState(1,1,1);
        gridStructureArray.setCellState(2,1,1);
        gridStructureArray.setCellState(2,0,1);
        GridStructureFactory gridStructureFactory = new GridStructureFactory();
        ParityRuleSet parityRuleSet = new ParityRuleSet();
        VonNeumannNeighborhood vonNeumannNeighborhood = new VonNeumannNeighborhood();
        FixedStepTerminator fixedStepTerminator = new FixedStepTerminator(5);
        ConsoleLogger consoleLogger = new ConsoleLogger();
        SimulationController simulationController1 = new SimulationController(gridStructureArray, gridStructureFactory,
                parityRuleSet, vonNeumannNeighborhood, fixedStepTerminator, consoleLogger);

        simulationController1.runSimulation();

        Assertions.assertEquals("### 0" + System.lineSeparator() + 
                "010" + System.lineSeparator() + 
                "010" + System.lineSeparator() + 
                "110" + System.lineSeparator() + 
                "### 1" + System.lineSeparator() + 
                "111" + System.lineSeparator() + 
                "001" + System.lineSeparator() + 
                "101" + System.lineSeparator() + 
                "### 2" + System.lineSeparator() + 
                "100" + System.lineSeparator() + 
                "000" + System.lineSeparator() + 
                "001" + System.lineSeparator() + 
                "### 3" + System.lineSeparator() + 
                "010" + System.lineSeparator() + 
                "101" + System.lineSeparator() + 
                "010" + System.lineSeparator() + 
                "### 4" + System.lineSeparator() + 
                "000" + System.lineSeparator() + 
                "000" + System.lineSeparator() + 
                "000" + System.lineSeparator() + 
                "### 5" + System.lineSeparator() + 
                "000" + System.lineSeparator() + 
                "000" + System.lineSeparator() + 
                "000" + System.lineSeparator() + 
                "END OF SIMULATION" + System.lineSeparator() + System.lineSeparator(), outputStreamCaptor.toString());
    }
}
