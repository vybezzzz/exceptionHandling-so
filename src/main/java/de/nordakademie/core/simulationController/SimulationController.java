package de.nordakademie.core.simulationController;

import de.nordakademie.core.errorHandler.ErrorHandler;
import de.nordakademie.core.neighborhoodStrategy.INeighborhoodStrategy;
import de.nordakademie.core.ruleSet.IRuleSet;
import de.nordakademie.core.terminator.ITerminator;
import de.nordakademie.implementation.gridStructure.IGridStructure;
import de.nordakademie.implementation.gridStructure.IGridStructureFactory;
import de.nordakademie.implementation.simulationState.ISimulationState;
import de.nordakademie.implementation.simulationState.SimulationState;
import de.nordakademie.view.logger.ILogger;

/**
 * Implements the controller of the simulation which has to be run.
 * The Controller controls the simulation process and invokes the necessary actions of IGridStructure,
 * IGridStructureFactory, IRuleSet, INeighborhoodStrategy, ITerminator, ILogger and ISimulationState objects to
 * perform every step of the simulation.
 */
public class SimulationController {
    private final IGridStructure gridStructure;
    private final IGridStructureFactory gridStructureFactory;
    private final IRuleSet ruleSet;
    private final INeighborhoodStrategy neighborhoodStrategy;
    private final ITerminator terminator;
    private final ILogger logger;
    private ISimulationState simulationState = null;

    /**
     * Constructs a SimulationController with a specified IGridStructure, IGridStructureFactory,
     * IRuleSet, INeighborhoodStrategy, ITerminator and ILogger.
     * Initializes the SimulationController with a current simulation step of 1 and a new instance of SimulationState.
     *
     * @param gridStructure         GridStructure of the Simulation
     * @param gridStructureFactory1 GridStructureFactory
     * @param ruleSet               RuleSet for the Simulation
     * @param neighborhoodStrategy  NeighborhoodStrategy for the Simulation
     * @param terminator            Terminator fpr the Simulation
     * @param logger                Logger for the Simulation
     */
    public SimulationController(IGridStructure gridStructure, IGridStructureFactory gridStructureFactory1,
                                IRuleSet ruleSet, INeighborhoodStrategy neighborhoodStrategy,
                                ITerminator terminator, ILogger logger) throws ExceptionInInitializerError {
        this.gridStructure = gridStructure;
        this.gridStructureFactory = gridStructureFactory1;
        this.ruleSet = ruleSet;
        this.neighborhoodStrategy = neighborhoodStrategy;
        this.terminator = terminator;
        this.logger = logger;
        this.simulationState = new SimulationState(gridStructureFactory.createGridStructure(gridStructure), gridStructure, 1);

    }

    /**
     * Constructs a SimulationController with a specified IGridStructure, IGridStructureFactory,
     * IRuleSet, INeighborhoodStrategy, ITerminator, ILogger and ISimulationState.
     * This Constructor is needed for appropriate testing.
     *
     * @param gridStructure         GridStructure of the Simulation
     * @param gridStructureFactory1 GridStructureFactory
     * @param ruleSet               RuleSet for the Simulation
     * @param neighborhoodStrategy  NeighborhoodStrategy for the Simulation
     * @param terminator            Terminator fpr the Simulation
     * @param logger                Logger for the Simulation
     * @param simulationState       SimulationState of the Simulation
     */
    public SimulationController(IGridStructure gridStructure, IGridStructureFactory gridStructureFactory1,
                                IRuleSet ruleSet, INeighborhoodStrategy neighborhoodStrategy,
                                ITerminator terminator, ILogger logger, ISimulationState simulationState) {
        this.gridStructure = gridStructure;
        this.gridStructureFactory = gridStructureFactory1;
        this.ruleSet = ruleSet;
        this.neighborhoodStrategy = neighborhoodStrategy;
        this.terminator = terminator;
        this.logger = logger;
        this.simulationState = simulationState;
    }

    /**
     * Runs the simulation by invoking the necessary actions of IGridStructure, IRuleSet,
     * INeighborhoodStrategy, ITerminator, ILogger and ISimulationState objects that were injected in the
     * SimulationController.
     * Therefore, it runs every step of the simulation, in which it computes the currentGrid per step,
     * checks the termination condition, and logs the result of said simulation step if the simulation ends.
     */
    public void runSimulation() {
        int currentCellAliveNeighbors;
        boolean terminationCondition = false;
        //ExceptionHandling
        try {
            // 0. Logging the initial GridStructure
            logger.log(gridStructure, 0);

            while (!terminationCondition) {
                // 1. compute new States of GridStructure using newGridStructure as temp. storage
                for (int y = 0; y < gridStructure.getHeight(); y++) {
                    for (int x = 0; x < gridStructure.getWidth(); x++) {
                        // 1.1. count and set aliveNeighbors
                        currentCellAliveNeighbors = neighborhoodStrategy.computeAliveNeighbors(simulationState.getPreviousGrid(), y, x);
                        simulationState.getPreviousGrid().setCellAliveNeighbors(y, x, currentCellAliveNeighbors);

                        // 1.2. compute new states according to aliveNeighbors of a cell
                        int nextState = ruleSet.computeNextState(simulationState.getPreviousGrid().getCell(y, x));
                        simulationState.getCurrentGrid().setCellState(y, x, nextState);
                    }
                }
                // 2. check termination condition of the current simulation step
                terminationCondition = terminator.shouldTerminate(simulationState);

                // 3. transfer of new states from currentGrid to gridStructure/previousGrid -> DeepCopy solution
                for (int y = 0; y < gridStructure.getHeight(); y++) {
                    for (int x = 0; x < gridStructure.getWidth(); x++) {
                        simulationState.getPreviousGrid().setCellState(y, x, simulationState.getCurrentGrid().getCellState(y, x));
                    }
                }
                if (terminationCondition) {
                    // 4. Logging the result of the simulation, if the termination condition is true.
                    logger.logEndOfSimulation();
                } else {
                    // 4. Logging the result of the current simulation step
                    logger.log(simulationState.getCurrentGrid(), simulationState.getCurrentSimulationStep());
                }

                // 5. update currentSimulationStep of SimulationState
                simulationState.setCurrentSimulationStep(simulationState.getCurrentSimulationStep() + 1);
            }
        } catch (Exception e) {
            ErrorHandler.handleException(SimulationController.this, e);
        }
    }
}
