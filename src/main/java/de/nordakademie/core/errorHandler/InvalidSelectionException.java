package de.nordakademie.core.errorHandler;

/**
 * The InvalidSelectionException is a custom runtime exception used to indicate
 * that an invalid selection was made.
 * This exception is thrown when a user's input does not match any of the valid options.
 */
public class InvalidSelectionException extends RuntimeException {

    /**
     * Constructs a new InvalidSelectionException with the specified detail message.
     *
     * @param message A descriptive message that provides additional information about the exception cause.
     */
    public InvalidSelectionException(String message) {
        super(message);
    }
}
