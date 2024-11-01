package de.nordakademie.core.errorHandler;

import java.util.InputMismatchException;

/**
 * The ErrorHandler class provides centralized methods to handle various types of exceptions
 * encountered in the application. The class includes overloaded methods to handle generic exceptions,
 * initialization errors, input mismatches, and invalid selections. Each method logs an error message
 * to the standard error stream.
 * <p>
 * This class is designed for consistent error handling and logging, making it easier to trace
 * exceptions and handle them systematically.
 */
public class ErrorHandler {

    /**
     * Handles a general exception by logging an error message with information
     * about the object where the exception occurred.
     *
     * @param o The object where the exception occurred.
     * @param e The exception to be handled.
     */
    public static void handleException(Object o, Exception e) {
        System.err.println("An error has occurred in " + o.toString() + ": " + e.getMessage());
    }

    /**
     * Handles an ExceptionInInitializerError by logging an error message with information
     * about the object where the error occurred. This is useful for initialization errors.
     *
     * @param o The object where the initialization error occurred.
     * @param e The ExceptionInInitializerError to be handled.
     */
    public static void handleException(Object o, ExceptionInInitializerError e) {
        System.err.println("An error has occurred in " + o.toString() + ": " + e.getMessage());
    }

    /**
     * Handles an InputMismatchException, specifically for user input mismatches.
     * Logs a predefined error message and error code from ErrorCode.
     *
     * @param e The InputMismatchException to be handled.
     */
    public static void handleException(InputMismatchException e) {
        System.err.println("An error has occurred: " + ErrorCode.UI_MANAGER_INPUT_MISMATCH.getMessage() + " [" + ErrorCode.UI_MANAGER_INPUT_MISMATCH.getCode() + "]");
    }

    /**
     * Handles an {@link InvalidSelectionException} by logging the exception message.
     * This method is useful for capturing errors related to invalid user selections in the UI.
     *
     * @param e The InvalidSelectionException to be handled.
     */
    public static void handleException(InvalidSelectionException e) {
        System.err.println("An error has occurred: " + e.getMessage());
    }
}
