package de.nordakademie.experiments;

import de.nordakademie.core.neighborhoodStrategy.VonNeumannNeighborhood;
import de.nordakademie.core.ruleSet.ParityRuleSet;
import de.nordakademie.core.simulationController.SimulationController;
import de.nordakademie.core.terminator.FixedStepTerminator;
import de.nordakademie.implementation.cell.Cell;
import de.nordakademie.implementation.cell.CellFactory;
import de.nordakademie.implementation.gridStructure.GridStructureArray;
import de.nordakademie.implementation.gridStructure.GridStructureFactory;
import de.nordakademie.view.logger.ConsoleLogger;

public class ParityExperiment2Structure1 extends Experiment {
    static int gridStructureHeightAndWidth = 40;
    static GridStructureArray gridStructureArray = new GridStructureArray(gridStructureHeightAndWidth,
            gridStructureHeightAndWidth, new CellFactory());
    static GridStructureFactory gridStructureFactory = new GridStructureFactory();
    static ParityRuleSet parityRuleSet = new ParityRuleSet();
    static VonNeumannNeighborhood vonNeumannNeighborhood = new VonNeumannNeighborhood();
    static FixedStepTerminator fixedStepTerminator = new FixedStepTerminator(100);
    static ConsoleLogger consoleLogger = new ConsoleLogger();
    static SimulationController simulationController = new SimulationController(gridStructureArray, gridStructureFactory,
            parityRuleSet, vonNeumannNeighborhood, fixedStepTerminator, consoleLogger);

    public static void main(String[] args) {
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

        GridStructureArray gridStructureArray = new GridStructureArray(cells);

        GridStructureFactory gridStructureFactory = new GridStructureFactory();

        ParityRuleSet parityRuleSet = new ParityRuleSet();

        VonNeumannNeighborhood vonNeumannNeighborhood = new VonNeumannNeighborhood();

        FixedStepTerminator fixedStepTerminator = new FixedStepTerminator(100);

        ConsoleLogger consoleLogger = new ConsoleLogger();

        SimulationController simulationController = new SimulationController(gridStructureArray, gridStructureFactory,
                parityRuleSet, vonNeumannNeighborhood, fixedStepTerminator, consoleLogger);

        simulationController.runSimulation();
    }
}
