package de.nordakademie.experiments;

import de.nordakademie.core.errorHandler.ErrorHandler;
import de.nordakademie.core.neighborhoodStrategy.VonNeumannNeighborhood;
import de.nordakademie.core.ruleSet.ParityRuleSet;
import de.nordakademie.core.simulationController.SimulationController;
import de.nordakademie.core.terminator.FixedStepTerminator;
import de.nordakademie.core.terminator.ITerminator;
import de.nordakademie.implementation.cell.CellFactory;
import de.nordakademie.implementation.gridStructure.GridStructureArray;
import de.nordakademie.implementation.gridStructure.GridStructureFactory;
import de.nordakademie.implementation.gridStructure.GridStructureHashMap;
import de.nordakademie.view.logger.ConsoleLogger;
import de.nordakademie.view.logger.ILogger;

public class ParityExperiment1Structure2 {
    static int gridStructureHeightAndWidth = 40;
    static GridStructureHashMap gridStructureHashMap;
    static GridStructureFactory gridStructureFactory = new GridStructureFactory();
    static ParityRuleSet parityRuleSet = new ParityRuleSet();
    static VonNeumannNeighborhood vonNeumannNeighborhood = new VonNeumannNeighborhood();
    static FixedStepTerminator fixedStepTerminator = new FixedStepTerminator(100);
    static ConsoleLogger consoleLogger = new ConsoleLogger();
    static SimulationController simulationController;

    public static void main(String[] args) {
        try {
            gridStructureHashMap = new GridStructureHashMap(gridStructureHeightAndWidth,
                    gridStructureHeightAndWidth, new CellFactory());
            gridStructureHashMap.setCellState(gridStructureHeightAndWidth / 2 - 1, gridStructureHeightAndWidth / 2 - 1, 1);
            gridStructureHashMap.setCellState(gridStructureHeightAndWidth / 2, gridStructureHeightAndWidth / 2 - 1, 1);
            gridStructureHashMap.setCellState(gridStructureHeightAndWidth / 2 - 1, gridStructureHeightAndWidth / 2, 1);
            gridStructureHashMap.setCellState(gridStructureHeightAndWidth / 2, gridStructureHeightAndWidth / 2, 1);

            simulationController = new SimulationController(gridStructureHashMap, gridStructureFactory,
                    parityRuleSet, vonNeumannNeighborhood, fixedStepTerminator, consoleLogger);

            simulationController.runSimulation();
        } catch (ExceptionInInitializerError e) {
            ErrorHandler.handleException(ParityExperiment1Structure1.class, e);
        } catch (IllegalArgumentException e) {
            ErrorHandler.handleException(ParityExperiment1Structure1.class, e);
        }
    }

    public void executeExperiment(ITerminator terminator, ILogger logger) {
        try {
            gridStructureHashMap = new GridStructureHashMap(gridStructureHeightAndWidth,
                    gridStructureHeightAndWidth, new CellFactory());
            gridStructureHashMap.setCellState(gridStructureHeightAndWidth / 2 - 1, gridStructureHeightAndWidth / 2 - 1, 1);
            gridStructureHashMap.setCellState(gridStructureHeightAndWidth / 2, gridStructureHeightAndWidth / 2 - 1, 1);
            gridStructureHashMap.setCellState(gridStructureHeightAndWidth / 2 - 1, gridStructureHeightAndWidth / 2, 1);
            gridStructureHashMap.setCellState(gridStructureHeightAndWidth / 2, gridStructureHeightAndWidth / 2, 1);

            simulationController = new SimulationController(gridStructureHashMap, gridStructureFactory,
                    parityRuleSet, vonNeumannNeighborhood, terminator, logger);

            simulationController.runSimulation();
        } catch (ExceptionInInitializerError e) {
            ErrorHandler.handleException(ParityExperiment1Structure1.class, e);
        } catch (IllegalArgumentException e) {
            ErrorHandler.handleException(ParityExperiment1Structure1.class, e);
        }
    }
}
