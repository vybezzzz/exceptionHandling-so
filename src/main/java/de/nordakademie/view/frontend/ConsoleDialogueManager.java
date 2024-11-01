package de.nordakademie.view.frontend;

import de.nordakademie.core.errorHandler.ErrorCode;
import de.nordakademie.core.errorHandler.ErrorHandler;
import de.nordakademie.core.errorHandler.InvalidSelectionException;
import de.nordakademie.core.terminator.CombinedTerminator;
import de.nordakademie.core.terminator.FixedStepTerminator;
import de.nordakademie.core.terminator.ITerminator;
import de.nordakademie.core.terminator.StableStateTerminator;
import de.nordakademie.experiments.*;
import de.nordakademie.view.logger.*;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Manages the console-based interaction for selecting and executing experiments.
 * <p>
 * This class provides a user interface in the console, allowing users to select
 * experiments and configure execution settings such as terminators and loggers.
 * </p>
 *
 * <p>The {@link #startConsoleDialogue(Scanner)} method initiates the interactive console
 * session, guiding the user through various selections for running experiments.</p>
 *
 * @author Lasse Tjark Bode
 */
public class ConsoleDialogueManager {
    static GameOfLifeExperiment1Structure1 experiment1;
    static GameOfLifeExperiment1Structure2 experiment2;
    static GameOfLifeExperiment2Structure1 experiment3;
    static GameOfLifeExperiment2Structure2 experiment4;
    static GameOfLifeExperiment3Structure1 experiment5;
    static GameOfLifeExperiment3Structure2 experiment6;
    static ParityExperiment1Structure1 experiment7;
    static ParityExperiment1Structure2 experiment8;
    static ParityExperiment2Structure1 experiment9;
    static ParityExperiment2Structure2 experiment10;

    /**
     *
     * @param experiment1
     * @param experiment2
     * @param experiment3
     * @param experiment4
     * @param experiment5
     * @param experiment6
     * @param experiment7
     * @param experiment8
     * @param experiment9
     * @param experiment10
     */
    public ConsoleDialogueManager(GameOfLifeExperiment1Structure1 experiment1, GameOfLifeExperiment1Structure2 experiment2,
                                  GameOfLifeExperiment2Structure1 experiment3, GameOfLifeExperiment2Structure2 experiment4,
                                  GameOfLifeExperiment3Structure1 experiment5, GameOfLifeExperiment3Structure2 experiment6,
                                  ParityExperiment1Structure1 experiment7, ParityExperiment1Structure2 experiment8,
                                  ParityExperiment2Structure1 experiment9, ParityExperiment2Structure2 experiment10) {
        ConsoleDialogueManager.experiment1 = experiment1;
        ConsoleDialogueManager.experiment2 = experiment2;
        ConsoleDialogueManager.experiment3 = experiment3;
        ConsoleDialogueManager.experiment4 = experiment4;
        ConsoleDialogueManager.experiment5 = experiment5;
        ConsoleDialogueManager.experiment6 = experiment6;
        ConsoleDialogueManager.experiment7 = experiment7;
        ConsoleDialogueManager.experiment8 = experiment8;
        ConsoleDialogueManager.experiment9 = experiment9;
        ConsoleDialogueManager.experiment10 = experiment10;
    }

    /**
     * Starts the console-based interaction loop for selecting and executing experiments.
     * <p>
     * This method presents a menu to the user, allowing them to choose from various experiments
     * and configure settings such as the terminator and logger to be used. The loop runs
     * until the user chooses to exit.
     * </p>
     * @param scanner The scanner instance used for user input.
     */
    public void startConsoleDialogue(Scanner scanner) {
        int selection = -1;

        ITerminator terminator;
        ILogger logger;

        do {
            printInitialSelectionMenu();
            try {
                selection = scanner.nextInt();

                switch (selection) {
                    case 0:
                        break;
                    case 1:
                        terminator = askWhichTerminatorToUse(scanner);
                        logger = askWhichLoggerToUse(scanner);
                        //experiment1.executeExperiment(terminator, logger);
                        break;
                    case 2:
                        terminator = askWhichTerminatorToUse(scanner);
                        logger = askWhichLoggerToUse(scanner);
                        //experiment2.executeExperiment(terminator, logger);
                        break;
                    case 3:
                        terminator = askWhichTerminatorToUse(scanner);
                        logger = askWhichLoggerToUse(scanner);
                        //experiment3.executeExperiment(terminator, logger);
                        break;
                    case 4:
                        terminator = askWhichTerminatorToUse(scanner);
                        logger = askWhichLoggerToUse(scanner);
                        //experiment4.executeExperiment(terminator, logger);
                        break;
                    case 5:
                        terminator = askWhichTerminatorToUse(scanner);
                        logger = askWhichLoggerToUse(scanner);
                        //experiment5.executeExperiment(terminator, logger);
                        break;
                    case 6:
                        terminator = askWhichTerminatorToUse(scanner);
                        logger = askWhichLoggerToUse(scanner);
                        //experiment6.executeExperiment(terminator, logger);
                        break;
                    case 7:
                        terminator = askWhichTerminatorToUse(scanner);
                        logger = askWhichLoggerToUse(scanner);
                        experiment7.executeExperiment(terminator, logger);
                        break;
                    case 8:
                        terminator = askWhichTerminatorToUse(scanner);
                        logger = askWhichLoggerToUse(scanner);
                        experiment8.executeExperiment(terminator, logger);
                        break;
                    case 9:
                        terminator = askWhichTerminatorToUse(scanner);
                        logger = askWhichLoggerToUse(scanner);
                        //experiment9.executeExperiment(terminator, logger);
                        break;
                    case 10:
                        terminator = askWhichTerminatorToUse(scanner);
                        logger = askWhichLoggerToUse(scanner);
                        //experiment10.executeExperiment(terminator, logger);
                        break;
                    default:
                        throw new InvalidSelectionException(ErrorCode.UI_INVALID_EXPERIMENT_SELECTION.getMessage() + " [" + ErrorCode.UI_INVALID_EXPERIMENT_SELECTION.getCode() + "]");
                }
            } catch (InputMismatchException e) {
                ErrorHandler.handleException(e);
                scanner.nextLine();
            } catch (InvalidSelectionException e) {
                ErrorHandler.handleException(e);
                scanner.nextLine();
            }
        } while (selection != 0);
        System.out.println("Application was closed.");
    }

    /**
     * Prints the initial menu for selecting an experiment.
     * <p>
     * This static method displays the list of available experiments and the option to exit
     * the application. It is called at the beginning of each loop in {@link #startConsoleDialogue(Scanner)}.
     * </p>
     */
    private static void printInitialSelectionMenu() {
        System.out.println("Which Experiment do you want to execute?" + System.lineSeparator() + 
                "(0) Close Simulation Application" + System.lineSeparator() + 
                "(1) Execute GOLExperiment1Structure1" + System.lineSeparator() + 
                "(2) Execute GOLExperiment1Structure2" + System.lineSeparator() + 
                "(3) Execute GOLExperiment2Structure1" + System.lineSeparator() + 
                "(4) Execute GOLExperiment2Structure2" + System.lineSeparator() + 
                "(5) Execute GOLExperiment3Structure1" + System.lineSeparator() + 
                "(6) Execute GOLExperiment3Structure2" + System.lineSeparator() + 
                "(7) Execute ParityExperiment1Structure1" + System.lineSeparator() + 
                "(8) Execute ParityExperiment1Structure2" + System.lineSeparator() + 
                "(9) Execute ParityExperiment2Structure1" + System.lineSeparator() + 
                "(10) Execute ParityExperiment2Structure2");
    }

    /**
     * Prompts the user to select a terminator type for the experiment.
     * <p>
     * The user can choose from a fixed step terminator, a stable state terminator, or a combined terminator.
     * Additional configurations may be prompted, such as the maximum number of steps for the fixed step terminator.
     * </p>
     *
     * @param scanner The scanner instance used for user input.
     * @return The selected {@link ITerminator} instance, configured as per user input.
     */
    public static ITerminator askWhichTerminatorToUse(Scanner scanner) {
        int selection;
        int maxSteps;
        do {
            System.out.println("Which terminator do you want to use?" + System.lineSeparator() + 
                    "(1) FixedStepTerminator" + System.lineSeparator() + 
                    "(2) StableStateTerminator" + System.lineSeparator() + 
                    "(3) CombinedTerminator");
            try {
                selection = scanner.nextInt();
                switch (selection) {
                    case 1:
                        System.out.println("What are the max steps of the simulation?");
                        maxSteps = scanner.nextInt();
                        return new FixedStepTerminator(maxSteps);
                    case 2:
                        return new StableStateTerminator();
                    case 3:
                        System.out.println("What are the max steps of the simulation?");
                        maxSteps = scanner.nextInt();
                        return new CombinedTerminator(maxSteps);
                    default:
                        throw new InvalidSelectionException(ErrorCode.UI_INVALID_TERMINATOR_SELECTION.getMessage() + " [" + ErrorCode.UI_INVALID_TERMINATOR_SELECTION.getCode() + "]");
                }
            } catch (InputMismatchException e) {
                ErrorHandler.handleException(e);
                scanner.nextLine();
            } catch (InvalidSelectionException e) {
                ErrorHandler.handleException(e);
                scanner.nextLine();
            }
        } while (true);
    }

    /**
     * Prompts the user to select a logger type for the experiment.
     * <p>
     * The user can choose between a null logger, a console logger, a file logger, or a combined logger.
     * </p>
     *
     * @param scanner The scanner instance used for user input.
     * @return The selected {@link ILogger} instance, configured as per user input.
     */
    public static ILogger askWhichLoggerToUse(Scanner scanner) {
        int selection;
        do {
            System.out.println("Which logger do you want to use?" + System.lineSeparator() + 
                    "(1) NullLogger" + System.lineSeparator() + 
                    "(2) ConsoleLogger" + System.lineSeparator() + 
                    "(3) FileLogger" + System.lineSeparator() + 
                    "(4) CombinedLogger");
            try {
                selection = scanner.nextInt();
                switch (selection) {
                    case 1:
                        return new NullLogger();
                    case 2:
                        return new ConsoleLogger();
                    case 3:
                        return new FileLogger();
                    case 4:
                        return new CombinedLogger();
                    default:
                        throw new InvalidSelectionException(ErrorCode.UI_INVALID_LOGGER_SELECTION.getMessage() + " [" + ErrorCode.UI_INVALID_LOGGER_SELECTION.getCode() + "]");
                }
            } catch (InputMismatchException e) {
                ErrorHandler.handleException(e);
                scanner.nextLine();
            } catch (InvalidSelectionException e) {
                ErrorHandler.handleException(e);
                scanner.nextLine();
            }
        } while (true);
    }
}
