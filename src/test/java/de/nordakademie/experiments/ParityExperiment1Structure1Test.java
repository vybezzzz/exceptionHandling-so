package de.nordakademie.experiments;

import de.nordakademie.core.neighborhoodStrategy.VonNeumannNeighborhood;
import de.nordakademie.core.ruleSet.ParityRuleSet;
import de.nordakademie.core.simulationController.SimulationController;
import de.nordakademie.core.terminator.FixedStepTerminator;
import de.nordakademie.implementation.cell.Cell;
import de.nordakademie.implementation.gridStructure.GridStructureArray;
import de.nordakademie.implementation.gridStructure.GridStructureFactory;
import de.nordakademie.view.logger.ConsoleLogger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ParityExperiment1Structure1Test {
    GridStructureArray gridStructureArray;
    GridStructureFactory gridStructureFactory;
    ParityRuleSet parityRuleSet;
    VonNeumannNeighborhood vonNeumannNeighborhood;
    FixedStepTerminator fixedStepTerminator;
    ConsoleLogger consoleLogger;
    SimulationController simulationController;
    ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    void setUp() {
        int gridStructureHeight = 40;
        int gridStructureWidth = 40;
        Cell[][] cells = new Cell[gridStructureHeight][gridStructureWidth];
        for (int y = 0; y < gridStructureHeight; y++) {
            for (int x = 0; x < gridStructureWidth; x++) {
                cells[y][x] = new Cell(y, x);
            }
        }
        cells[gridStructureHeight / 2 - 1][gridStructureHeight / 2 - 1].setState(1);
        cells[gridStructureHeight / 2][gridStructureHeight / 2 - 1].setState(1);
        cells[gridStructureHeight / 2 - 1][gridStructureHeight / 2].setState(1);
        cells[gridStructureHeight / 2][gridStructureHeight / 2].setState(1);

        gridStructureArray = new GridStructureArray(cells);

        gridStructureFactory = new GridStructureFactory();

        parityRuleSet = new ParityRuleSet();

        vonNeumannNeighborhood = new VonNeumannNeighborhood();

        fixedStepTerminator = new FixedStepTerminator(100);

        consoleLogger = new ConsoleLogger();

        simulationController = new SimulationController(gridStructureArray, gridStructureFactory,
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
