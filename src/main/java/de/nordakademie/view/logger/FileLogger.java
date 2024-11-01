package de.nordakademie.view.logger;

import de.nordakademie.core.errorHandler.ErrorCode;
import de.nordakademie.core.errorHandler.ErrorHandler;
import de.nordakademie.implementation.gridStructure.IGridStructure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logger that logs the simulation steps to a file.
 */
public class FileLogger implements ILogger {

    private final String filename;

    /**
     * Constructor that creates a new log file.
     */
    public FileLogger() {

        File logDir = new File("logs");
        if (!logDir.exists()) {
            logDir.mkdir();
        }
        this.filename = "logs/log_" + new SimpleDateFormat("yyyyddMM_HHmmss").format(new Date()) + ".txt";
    }

    /**
     * Getter for the filename of the log file.
     * It is only used for tests!!
     *
     * @return filename of the log file
     */
    public String getFilename() {

        return filename;
    }

    /**
     * Logs a complete GridStructure for the current simulation step.
     *
     * @param gridStructure GridStructure at the end of the current simulation step
     * @param currentStep   current simulation step
     */
    @Override
    public void log(IGridStructure gridStructure, int currentStep) throws IllegalArgumentException {
        // ExceptionHandling
        if (gridStructure == null) {
            throw new IllegalArgumentException(ErrorCode.LOGGER_GRID_NULL.getMessage() + " [" + ErrorCode.LOGGER_GRID_NULL.getCode() + "]");
        }
        if (currentStep < 0) {
            throw new IllegalArgumentException(ErrorCode.LOGGER_CURRENT_STEP.getMessage() + " [" + ErrorCode.LOGGER_CURRENT_STEP.getCode() + "]");
        }

        try (FileWriter fileWriter = new FileWriter(filename, true);
             PrintWriter writer = new PrintWriter(fileWriter)) {

            writer.println("### " + currentStep);

            for (int y = 0; y < gridStructure.getHeight(); y++) {
                for (int x = 0; x < gridStructure.getWidth(); x++) {
                    writer.print(gridStructure.getCellState(y, x));
                }
                writer.println();
            }
        } catch (IOException e) {
            ErrorHandler.handleException(FileLogger.class, e);
        }
    }

    /**
     * last print statement at the end of the simulation
     */
    public void logEndOfSimulation() {

        try (FileWriter fileWriter = new FileWriter(filename, true);
             PrintWriter writer = new PrintWriter(fileWriter)) {
            writer.println("END OF SIMULATION");
        } catch (IOException e) {
            ErrorHandler.handleException(FileLogger.class, e);
        }
    }

}