package de.nordakademie;

import de.nordakademie.experiments.*;
import de.nordakademie.view.frontend.ConsoleDialogueManager;

import java.util.Scanner;

/**
 * The main class of the application, responsible for starting the console dialog.
 * <p>
 * This class initializes and launches the console-based user interface
 * by creating an instance of {@link ConsoleDialogueManager} and calling the
 * {@code startConsoleDialogue()} method.
 * </p>
 *
 * @author Lasse Tjark Bode
 */
public class App {

    /**
     * The application's entry point.
     * <p>
     * This method is called when the program starts. It initializes a
     * {@link ConsoleDialogueManager} to launch the console dialog interface
     * and facilitate user interaction to decide which experiment should be executed.
     * </p>
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        new ConsoleDialogueManager(new GameOfLifeExperiment1Structure1(),
                new GameOfLifeExperiment1Structure2(),
                new GameOfLifeExperiment2Structure1(),
                new GameOfLifeExperiment2Structure2(),
                new GameOfLifeExperiment3Structure1(),
                new GameOfLifeExperiment3Structure2(),
                new ParityExperiment1Structure1(),
                new ParityExperiment1Structure2(),
                new ParityExperiment2Structure1(),
                new ParityExperiment2Structure2()).startConsoleDialogue(new Scanner(System.in));
    }
}
