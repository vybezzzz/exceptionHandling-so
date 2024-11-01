package de.nordakademie.experiments;

import de.nordakademie.core.neighborhoodStrategy.MooreNeighborhood;
import de.nordakademie.core.neighborhoodStrategy.VonNeumannNeighborhood;
import de.nordakademie.core.ruleSet.GameOfLifeRuleSet;
import de.nordakademie.core.ruleSet.ParityRuleSet;
import de.nordakademie.core.simulationController.SimulationController;
import de.nordakademie.core.terminator.FixedStepTerminator;
import de.nordakademie.implementation.cell.Cell;
import de.nordakademie.implementation.cell.CellFactory;
import de.nordakademie.implementation.gridStructure.GridStructureArray;
import de.nordakademie.implementation.gridStructure.GridStructureFactory;
import de.nordakademie.view.logger.ConsoleLogger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class GameOfLifeExperiment2Structure1Test {

    GridStructureArray gridStructureArray;
    GridStructureFactory gridStructureFactory;
    GameOfLifeRuleSet gameOfLifeRuleSet;
    MooreNeighborhood mooreNeighborhood;
    FixedStepTerminator fixedStepTerminator;
    ConsoleLogger consoleLogger;
    SimulationController simulationController;
    ByteArrayOutputStream outputStreamCaptor;
    CellFactory cellFactory;

    @BeforeEach
    void setUp() {

        // setting up dimensions
        int gridStructureHeight = 10;
        int gridStructureWidth = 10;

        // setting up GridStructureArray using CellFactory
        cellFactory = new CellFactory();
        gridStructureArray = new GridStructureArray(gridStructureHeight, gridStructureWidth, cellFactory);
        gridStructureFactory = new GridStructureFactory();

        // even lines initialized as: dead -> alive -> dead -> ...
        // odd lines the other way, being: alive -> dead -> alive -> ...
        for (int y = 0; y < gridStructureHeight; y++) {
            for (int x = 0; x < gridStructureWidth; x++) {
                if ((1 + y) % 2 == 0) {
                    gridStructureArray.setCellState(y, x, 0);
                }
                if ((1 + y) % 4 == 0) {
                    gridStructureArray.setCellState(y, x, 1);
                }
                if (y % 2 == 1) {
                    gridStructureArray.setCellState(y - 1, x, 0);
                }
                if (y % 4 == 1) {
                    gridStructureArray.setCellState(y - 1, x, 1);
                }
            }
        }

        // setting of GameOfLifeRuleSet and MooreNeighborhoodStrategy
        gameOfLifeRuleSet = new GameOfLifeRuleSet();
        mooreNeighborhood = new MooreNeighborhood();

        // setting up a FixedStepTerminator with 100 maxSteps
        fixedStepTerminator = new FixedStepTerminator(100);

        // setting up a ConsoleLogger
        consoleLogger = new ConsoleLogger();

        // initializing and execution of SimulationController
        simulationController = new SimulationController(gridStructureArray,
                gridStructureFactory, gameOfLifeRuleSet, mooreNeighborhood, fixedStepTerminator, consoleLogger);

        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testSetupOfGameOfLifeExperiment2Structure1WithWidthAndHeight10() {
        simulationController.runSimulation();

        boolean endOfSimulation = outputStreamCaptor.toString().contains("### 10" + System.lineSeparator() + 
                "0110000110" + System.lineSeparator() + 
                "1010000101" + System.lineSeparator() + 
                "0010000100" + System.lineSeparator() + 
                "0010000100" + System.lineSeparator() + 
                "0010000100" + System.lineSeparator() + 
                "0000000000" + System.lineSeparator() + 
                "1000000001" + System.lineSeparator() + 
                "0000000000" + System.lineSeparator() + 
                "0000000000" + System.lineSeparator() + 
                "0000000000" + System.lineSeparator());

        assertEquals(true, endOfSimulation);
    }
}
