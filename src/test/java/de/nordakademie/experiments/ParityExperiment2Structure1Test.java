package de.nordakademie.experiments;

import de.nordakademie.core.neighborhoodStrategy.VonNeumannNeighborhood;
import de.nordakademie.core.ruleSet.ParityRuleSet;
import de.nordakademie.core.simulationController.SimulationController;
import de.nordakademie.core.terminator.FixedStepTerminator;
import de.nordakademie.implementation.cell.Cell;
import de.nordakademie.implementation.gridStructure.GridStructureArray;
import de.nordakademie.implementation.gridStructure.GridStructureFactory;
import de.nordakademie.view.logger.ConsoleLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ParityExperiment2Structure1Test {

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
        for (int y = 0; y < gridStructureHeight; y++) {
            for (int x = 0; x < gridStructureWidth; x++) {
                if ((1 + y) % 2 == 0) {
                    cells[y][x].setState(0);
                }
                if ((1 + y) % 4 == 0) {
                    cells[y][x].setState(1);
                }
                if (y % 2 == 1) {
                    cells[y-1][x].setState(0);
                }
                if (y % 4 == 1) {
                    cells[y-1][x].setState(1);
                }
            }
        }

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
                "0000000011000000011001100000001100000000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000100010001000001001000001000100010000" + System.lineSeparator() + 
                "0000100011001100010000100011001100010000" + System.lineSeparator() + 
                "1111111110111011110110111101110111111111" + System.lineSeparator() + 
                "1111111111111111101111011111111111111111" + System.lineSeparator() + 
                "0000100000001100001001000011000000010000" + System.lineSeparator() + 
                "1111011110110111111111111110110111101111" + System.lineSeparator() + 
                "0001000001000000010000100000001000001000" + System.lineSeparator() + 
                "0001000000000100011001100010000000001000" + System.lineSeparator() + 
                "1111011111110011110110111100111111101111" + System.lineSeparator() + 
                "1110011110110011110110111100110111100111" + System.lineSeparator() + 
                "0000000001000100001001000010001000000000" + System.lineSeparator() + 
                "0000000010000100001001000010000100000000" + System.lineSeparator() + 
                "1110011101110011110110111100111011100111" + System.lineSeparator() + 
                "0001100001001100011001100011001000011000" + System.lineSeparator() + 
                "1110111100111011100110011101110011110111" + System.lineSeparator() + 
                "1110011111110011110110111100111111100111" + System.lineSeparator() + 
                "0001000000000100001001000010000000001000" + System.lineSeparator() + 
                "0001100011001100011001100011001100011000" + System.lineSeparator() + 
                "0001100011001100011001100011001100011000" + System.lineSeparator() + 
                "0001000000000100001001000010000000001000" + System.lineSeparator() + 
                "1110011111110011110110111100111111100111" + System.lineSeparator() + 
                "1110111100111011100110011101110011110111" + System.lineSeparator() + 
                "0001100001001100011001100011001000011000" + System.lineSeparator() + 
                "1110011101110011110110111100111011100111" + System.lineSeparator() + 
                "0000000010000100001001000010000100000000" + System.lineSeparator() + 
                "0000000001000100001001000010001000000000" + System.lineSeparator() + 
                "1110011110110011110110111100110111100111" + System.lineSeparator() + 
                "1111011111110011110110111100111111101111" + System.lineSeparator() + 
                "0001000000000100011001100010000000001000" + System.lineSeparator() + 
                "0001000001000000010000100000001000001000" + System.lineSeparator() + 
                "1111011110110111111111111110110111101111" + System.lineSeparator() + 
                "0000100000001100001001000011000000010000" + System.lineSeparator() + 
                "1111111111111111101111011111111111111111" + System.lineSeparator() + 
                "1111111110111011110110111101110111111111" + System.lineSeparator() + 
                "0000100011001100010000100011001100010000" + System.lineSeparator() + 
                "0000100010001000001001000001000100010000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000000011000000011001100000001100000000" + System.lineSeparator() + 
                "END OF SIMULATION" + System.lineSeparator() + System.lineSeparator());

        assertEquals(true, endOfSimulation);
    }


}
