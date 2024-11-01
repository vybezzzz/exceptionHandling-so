package de.nordakademie.experiments;

import de.nordakademie.core.neighborhoodStrategy.MooreNeighborhood;
import de.nordakademie.core.neighborhoodStrategy.VonNeumannNeighborhood;
import de.nordakademie.core.ruleSet.GameOfLifeRuleSet;
import de.nordakademie.core.ruleSet.ParityRuleSet;
import de.nordakademie.core.simulationController.SimulationController;
import de.nordakademie.core.terminator.FixedStepTerminator;
import de.nordakademie.implementation.cell.Cell;
import de.nordakademie.implementation.cell.CellFactory;
import de.nordakademie.implementation.gridStructure.GridStructureHashMap;
import de.nordakademie.implementation.gridStructure.GridStructureFactory;
import de.nordakademie.view.logger.ConsoleLogger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;


public class GameOfLifeExperiment3Structure2Test {

    GridStructureHashMap gridStructureHashMap;
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
        int gridStructureHeight = 30;
        int gridStructureWidth = 30;

        // setting up GridStructureArray using CellFactory
        cellFactory = new CellFactory();
        gridStructureHashMap = new GridStructureHashMap(gridStructureHeight, gridStructureWidth, cellFactory);
        gridStructureFactory = new GridStructureFactory();

        // even lines initialized as: dead -> alive -> dead -> ...
        // odd lines the other way, being: alive -> dead -> alive -> ...
        for (int y = 0; y < gridStructureHeight; y++) {
            for (int x = 0; x < gridStructureWidth; x++) {
                gridStructureHashMap.setCellState(y, x, 0);
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
        simulationController = new SimulationController(gridStructureHashMap, gridStructureFactory, gameOfLifeRuleSet, mooreNeighborhood, fixedStepTerminator, consoleLogger);

        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));


    }

    @Test
    void testSetupOfGameOfLifeExperiment3Structure1WithWidthAndHeight30() {
        simulationController.runSimulation();

        boolean endOfSimulation = outputStreamCaptor.toString().contains("### 100" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "000000000000000000000000000000" + System.lineSeparator() + 
                "END OF SIMULATION" + System.lineSeparator());

        assertEquals(true, endOfSimulation);
    }


}
