package de.nordakademie.core.errorHandler;

/**
 * Enum ErrorCode defines standardized error codes and messages used throughout the application.
 * Each error code has a unique identifier and a descriptive message that provides context
 * for various exceptions and validation checks encountered in the system.
 * <p>
 * The error codes are organized by specific components such as NEIGHBORHOOD, RULESET, TERMINATOR,
 * CELL, GRID_STRUCTURE, SIMULATION_STATE, UI, and LOGGER. These codes help with consistent error
 * handling, facilitate debugging, and improve maintainability.
 * <p>
 * Example usage:
 * <pre>
 *     throw new IllegalArgumentException(ErrorCode.NEIGHBORHOOD_GRID_NULL.getMessage());
 * </pre>
 *
 * Each error code follows a general format of "COMPONENT-STATUS", where COMPONENT represents
 * the system area and STATUS represents a numerical code (usually aligned with HTTP-like conventions).
 * For example, 400-series codes typically denote client-side input issues, while 500-series
 * codes indicate internal system errors.
 */
public enum ErrorCode {
    /**
     * Error when the GridStructure is null in a neighborhood calculation.
     */
    NEIGHBORHOOD_GRID_NULL("NEIGHBORHOOD-500", "GridStructure cannot be null."),

    /**
     * Error when coordinates are out of bounds in a neighborhood calculation.
     */
    NEIGHBORHOOD_INVALID_COORDINATES("NEIGHBORHOOD-400", "Position coordinates must be within the range of the grid to compute alive neighbors."),

    /**
     * Error for an invalid number of alive neighbors of a cell in a ruleset configuration.
     */
    RULESET_INVALID_ALIVE_NEIGHBORS("RULESET-400", "Invalid number of alive neighbors: "),

    /**
     * Error for an invalid current state of a cell in a ruleset configuration.
     */
    RULESET_INVALID_CURRENT_STATE("RULESET-401", "Invalid current state: "),

    /**
     * Error for a non-positive maximum step count of a simulation state in a terminator setting.
     */
    TERMINATOR_MAX_STEPS("TERMINATOR-400", "Max steps must be positive."),

    /**
     * Error when the current GridStructure is null of a simulation state in a terminator setting.
     */
    TERMINATOR_CURRENT_GRID_NULL("TERMINATOR-401", "Current GridStructure cannot be null."),

    /**
     * Error when the previous GridStructure is null of a simulation state in a terminator setting.
     */
    TERMINATOR_PREVIOUS_GRID_NULL("TERMINATOR-402", "Previous GridStructure cannot be null."),

    /**
     * Error for invalid (negative) coordinates in a Cell configuration.
     */
    CELL_INVALID_COORDINATES("CELL-400", "Position coordinates of a Cell must be non-negative."),

    /**
     * Error for an invalid state in a Cell configuration (must be 0 or 1).
     */
    CELL_INVALID_STATE("CELL-401", "State of a Cell must be 0 (dead) or 1 (alive)."),

    /**
     * Error when it is tried to set the count of alive neighbors for a Cell as negative.
     */
    CELL_INVALID_ALIVE_NEIGHBORS("CELL-402", "Number of alive neighbors of a Cell cannot be negative or beyond 8."),

    /**
     * Error for invalid grid dimensions (height or width must be greater than 0).
     */
    GRID_STRUCTURE_INVALID_HEIGHT_OR_WIDTH("GRID_STRUCTURE-400", "The given height and width of a gridStructureArray must be greater than 0"),

    /**
     * Error for invalid (negative) coordinates in a GridStructure cell retrieval.
     */
    GRID_STRUCTURE_INVALID_COORDINATE("GRID_STRUCTURE-401", "Position coordinates in a GridStructure must be non-negative to get a Cell State."),

    /**
     * Error when the specified GridStructure type is not supported.
     */
    GRID_STRUCTURE_FACTORY_UNSUPPORTED_TYPE("GRID_STRUCTURE-500", "The type of the GridStructure is not supported at the moment."),

    /**
     * Error for an invalid simulation step (must be at least 1).
     */
    SIMULATION_STATE_INVALID_STEP("SIMULATION_STATE-400", "The current Simulation Step has to be at least 1."),

    /**
     * Error when the current GridStructure is null in a simulation state.
     */
    SIMULATION_STATE_CURRENT_GRID_NULL("SIMULATION_STATE-401", "Current GridStructure cannot be null."),

    /**
     * Error when the previous GridStructure is null in a simulation state.
     */
    SIMULATION_STATE_PREVIOUS_GRID_NULL("SIMULATION_STATE-402", "Previous GridStructure cannot be null."),

    /**
     * Error when the current simulation step is negative.
     */
    SIMULATION_STATE_CURRENT_STEP("SIMULATION_STATE-403", "Current step cannot be negative"),

    /**
     * Error for an invalid experiment selection in the UI (must be between 1 and 10).
     */
    UI_INVALID_EXPERIMENT_SELECTION("UI-400", "Invalid experiment selection. Please enter a number between 1 and 10."),

    /**
     * Error for an invalid terminator selection in the UI (must be between 1 and 3).
     */
    UI_INVALID_TERMINATOR_SELECTION("UI-401", "Invalid terminator selection. Please enter a number between 1 and 3."),

    /**
     * Error for an invalid logger selection in the UI (must be between 1 and 4).
     */
    UI_INVALID_LOGGER_SELECTION("UI-402", "Invalid logger selection. Please enter a number between 1 and 4."),

    /**
     * Error when the user input in the UI does not match the expected format.
     */
    UI_MANAGER_INPUT_MISMATCH("UI-404", "Invalid input. Please enter a number."),

    /**
     * Error when the GridStructure is null in a logger operation.
     */
    LOGGER_GRID_NULL("LOGGER-400", "GridStructure cannot be null."),

    /**
     * Error when the current simulation step is null in a logger operation.
     */
    LOGGER_CURRENT_STEP("LOGGER-401", "Current step cannot be null.");

    private final String code;
    private final String message;

    /**
     * Constructs an ErrorCode with a specified code and message.
     *
     * @param code    The unique identifier for the error, following the format COMPONENT-STATUS.
     * @param message A descriptive message providing context for the error.
     */
    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Returns the unique error code associated with this ErrorCode.
     *
     * @return the error code as a String.
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the descriptive error message associated with this ErrorCode.
     *
     * @return the error message as a String.
     */
    public String getMessage() {
        return message;
    }
}
