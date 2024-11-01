package de.nordakademie.implementation.cell;

import de.nordakademie.core.errorHandler.ErrorCode;

/**
 * Each cell has a position (x, y), a state (0 = dead, 1 = alive) and a number of living neighboring cells.
 *
 * Implements the ICell interface for the getter and setter method.
 */
public class Cell implements ICell {

    private int xPosition;
    private int yPosition;
    private int state;
    private int aliveNeighbors;

    /**
     * Constructs a cell with specific x and y coordinates
     *
     * @param yPosition y position of the cell
     * @param xPosition x position of the cell
     */
    public Cell(int yPosition, int xPosition) {
        if (xPosition < 0 || yPosition < 0) {
            throw new IllegalArgumentException(ErrorCode.CELL_INVALID_COORDINATES.getMessage() + " [" + ErrorCode.CELL_INVALID_COORDINATES.getCode() + "]");
        }
        this.yPosition = yPosition;
        this.xPosition = xPosition;
    }


    /**
     * Returns the x position of the cell.
     *
     * @return x position of the cell
     */
    public int getXPosition() {

        return xPosition;
    }

    /**
     * Returns the y position of the cell.
     *
     * @return y position of the cell
     */
    public int getYPosition() {

        return yPosition;
    }

    /**
     * Returns the current state of the cell.
     *
     * @return current state of the cell
     */
    @Override
    public int getState() {

        return state;
    }

    /**
     * Sets a state for the cell.
     *
     * @param state new state of the cell
     */
    @Override
    public void setState(int state) {
        if (state != 0 && state != 1) {
            throw new IllegalArgumentException(ErrorCode.CELL_INVALID_STATE.getMessage() + " [" + ErrorCode.CELL_INVALID_STATE.getCode() + "]");
        }
        this.state = state;
    }

    /**
     * Returns the current number of living neighbor cells.
     *
     * @return current number of living neighbor cells
     */
    @Override
    public int getAliveNeighbors() {

        return aliveNeighbors;
    }

    /**
     * Sets the number of living neighbor cells.
     *
     * @param aliveNeighbors new number of alive neighbor cells
     */
    @Override
    public void setAliveNeighbors(int aliveNeighbors) {
        if (aliveNeighbors < 0 || aliveNeighbors > 8) {
            throw new IllegalArgumentException(ErrorCode.CELL_INVALID_ALIVE_NEIGHBORS.getMessage() + " [" + ErrorCode.CELL_INVALID_ALIVE_NEIGHBORS.getCode() + "]");
        }
        this.aliveNeighbors = aliveNeighbors;
    }
}
