package de.nordakademie.view.frontend;

import de.nordakademie.core.terminator.CombinedTerminator;
import de.nordakademie.core.terminator.FixedStepTerminator;
import de.nordakademie.core.terminator.ITerminator;
import de.nordakademie.core.terminator.StableStateTerminator;
import de.nordakademie.experiments.*;
import de.nordakademie.view.logger.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit and integration test class for {@link ConsoleDialogueManager}.
 * This class uses mock dependencies to verify the correct behavior of
 * the ConsoleDialogueManager’s methods and the flow of user interactions.
 * It also performs integration testing to validate the complete sequence
 * of experiment selection, terminator choice, and logger functionality.
 * <p>
 * Tests include:
 * - User selection and application output.
 * - Logger and terminator selection with associated prompts.
 * - Verification of experiments based on user input.
 * - Validation of console output
 * </p>
 */
public class ConsoleDialogueManagerTest {

    ConsoleDialogueManager manager;

    GameOfLifeExperiment1Structure1 experiment1;
    GameOfLifeExperiment1Structure2 experiment2;
    GameOfLifeExperiment2Structure1 experiment3;
    GameOfLifeExperiment2Structure2 experiment4;
    GameOfLifeExperiment3Structure1 experiment5;
    GameOfLifeExperiment3Structure2 experiment6;
    ParityExperiment1Structure1 experiment7;
    ParityExperiment1Structure2 experiment8;
    ParityExperiment2Structure1 experiment9;
    ParityExperiment2Structure2 experiment10;

    Scanner scanner;
    ByteArrayOutputStream outputStreamCaptor;
    ByteArrayOutputStream errorOutputStreamCaptor;

    /**
     * Sets up the test environment by initializing mock objects
     * for experiments, terminators, and loggers, and redirects
     * console output to a {@link ByteArrayOutputStream} for assertion.
     */
    @BeforeEach
    void setUp() {
        experiment1 = mock(GameOfLifeExperiment1Structure1.class);
        experiment2 = mock(GameOfLifeExperiment1Structure2.class);
        experiment3 = mock(GameOfLifeExperiment2Structure1.class);
        experiment4 = mock(GameOfLifeExperiment2Structure2.class);
        experiment5 = mock(GameOfLifeExperiment3Structure1.class);
        experiment6 = mock(GameOfLifeExperiment3Structure2.class);
        experiment7 = mock(ParityExperiment1Structure1.class);
        experiment8 = mock(ParityExperiment1Structure2.class);
        experiment9 = mock(ParityExperiment2Structure1.class);
        experiment10 = mock(ParityExperiment2Structure2.class);
        manager = new ConsoleDialogueManager(experiment1, experiment2, experiment3, experiment4, experiment5,
                experiment6, experiment7, experiment8, experiment9, experiment10);

        scanner = mock(Scanner.class);

        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        errorOutputStreamCaptor = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorOutputStreamCaptor));
    }

    /**
     * Tests the initial menu display and verifies that the application
     * closes as expected when the user selects option 0.
     */
    @Test
    public void testPrintingTheInitialSelectionMenuAndClosingApplication() {
        when(scanner.nextInt()).thenReturn(0);

        manager.startConsoleDialogue(scanner);

        Assertions.assertEquals(
                "Which Experiment do you want to execute?" + System.lineSeparator() + 
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
                        "(10) Execute ParityExperiment2Structure2" + System.lineSeparator() + 
                        "Application was closed." + System.lineSeparator(), outputStreamCaptor.toString());
    }

    /**
     * Tests that the terminator selection menu is printed and prompts
     * the user to select a terminator type.
     */
    @Test
    public void testPrintingTheSelectionOfTerminators() {
        when(scanner.nextInt()).thenReturn(2);

        manager.askWhichTerminatorToUse(scanner);

        Assertions.assertEquals(
                "Which terminator do you want to use?" + System.lineSeparator() + 
                        "(1) FixedStepTerminator" + System.lineSeparator() + 
                        "(2) StableStateTerminator" + System.lineSeparator() + 
                        "(3) CombinedTerminator" + System.lineSeparator(), outputStreamCaptor.toString());
    }

    /**
     * Tests that selecting {@link FixedStepTerminator} prompts the user
     * for a maximum step value, then verifies that a
     * {@link FixedStepTerminator} instance is returned.
     */
    @Test
    public void testSelectingFixedStepTerminatorAndPrintingMaxStepQuestion() {
        when(scanner.nextInt()).thenReturn(1).thenReturn(10);

        ITerminator terminatorResult = manager.askWhichTerminatorToUse(scanner);

        Assertions.assertEquals(
                "Which terminator do you want to use?" + System.lineSeparator() + 
                        "(1) FixedStepTerminator" + System.lineSeparator() + 
                        "(2) StableStateTerminator" + System.lineSeparator() + 
                        "(3) CombinedTerminator" + System.lineSeparator() + 
                        "What are the max steps of the simulation?" + System.lineSeparator(), outputStreamCaptor.toString());
        Assertions.assertEquals(FixedStepTerminator.class, terminatorResult.getClass());
    }

    /**
     * Verifies that selecting StableStateTerminator returns an instance
     * of {@link StableStateTerminator}.
     */
    @Test
    public void testSelectingStableStateTerminator() {
        when(scanner.nextInt()).thenReturn(2);

        ITerminator terminatorResult = manager.askWhichTerminatorToUse(scanner);

        Assertions.assertEquals(StableStateTerminator.class, terminatorResult.getClass());
    }

    /**
     * Verifies that selecting CombinedTerminator returns an instance
     * of {@link CombinedTerminator}.
     */
    @Test
    public void testSelectingCombinedTerminator() {
        when(scanner.nextInt()).thenReturn(3);

        ITerminator terminatorResult = manager.askWhichTerminatorToUse(scanner);

        Assertions.assertEquals(CombinedTerminator.class, terminatorResult.getClass());
    }

    /**
     * Tests invalid selection handling for terminator selection by providing
     * an out-of-range value, expecting an "Invalid selection" message as well as asking again which terminator to
     * initiate.
     */
    @Test
    public void testShowingInvalidSelectionMessageWhenTerminatorInputIsGreaterThan3() {
        when(scanner.nextInt()).thenReturn(4).thenReturn(2);

        manager.askWhichTerminatorToUse(scanner);

        Assertions.assertEquals(
                "An error has occurred: Invalid terminator selection. Please enter a number between 1 and 3. [UI-401]" + System.lineSeparator(), errorOutputStreamCaptor.toString());
    }

    /**
     * Tests invalid input handling for terminator selection by providing a
     * string value, expecting an "Invalid input" message as well as asking again which logger to
     * initiate.
     */
    @Test
    public void testShowingInvalidInputMessageWhenTerminatorInputIsAString() {
        when(scanner.nextInt()).thenThrow(InputMismatchException.class).thenReturn(0);

        manager.startConsoleDialogue(scanner);

        Assertions.assertEquals(
                "An error has occurred: Invalid input. Please enter a number. [UI-404]" + System.lineSeparator(), errorOutputStreamCaptor.toString());
    }

    /**
     * Tests that the logger selection menu is printed, prompting the user
     * to select a logger type.
     */
    @Test
    public void testPrintingTheSelectionOfLogger() {
        when(scanner.nextInt()).thenReturn(2);

        manager.askWhichLoggerToUse(scanner);

        Assertions.assertEquals(
                "Which logger do you want to use?" + System.lineSeparator() + 
                        "(1) NullLogger" + System.lineSeparator() + 
                        "(2) ConsoleLogger" + System.lineSeparator() + 
                        "(3) FileLogger" + System.lineSeparator() + 
                        "(4) CombinedLogger" + System.lineSeparator(), outputStreamCaptor.toString());
    }

    /**
     * Verifies that selecting NullLogger returns an instance of {@link NullLogger}.
     */
    @Test
    public void testSelectingNullLogger() {
        when(scanner.nextInt()).thenReturn(1);

        ILogger loggerResult = manager.askWhichLoggerToUse(scanner);

        Assertions.assertEquals(NullLogger.class, loggerResult.getClass());
    }

    /**
     * Verifies that selecting ConsoleLogger returns an instance of {@link ConsoleLogger}.
     */
    @Test
    public void testSelectingConsoleLogger() {
        when(scanner.nextInt()).thenReturn(2);

        ILogger loggerResult = manager.askWhichLoggerToUse(scanner);

        Assertions.assertEquals(ConsoleLogger.class, loggerResult.getClass());
    }

    /**
     * Verifies that selecting FileLogger returns an instance of {@link FileLogger}.
     */
    @Test
    public void testSelectingFileLogger() {
        when(scanner.nextInt()).thenReturn(3);

        ILogger loggerResult = manager.askWhichLoggerToUse(scanner);

        Assertions.assertEquals(FileLogger.class, loggerResult.getClass());
    }

    /**
     * Verifies that selecting CombinedLogger returns an instance of {@link CombinedLogger}.
     */
    @Test
    public void testSelectingCombinedLogger() {
        when(scanner.nextInt()).thenReturn(4);

        ILogger loggerResult = manager.askWhichLoggerToUse(scanner);

        Assertions.assertEquals(CombinedLogger.class, loggerResult.getClass());
    }

    /**
     * Tests invalid selection handling for logger selection by providing an
     * out-of-range value, expecting an "Invalid selection" message as well as asking again which logger to
     * initiate.
     */
    @Test
    public void testShowingInvalidSelectionMessageWhenLoggerInputIsGreaterThan4() {
        when(scanner.nextInt()).thenReturn(5).thenReturn(2);

        manager.askWhichLoggerToUse(scanner);

        Assertions.assertEquals(
                "An error has occurred: Invalid logger selection. Please enter a number between 1 and 4. [UI-402]"
                        + System.lineSeparator(), errorOutputStreamCaptor.toString());
    }

    /**
     * Tests invalid input handling for logger selection by providing a
     * string value, expecting an "Invalid input" message as well as asking again which logger to
     * initiate.
     */
    @Test
    public void testShowingInvalidInputMessageWhenLoggerInputIsAString() {
        when(scanner.nextInt()).thenThrow(InputMismatchException.class).thenReturn(0);

        manager.startConsoleDialogue(scanner);

        Assertions.assertEquals(
                "An error has occurred: Invalid input. Please enter a number. [UI-404]"
                        + System.lineSeparator(), errorOutputStreamCaptor.toString());
    }

    /**
     * Tests the execution of an experiment based on user input, verifying
     * that the correct experiment is invoked with the specified terminator
     * and logger configurations.
     */
    @Test
    public void testExecutingExperimentAccordingToUserInput() {
        when(scanner.nextInt()).thenReturn(7).thenReturn(1).thenReturn(10).thenReturn(2).thenReturn(0);

        manager.startConsoleDialogue(scanner);

        verify(experiment7).executeExperiment(any(ITerminator.class), any(ILogger.class));
    }

    /**
     * Tests invalid selection handling for experiment selection by providing an
     * out-of-range value, expecting an "Invalid selection" message as well as asking again which logger to
     * initiate.
     */
    @Test
    public void testShowingInvalidSelectionMessageWhenExperimentInputIsGreaterThan10() {
        when(scanner.nextInt()).thenReturn(11).thenReturn(0);

        manager.startConsoleDialogue(scanner);

        Assertions.assertEquals(
                "An error has occurred: Invalid experiment selection. Please enter a number between 1 and 10. [UI-400]" + System.lineSeparator(), errorOutputStreamCaptor.toString());
    }

    /**
     * Tests invalid input handling for experiment selection by providing a
     * string value, expecting an "Invalid input" message as well as asking again which logger to
     * initiate.
     */
    @Test
    public void testShowingInvalidInputMessageWhenExperimentInputIsAString() {
        when(scanner.nextInt()).thenThrow(InputMismatchException.class).thenReturn(0);

        manager.startConsoleDialogue(scanner);

        Assertions.assertEquals(
                "An error has occurred: Invalid input. Please enter a number. [UI-404]" + System.lineSeparator(), errorOutputStreamCaptor.toString());
    }

    /**
     * Integration test that verifies the correct console output sequence
     * when selecting and executing an experiment, checking for expected
     * display patterns and the "END OF SIMULATION" marker.
     */
    @Test
    public void testCorrectOutputAfterSelectingAnExperiment() {
        when(scanner.nextInt()).thenReturn(7).thenReturn(1).thenReturn(100).thenReturn(2).thenReturn(0);
        manager = new ConsoleDialogueManager(experiment1, experiment2, experiment3, experiment4, experiment5, experiment6,
                new ParityExperiment1Structure1(), experiment8, experiment9, experiment10);

        manager.startConsoleDialogue(scanner);

        boolean consoleOutput = outputStreamCaptor.toString().contains("### 100" + System.lineSeparator() + 
                "0000000000110000000000000000110000000000" + System.lineSeparator() + 
                "0000001100110011000110001100110011000000" + System.lineSeparator() + 
                "0000001100000011000110001100000011000000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000000000110001100000011000110000000000" + System.lineSeparator() + 
                "0110000000110001100110011000110000000110" + System.lineSeparator() + 
                "0110000000000000000110000000000000000110" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000000000000000000110000000000000000000" + System.lineSeparator() + 
                "1100011000000001100110011000000001100011" + System.lineSeparator() + 
                "1100011000000001100000011000000001100011" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0110000000000000000110000000000000000110" + System.lineSeparator() + 
                "0110011000110000000110000000110001100110" + System.lineSeparator() + 
                "0000011000110000000000000000110001100000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0110001101100011000000001100011011000110" + System.lineSeparator() + 
                "0110001101100011000000001100011011000110" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000011000110000000000000000110001100000" + System.lineSeparator() + 
                "0110011000110000000110000000110001100110" + System.lineSeparator() + 
                "0110000000000000000110000000000000000110" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "1100011000000001100000011000000001100011" + System.lineSeparator() + 
                "1100011000000001100110011000000001100011" + System.lineSeparator() + 
                "0000000000000000000110000000000000000000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0110000000000000000110000000000000000110" + System.lineSeparator() + 
                "0110000000110001100110011000110000000110" + System.lineSeparator() + 
                "0000000000110001100000011000110000000000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000000000000000000000000000000000000000" + System.lineSeparator() + 
                "0000001100000011000110001100000011000000" + System.lineSeparator() + 
                "0000001100110011000110001100110011000000" + System.lineSeparator() + 
                "0000000000110000000000000000110000000000" + System.lineSeparator() + 
                "END OF SIMULATION" + System.lineSeparator() + System.lineSeparator());

        assertEquals(true, consoleOutput);
    }
}
