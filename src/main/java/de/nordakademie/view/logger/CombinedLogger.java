package de.nordakademie.view.logger;

import de.nordakademie.implementation.gridStructure.IGridStructure;

/**
 * CombinedLogger that logs the simulation steps to both the console and a file.
 */
public class CombinedLogger implements ILogger {

    private final ConsoleLogger consoleLogger;
    private final FileLogger fileLogger;

    /**
     * Constructor that initializes the ConsoleLogger and FileLogger.
     */
    public CombinedLogger() {

        this.consoleLogger = new ConsoleLogger();
        this.fileLogger = new FileLogger();
    }

    /**
     * Logs a complete GridStructure for the current simulation step to both the console and a file.
     *
     * @param gridStructure GridStructure at the end of the current simulation step
     * @param currentStep   current simulation step
     */
    @Override
    public void log(IGridStructure gridStructure, int currentStep) {

        consoleLogger.log(gridStructure, currentStep);
        fileLogger.log(gridStructure, currentStep);
    }

    /**
     * Logs the end of the simulation to both the console and a file.
     */
    public void logEndOfSimulation() {

        consoleLogger.logEndOfSimulation();
        fileLogger.logEndOfSimulation();
    }

    /**
     * Getter for the FileLogger instance.
     *
     * @return the FileLogger instance
     */
    public FileLogger getFileLogger() {

        return fileLogger;
    }
}