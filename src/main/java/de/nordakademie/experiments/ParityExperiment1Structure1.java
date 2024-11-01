package de.nordakademie.experiments;

import de.nordakademie.core.errorHandler.ErrorHandler;
import de.nordakademie.core.neighborhoodStrategy.VonNeumannNeighborhood;
import de.nordakademie.core.ruleSet.ParityRuleSet;
import de.nordakademie.core.simulationController.SimulationController;
import de.nordakademie.core.terminator.FixedStepTerminator;
import de.nordakademie.core.terminator.ITerminator;
import de.nordakademie.implementation.cell.Cell;
import de.nordakademie.implementation.cell.CellFactory;
import de.nordakademie.implementation.gridStructure.GridStructureArray;
import de.nordakademie.implementation.gridStructure.GridStructureFactory;
import de.nordakademie.view.logger.ConsoleLogger;
import de.nordakademie.view.logger.FileLogger;
import de.nordakademie.view.logger.ILogger;

public class ParityExperiment1Structure1 extends Experiment {
    static int gridStructureHeightAndWidth = 40;
    static GridStructureArray gridStructureArray;
    static GridStructureFactory gridStructureFactory = new GridStructureFactory();
    static ParityRuleSet parityRuleSet = new ParityRuleSet();
    static VonNeumannNeighborhood vonNeumannNeighborhood = new VonNeumannNeighborhood();
    static FixedStepTerminator fixedStepTerminator = new FixedStepTerminator(100);
    static ConsoleLogger consoleLogger = new ConsoleLogger();
    static SimulationController simulationController;

    public static void main(String[] args) {
        try {
            gridStructureArray = new GridStructureArray(gridStructureHeightAndWidth,
                    gridStructureHeightAndWidth, new CellFactory());
            gridStructureArray.setCellState(gridStructureHeightAndWidth / 2 - 1, gridStructureHeightAndWidth / 2 - 1, 1);
            gridStructureArray.setCellState(gridStructureHeightAndWidth / 2, gridStructureHeightAndWidth / 2 - 1, 1);
            gridStructureArray.setCellState(gridStructureHeightAndWidth / 2 - 1, gridStructureHeightAndWidth / 2, 1);
            gridStructureArray.setCellState(gridStructureHeightAndWidth / 2, gridStructureHeightAndWidth / 2, 1);

            simulationController = new SimulationController(gridStructureArray, gridStructureFactory,
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
            gridStructureArray = new GridStructureArray(gridStructureHeightAndWidth,
                    gridStructureHeightAndWidth, new CellFactory());
            gridStructureArray.setCellState(gridStructureHeightAndWidth / 2 - 1, gridStructureHeightAndWidth / 2 - 1, 1);
            gridStructureArray.setCellState(gridStructureHeightAndWidth / 2, gridStructureHeightAndWidth / 2 - 1, 1);
            gridStructureArray.setCellState(gridStructureHeightAndWidth / 2 - 1, gridStructureHeightAndWidth / 2, 1);
            gridStructureArray.setCellState(gridStructureHeightAndWidth / 2, gridStructureHeightAndWidth / 2, 1);

            simulationController = new SimulationController(gridStructureArray, gridStructureFactory,
                    parityRuleSet, vonNeumannNeighborhood, terminator, logger);

            simulationController.runSimulation();
        } catch (ExceptionInInitializerError e) {
            ErrorHandler.handleException(ParityExperiment1Structure1.class, e);
        } catch (IllegalArgumentException e) {
            ErrorHandler.handleException(ParityExperiment1Structure1.class, e);
        }
    }

}
