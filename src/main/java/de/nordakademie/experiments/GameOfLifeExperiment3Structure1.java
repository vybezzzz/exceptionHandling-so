package de.nordakademie.experiments;

import de.nordakademie.core.neighborhoodStrategy.MooreNeighborhood;
import de.nordakademie.core.ruleSet.GameOfLifeRuleSet;
import de.nordakademie.core.simulationController.SimulationController;
import de.nordakademie.core.terminator.FixedStepTerminator;
import de.nordakademie.implementation.cell.CellFactory;
import de.nordakademie.implementation.gridStructure.GridStructureArray;
import de.nordakademie.implementation.gridStructure.GridStructureFactory;
import de.nordakademie.implementation.gridStructure.IGridStructureFactory;
import de.nordakademie.view.logger.ConsoleLogger;


public class GameOfLifeExperiment3Structure1 {

    public static void main(String[] args) {

        // setting up dimensions
        int gridStructureHeight = 300;
        int gridStructureWidth = 300;

        // setting up GridStructureArray using CellFactory
        CellFactory cellFactory = new CellFactory();
        GridStructureArray gridStructureArray = new GridStructureArray(gridStructureHeight, gridStructureWidth, cellFactory);
        IGridStructureFactory gridStructureFactory = new GridStructureFactory();

        // setting all cells to dead
        for (int y = 0; y < gridStructureHeight; y++) {
            for (int x = 0; x < gridStructureWidth; x++) {
                gridStructureArray.setCellState(y, x, 0);
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
        SimulationController simulationController = new SimulationController(gridStructureArray, gridStructureFactory
        ,gameOfLifeRuleSet, mooreNeighborhood, fixedStepTerminator, consoleLogger);
        simulationController.runSimulation();
    }


}
