package de.nordakademie.experiments;

import de.nordakademie.core.neighborhoodStrategy.MooreNeighborhood;
import de.nordakademie.core.ruleSet.GameOfLifeRuleSet;
import de.nordakademie.core.simulationController.SimulationController;
import de.nordakademie.core.terminator.FixedStepTerminator;
import de.nordakademie.implementation.cell.CellFactory;
import de.nordakademie.implementation.gridStructure.GridStructureHashMap;
import de.nordakademie.implementation.gridStructure.GridStructureFactory;
import de.nordakademie.view.logger.ConsoleLogger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class GameOfLifeExperiment1Structure2Test {
    // Declaration of essential components for the test
    GridStructureHashMap gridStructureHashMap;
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


        CellFactory cellFactory = new CellFactory();
        gridStructureHashMap = new GridStructureHashMap(gridStructureHeight, gridStructureWidth, cellFactory);
        gridStructureFactory = new GridStructureFactory();


        gridStructureHashMap.setCellState(17, 18, 1);
        gridStructureHashMap.setCellState(17, 19, 1);
        gridStructureHashMap.setCellState(18, 18, 1);
        gridStructureHashMap.setCellState(18, 19, 1);
        gridStructureHashMap.setCellState(19, 19, 1);
        gridStructureHashMap.setCellState(20, 17, 1);
        gridStructureHashMap.setCellState(20, 19, 1);
        gridStructureHashMap.setCellState(21, 17, 1);
        gridStructureHashMap.setCellState(21, 19, 1);
        gridStructureHashMap.setCellState(22, 17, 1);
        gridStructureHashMap.setCellState(22, 18, 1);

        gridStructureHashMap.setCellState(17, 21, 1);
        gridStructureHashMap.setCellState(17, 22, 1);
        gridStructureHashMap.setCellState(18, 21, 1);
        gridStructureHashMap.setCellState(18, 22, 1);
        gridStructureHashMap.setCellState(19, 21, 1);
        gridStructureHashMap.setCellState(20, 21, 1);
        gridStructureHashMap.setCellState(20, 23, 1);
        gridStructureHashMap.setCellState(21, 21, 1);
        gridStructureHashMap.setCellState(21, 23, 1);
        gridStructureHashMap.setCellState(22, 22, 1);
        gridStructureHashMap.setCellState(22, 23, 1);

        gameOfLifeRuleSet = new GameOfLifeRuleSet();
        mooreNeighborhood = new MooreNeighborhood();
        fixedStepTerminator = new FixedStepTerminator(100);
        consoleLogger = new ConsoleLogger();

        simulationController = new SimulationController(gridStructureHashMap, gridStructureFactory,
                gameOfLifeRuleSet, mooreNeighborhood, fixedStepTerminator, consoleLogger);

        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void testSetupOfGameOfLifeExperiment1Structure2WithWidthAndHeight40x41() {
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
