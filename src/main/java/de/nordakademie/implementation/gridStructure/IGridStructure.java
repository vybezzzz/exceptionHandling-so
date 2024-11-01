package de.nordakademie.implementation.gridStructure;

import de.nordakademie.implementation.cell.ICell;

/**
 * Interface for grid structures in the cellular automaton.
 */
public interface IGridStructure {

    /**
     * Getter method for the state of a cell at the specified coordinates.
     *
     * @param x x-coordinate of the cell
     * @param y y-coordinate of the cell
     * @return state of the cell (0 = dead, 1 = alive)
     */
    int getCellState(int y, int x);

    /**
     * Setter method for the state of a cell at the specified coordinates.
     *
     * @param x x-coordinate of the cell
     * @param y y-coordinate of the cell
     * @param state new state of the cell (0 = dead, 1 = alive)
     */
    void setCellState(int y, int x, int state);

    /**
     * Getter method for the width of the structure
     *
     * @return int width of the grid structure
     */
    int getWidth();

    /**
     * Getter method for the height of the structure
     *
     * @return int height of the grid structure
     */
    int getHeight();


    /**
     * Setter method for the number of living neighbor cells for a cell at the specified coordinates.
     *
     * @param y              y-coordinate of the cell
     * @param x              x-coordinate of the cell
     * @param aliveNeighbors number of living neighbor cells
     */
    void setCellAliveNeighbors(int y, int x, int aliveNeighbors);

    /**
     * Getter method for a cell of the structure at the specified coordinates.
     *
     * @param y y-coordinate of the cell
     * @param x x-coordinate of the cell
     * @return ICell cell at the specified coordinates
     */
    ICell getCell(int y, int x);

    /**
     * Getter method for the type of the structure.
     *
     * @return the type of the structure
     */
    GridStructureType getType();
}