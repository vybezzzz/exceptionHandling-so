package de.nordakademie.core.neighborhoodStrategy;

import de.nordakademie.core.errorHandler.ErrorCode;
import de.nordakademie.implementation.gridStructure.IGridStructure;

/**
 * Implements the Moore neighborhood strategy for cellular automata.
 *
 * The Moore neighborhood comprises the eight surrounding cells (including the diagonal neighbors)
 * around a central cell in a two-dimensional grid. This class provides a method,
 * to count the number of living neighbors for a given cell according to this neighborhood definition.
 *
 * This implementation takes into account edge cases by only considering cells within
 * the grid boundaries. Cells outside the grid are not counted.
 */

public class MooreNeighborhood implements INeighborhoodStrategy{

    /**
     * Calculates the number of alive neighbors based on the MooreNeighborhood strategy.
     *
     * @param grid the grid containing the cells to be checked
     * @param y the y-position of the selected cell
     * @param x the x-position of the selected cell
     * @return number of living neighbors of the cell at the given x, y coordinates
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

        // define mooreDirections as an array for better iteration
        int[][] mooreDirections = {
                {-1, -1}, // up-left
                {-1, 0},  // up
                {-1, 1},  // up-right
                { 0, 1},  // right
                { 1, 1},  // down-right
                { 1, 0},  // down
                { 1, -1}, // down-left
                { 0, -1}  // left
        };

        // Iteration over each direction of the Moore neighborhood
        for (int[] dir : mooreDirections) {

            // initiate to be checked coordinates for each iteration
            int newX = x + dir[1];
            int newY = y + dir[0];

            // Check whether the new coordinates lie within the grid by using the getWidth() and getHeight()-method
            if (newX >= 0 && newX < grid.getWidth() && newY >= 0 && newY < grid.getHeight()) {
                aliveCount += grid.getCellState(newY, newX);
            }
        }

        return aliveCount;
    }
}
