package de.nordakademie.experiments;

import de.nordakademie.core.neighborhoodStrategy.MooreNeighborhood;
import de.nordakademie.core.ruleSet.GameOfLifeRuleSet;
import de.nordakademie.core.simulationController.SimulationController;
import de.nordakademie.core.terminator.FixedStepTerminator;
import de.nordakademie.implementation.cell.CellFactory;
import de.nordakademie.implementation.gridStructure.GridStructureHashMap;
import de.nordakademie.implementation.gridStructure.GridStructureFactory;
import de.nordakademie.view.logger.ConsoleLogger;

public class GameOfLifeExperiment1Structure2 {
    public static void main(String[] args) {

        // setting up dimensions
        int gridStructureHeight = 40;
        int gridStructureWidth = 41;

        // setting up GridStructureHashMap using CellFactory
        CellFactory cellFactory = new CellFactory();
        GridStructureHashMap gridStructureHashMap = new GridStructureHashMap(gridStructureHeight, gridStructureWidth, cellFactory);
        GridStructureFactory gridStructureFactory = new GridStructureFactory();

        // setting up the specified initial configuration
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

        // setting up GameOfLifeRuleSet and MooreNeighborhood
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
