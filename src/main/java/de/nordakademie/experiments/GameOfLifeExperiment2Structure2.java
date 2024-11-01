package de.nordakademie.experiments;

import de.nordakademie.core.neighborhoodStrategy.MooreNeighborhood;
import de.nordakademie.core.ruleSet.GameOfLifeRuleSet;
import de.nordakademie.core.simulationController.SimulationController;
import de.nordakademie.core.terminator.FixedStepTerminator;
import de.nordakademie.implementation.cell.CellFactory;
import de.nordakademie.implementation.gridStructure.GridStructureHashMap;
import de.nordakademie.implementation.gridStructure.GridStructureFactory;
import de.nordakademie.view.logger.ConsoleLogger;


public class GameOfLifeExperiment2Structure2 {

    public static void main(String[] args) {

        // setting up dimensions
        int gridStructureHeight = 100;
        int gridStructureWidth = 100;

        // setting up GridStructureArray using CellFactory
        CellFactory cellFactory = new CellFactory();
        GridStructureHashMap gridStructureHashMap = new GridStructureHashMap(gridStructureHeight, gridStructureWidth, cellFactory);
        GridStructureFactory gridStructureFactory = new GridStructureFactory();

        // even lines initialized as: dead -> alive -> dead -> ...
        // odd lines the other way, being: alive -> dead -> alive -> ...
        for (int y = 0; y < gridStructureHeight; y++) {
            for (int x = 0; x < gridStructureWidth; x++) {
                if ((1 + y) % 2 == 0) {
                    gridStructureHashMap.setCellState(y, x, 0);
                }
                if ((1 + y) % 4 == 0) {
                    gridStructureHashMap.setCellState(y, x, 1);
                }
                if (y % 2 == 1) {
                    gridStructureHashMap.setCellState(y - 1, x, 0);
                }
                if (y % 4 == 1) {
                    gridStructureHashMap.setCellState(y - 1, x, 1);
                }
            }
        }

        // setting of GameOfLifeRuleSet and MooreNeighborhoodStrategy
        GameOfLifeRuleSet gameOfLifeRuleSet = new GameOfLifeRuleSet();
        MooreNeighborhood mooreNeighborhood = new MooreNeighborhood();

        // setting up a FixedStepTerminator with 100 maxSteps
        FixedStepTerminator fixedStepTerminator = new FixedStepTerminator(100);

        // setting up a ConsoleLogger
        ConsoleLogger consoleLogger = new ConsoleLogger();

        // initializing and execution of SimulationController
        SimulationController simulationController = new SimulationController(gridStructureHashMap, gridStructureFactory, gameOfLifeRuleSet, mooreNeighborhood, fixedStepTerminator, consoleLogger);
        simulationController.runSimulation();
    }

}
