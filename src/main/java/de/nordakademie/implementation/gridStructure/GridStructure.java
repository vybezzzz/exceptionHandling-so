package de.nordakademie.implementation.gridStructure;

/**
 * Abstract class for grid structures in a cellular automaton.
 *
 * @param <T> type of the grid - since we will use both: an array and a hashmap
 */
public abstract class GridStructure<T> {

    int width;
    int height;
    GridStructureType gridStructureType;

    /**
     * Gets the width of the grid.
     *
     * @return width of the grid
     */
    public int getWidth() {

        return width;
    }

    /**
     * Gets the height of the grid.
     *
     * @return height of the grid
     */
    public int getHeight() {

        return height;
    }

    /**
     * Gets the grid.
     *
     * @return grid
     */
    public abstract T getGrid();
}