package de.nordakademie.core.errorHandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;

import static org.junit.Assert.assertTrue;


/**
 * Test class for the ErrorHandler class.
 *
 * This class contains test methods to verify the behavior of the
 * {@link ErrorHandler#handleException(Object, Exception)} method
 * for various types of exceptions. It ensures that the expected
 * error messages are correctly sent to the standard error output.
 */
public class ErrorHandlerTest {

    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    /**
     * Sets up the test environment before each test method.
     * This method redirects the standard error output to a
     * ByteArrayOutputStream to capture and verify the output.
     */
    @BeforeEach
    void setUp() {
        System.setErr(new PrintStream(errContent));
    }

    /**
     * Tests the {@link ErrorHandler#handleException(Object, Exception)} method
     * with a generic Exception.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    void testHandleException_withGenericException() {
        Exception exception = new Exception("Generic error");
        Object source = new Object();

        ErrorHandler.handleException(source, exception);

        String expectedMessage = "An error has occurred in " + source.toString() + ": " + exception.getMessage();
        assertTrue(errContent.toString().contains(expectedMessage));
    }

    /**
     * Tests the {@link ErrorHandler#handleException(Object, ExceptionInInitializerError)} method
     * with an ExceptionInInitializerError.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    void testHandleException_withExceptionInInitializerError() {
        ExceptionInInitializerError error = new ExceptionInInitializerError("Initialization error");
        Object source = new Object();

        ErrorHandler.handleException(source, error);

        String expectedMessage = "An error has occurred in " + source.toString() + ": " + error.getMessage();
        assertTrue(errContent.toString().contains(expectedMessage));
    }

    /**
     * Tests the {@link ErrorHandler#handleException(InputMismatchException)} method
     * with an InputMismatchException.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    void testHandleException_withInputMismatchException() {
        InputMismatchException exception = new InputMismatchException("Input mismatch");

        ErrorHandler.handleException(exception);

        String expectedMessage = "An error has occurred: Invalid input. Please enter a number. [UI-404]";
        assertTrue(errContent.toString().contains(expectedMessage));
    }

    /**
     * Tests the {@link ErrorHandler#handleException(InvalidSelectionException)} method
     * with an InvalidSelectionException.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    void testHandleException_withInvalidSelectionException() {
        InvalidSelectionException exception = new InvalidSelectionException("Invalid selection");

        ErrorHandler.handleException(exception);

        String expectedMessage = "An error has occurred: " + exception.getMessage();
        assertTrue(errContent.toString().contains(expectedMessage));
    }
}
