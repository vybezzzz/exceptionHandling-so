package de.nordakademie.implementation.cell;

/**
 * Interface for a cell in the cellular automaton.
 */
public interface ICell {

    /**
     * Getter method for the state of the cell
     *
     * @return state of the cell (0 for dead, 1 for alive)
     */
    int getState();

    /**
     * Setter method for the state of the cell
     *
     * @param state state of the cell (0 for dead, 1 for alive)
     */
    void setState(int state);

    /**
     * Getter method for the number of living neighbors
     *
     * @return number of living neighbor cells
     */
    int getAliveNeighbors();

    /**
     * Setter method for the number of living neighbor cells
     *
     * @param aliveNeighbors number of alive neighbor cells
     */
    void setAliveNeighbors(int aliveNeighbors);

    /**
     * Getter method for the x position of the cell
     *
     * @return x position of the cell
     */
    int getXPosition();

    /**
     * Getter method for the y position of the cell
     *
     * @return y position of the cell
     */
    int getYPosition();
}
