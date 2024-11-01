package de.nordakademie.experiments;

import de.nordakademie.core.neighborhoodStrategy.VonNeumannNeighborhood;
import de.nordakademie.core.ruleSet.ParityRuleSet;
import de.nordakademie.core.simulationController.SimulationController;
import de.nordakademie.core.terminator.FixedStepTerminator;
import de.nordakademie.implementation.cell.Cell;
import de.nordakademie.implementation.cell.CellFactory;
import de.nordakademie.implementation.gridStructure.GridStructureFactory;
import de.nordakademie.implementation.gridStructure.GridStructureHashMap;
import de.nordakademie.view.logger.ConsoleLogger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ParityExperiment1Structure2Test {

    GridStructureHashMap gridStructureHashMap;
    GridStructureFactory gridStructureFactory;
    ParityRuleSet parityRuleSet;
    VonNeumannNeighborhood vonNeumannNeighborhood;
    FixedStepTerminator fixedStepTerminator;
    ConsoleLogger consoleLogger;
    SimulationController simulationController;
    ByteArrayOutputStream outputStreamCaptor;
    CellFactory cellFactory;

    @BeforeEach
    void setUp() {
        // setting up dimensions
        int gridStructureHeight = 40;
        int gridStructureWidth = 40;

        // setting up GridStructureHashMap using CellFactory
        cellFactory = new CellFactory();
        gridStructureHashMap = new GridStructureHashMap(gridStructureHeight, gridStructureWidth, cellFactory);
        gridStructureFactory = new GridStructureFactory();

        // setting centered cells as alive
        int heightMid = gridStructureHeight / 2;
        int widthMid = gridStructureWidth / 2;
        gridStructureHashMap.setCellState(heightMid-1, widthMid-1, 1);
        gridStructureHashMap.setCellState(heightMid, widthMid-1, 1);
        gridStructureHashMap.setCellState(heightMid-1, widthMid, 1);
        gridStructureHashMap.setCellState(heightMid, widthMid, 1);

        // setting up ParityRuleSet and NeighborhoodStrategy
        parityRuleSet = new ParityRuleSet();
        vonNeumannNeighborhood = new VonNeumannNeighborhood();

        // setting up a FixedStepTerminator with 100 maxSteps
        fixedStepTerminator = new FixedStepTerminator(100);

        // setting up a ConsoleLogger
        consoleLogger = new ConsoleLogger();

        // initializing and execution of SimulationController
        simulationController = new SimulationController(gridStructureHashMap, gridStructureFactory,
                parityRuleSet, vonNeumannNeighborhood, fixedStepTerminator, consoleLogger);

        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testSetupOfParityExperiment1Structure1WithWidthAndHeight40() {
        simulationController.runSimulation();

        boolean endOfSimulation = outputStreamCaptor.toString().contains("### 100" + System.lineSeparator() + 
                "0000000000110000000000000000110000000000" + System.lineSeparator() + 
                "0000001100110011000110001100110011000000" + System.lineSeparator() + 
                "0000001100000011000110001100000011000000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000000000110001100000011000110000000000" + System.lineSeparator() + 
                "0110000000110001100110011000110000000110" + System.lineSeparator() + 
                "0110000000000000000110000000000000000110" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000000000000000000110000000000000000000" + System.lineSeparator() + 
                "1100011000000001100110011000000001100011" + System.lineSeparator() + 
                "1100011000000001100000011000000001100011" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0110000000000000000110000000000000000110" + System.lineSeparator() + 
                "0110011000110000000110000000110001100110" + System.lineSeparator() + 
                "0000011000110000000000000000110001100000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0110001101100011000000001100011011000110" + System.lineSeparator() + 
                "0110001101100011000000001100011011000110" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000011000110000000000000000110001100000" + System.lineSeparator() + 
                "0110011000110000000110000000110001100110" + System.lineSeparator() + 
                "0110000000000000000110000000000000000110" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "1100011000000001100000011000000001100011" + System.lineSeparator() + 
                "1100011000000001100110011000000001100011" + System.lineSeparator() + 
                "0000000000000000000110000000000000000000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0110000000000000000110000000000000000110" + System.lineSeparator() + 
                "0110000000110001100110011000110000000110" + System.lineSeparator() + 
                "0000000000110001100000011000110000000000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000001100000011000110001100000011000000" + System.lineSeparator() + 
                "0000001100110011000110001100110011000000" + System.lineSeparator() + 
                "0000000000110000000000000000110000000000" + System.lineSeparator() + 
                "END OF SIMULATION" + System.lineSeparator() + System.lineSeparator());

        assertEquals(true, endOfSimulation);
    }
}
