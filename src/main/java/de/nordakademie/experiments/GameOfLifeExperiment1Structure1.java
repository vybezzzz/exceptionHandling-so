package de.nordakademie.experiments;

import de.nordakademie.core.neighborhoodStrategy.MooreNeighborhood;
import de.nordakademie.core.ruleSet.GameOfLifeRuleSet;
import de.nordakademie.core.simulationController.SimulationController;
import de.nordakademie.core.terminator.FixedStepTerminator;
import de.nordakademie.implementation.cell.Cell;
import de.nordakademie.implementation.gridStructure.GridStructureArray;
import de.nordakademie.implementation.gridStructure.GridStructureFactory;
import de.nordakademie.view.logger.ConsoleLogger;

public class GameOfLifeExperiment1Structure1 extends Experiment{

    public static void main(String[] args) {

        // setting up dimensions
        int gridStructureHeight = 40;
        int gridStructureWidth = 41;

        Cell[][] cells = new Cell[gridStructureHeight][gridStructureWidth];

        for (int y = 0; y < gridStructureHeight; y++) {
            for (int x = 0; x < gridStructureWidth; x++) {
                cells[y][x] = new Cell(y, x);
            }
        }

        // initialize specific cells with alive state
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

        // setting up gridStructureArray and gridStructureFactory
        GridStructureArray gridStructureArray = new GridStructureArray(cells);
        GridStructureFactory gridStructureFactory = new GridStructureFactory();

        // setting up GameOfLifeRuleSet and MooreNeighborhood
        GameOfLifeRuleSet gameOfLifeRuleSet = new GameOfLifeRuleSet();
        MooreNeighborhood mooreNeighborhood = new MooreNeighborhood();

        // setting up a FixedStepTerminator with 100 maxSteps
        FixedStepTerminator fixedStepTerminator = new FixedStepTerminator(100);

        // setting up a ConsoleLogger
        ConsoleLogger consoleLogger = new ConsoleLogger();

        SimulationController simulationController = new SimulationController(gridStructureArray, gridStructureFactory, gameOfLifeRuleSet, mooreNeighborhood, fixedStepTerminator, consoleLogger);

        simulationController.runSimulation();
    }

}
