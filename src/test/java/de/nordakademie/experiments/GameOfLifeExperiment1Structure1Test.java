package de.nordakademie.experiments;

import de.nordakademie.core.neighborhoodStrategy.MooreNeighborhood;
import de.nordakademie.core.ruleSet.GameOfLifeRuleSet;
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


import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameOfLifeExperiment1Structure1Test {
    GridStructureArray gridStructureArray;
    GridStructureFactory gridStructureFactory;
    GameOfLifeRuleSet gameOfLifeRuleSet;
    MooreNeighborhood mooreNeighborhood;
    FixedStepTerminator fixedStepTerminator;
    ConsoleLogger consoleLogger;
    SimulationController simulationController;
    ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    void setUp() {

        int gridStructureHeight = 40;
        int gridStructureWidth = 41;


        Cell[][] cells = new Cell[gridStructureHeight][gridStructureWidth];

        for (int y = 0; y < gridStructureHeight; y++) {
            for (int x = 0; x < gridStructureWidth; x++) {
                cells[y][x] = new Cell(y, x);
            }
        }


        cells[17][18].setState(1);
        cells[17][19].setState(1);
        cells[18][18].setState(1);
        cells[18][19].setState(1);
        cells[19][19].setState(1);
        cells[20][17].setState(1);
        cells[20][19].setState(1);
        cells[21][17].setState(1);
        cells[21][19].setState(1);
        cells[22][17].setState(1);
        cells[22][18].setState(1);

        cells[17][21].setState(1);
        cells[17][22].setState(1);
        cells[18][21].setState(1);
        cells[18][22].setState(1);
        cells[19][21].setState(1);
        cells[20][21].setState(1);
        cells[20][23].setState(1);
        cells[21][21].setState(1);
        cells[21][23].setState(1);
        cells[22][22].setState(1);
        cells[22][23].setState(1);

        gridStructureArray = new GridStructureArray(cells);
        gridStructureFactory = new GridStructureFactory();
        gameOfLifeRuleSet = new GameOfLifeRuleSet();
        mooreNeighborhood = new MooreNeighborhood();
        fixedStepTerminator = new FixedStepTerminator(100);
        consoleLogger = new ConsoleLogger();



        simulationController = new SimulationController(gridStructureArray, gridStructureFactory,
                gameOfLifeRuleSet, mooreNeighborhood, fixedStepTerminator, consoleLogger);

        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @Test
    void testSetupOfGameOfLifeExperiment1Structure1WithWidthAndHeight40x41() {
        simulationController.runSimulation();

        // Defining the expected pattern to check final output state after 100 steps
        String expectedPattern = "### 100" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000011011000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000001010000000000000000000" + System.lineSeparator() + 
                        "00000000000000001101010110000000000000000" + System.lineSeparator() + 
                        "00000000000000001110001110000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "00000000000000000000000000000000000000000" + System.lineSeparator() + 
                        "END OF SIMULATION" + System.lineSeparator();

        // Verifying that the output matches the expected pattern
        assertTrue(outputStreamCaptor.toString().contains(expectedPattern));

    }
}
