package de.nordakademie.core.neighborhoodStrategy;

import de.nordakademie.core.errorHandler.ErrorCode;
import de.nordakademie.implementation.gridStructure.GridStructureType;
import de.nordakademie.implementation.gridStructure.IGridStructure;

/**
 * Implements the VonNeumann neighborhood strategy for cellular automata.
 * <p>
 * The Von Neumann neighborhood consists of the four orthogonally adjacent cells
 * (up, right, down, left) surrounding a central cell in a two-dimensional grid.
 * This class provides a method to count the number of alive neighbors for a given
 * cell according to this neighborhood definition.
 * <p>
 * This implementation handles edge cases by only considering cells within
 * the grid boundaries. Cells outside the grid are not counted.
 */

public class VonNeumannNeighborhood implements INeighborhoodStrategy {

    /**
     * computes the amount of alive neighbors based on the VonNeumannNeighborhood strategy
     *
     * @param grid the data-structure housing the to-be-checked cells
     * @param y    the y-position of the selected cell
     * @param x    the x-position of the selected cell
     * @return number of alive neighbors of a cell within the gridStructure with given x, y coordinates
     */

    @Override
    public int computeAliveNeighbors(IGridStructure grid, int y, int x) {
        int aliveCount = 0;

        // ExceptionHandling
        if (grid == null) {
            throw new NullPointerException(ErrorCode.NEIGHBORHOOD_GRID_NULL.getMessage() + " [" + ErrorCode.NEIGHBORHOOD_GRID_NULL.getCode() + "]");
        }
        if (x < 0 || x >= grid.getWidth() || y < 0 || y >= grid.getHeight()) {
            throw new IllegalArgumentException(ErrorCode.NEIGHBORHOOD_INVALID_COORDINATES.getMessage() + " [" + ErrorCode.NEIGHBORHOOD_INVALID_COORDINATES.getCode() + "]");
        }

        // define vonNeumannDirections as an array for better iteration
        int[][] vonNeumannDirections = {{-1, 0},  // up
                {0, 1},   // right
                {1, 0},   // down
                {0, -1}}; // left

        // iterating trough each direction of the valid vonNeumannDirections
        for (int[] dir : vonNeumannDirections) {

            // initiate to be checked coordinates for each iteration
            int newX = x + dir[1];
            int newY = y + dir[0];

            // check, if the given coordinates are valid by using the getWidth() and getHeight()-method
            if (newX >= 0 && newX < grid.getWidth() && newY >= 0 && newY < grid.getHeight()) {
                aliveCount += grid.getCellState(newY, newX);
            }

        }

        return aliveCount;
    }
}
