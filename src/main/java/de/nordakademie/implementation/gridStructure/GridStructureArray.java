package de.nordakademie.implementation.gridStructure;

import de.nordakademie.core.errorHandler.ErrorCode;
import de.nordakademie.implementation.cell.ICell;
import de.nordakademie.implementation.cell.ICellFactory;

/**
 * Implementation of a grid structure using a 2D array of cells.
 */
public class GridStructureArray extends GridStructure<ICell[][]> implements IGridStructure {

    private ICell[][] grid;

    /**
     * Constructs a GridStructureArray with a specified width and height.
     * Initializes the grid with new Cell instances.
     *
     * @param width width of the grid
     * @param height height of the grid
     */
    public GridStructureArray(int height, int width, ICellFactory cellFactory) throws ExceptionInInitializerError,
            IllegalArgumentException {
        //ExceptionHandling
        if (height < 1 || width < 1) {
            throw new ExceptionInInitializerError(ErrorCode.GRID_STRUCTURE_INVALID_HEIGHT_OR_WIDTH.getMessage() + " [" + ErrorCode.GRID_STRUCTURE_INVALID_HEIGHT_OR_WIDTH.getCode() + "]");
        }

        this.height = height;
        this.width = width;
        grid = new ICell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = cellFactory.createCell(y, x);
            }
        }
        this.gridStructureType = GridStructureType.ARRAY;
    }

    /**
     * Constructs a GridStructureArray with a pre-initialized grid.
     *
     * @param grid pre-initialized grid
     */
    public GridStructureArray(ICell[][] grid) {

        this.height = grid.length;
        this.width = grid[0].length;
        this.grid = grid;
        this.gridStructureType = GridStructureType.ARRAY;
    }

    /**
     * Gets the state of a cell at the specified coordinates.
     *
     * @param x x-coordinate of the cell
     * @param y y-coordinate of the cell
     * @return state of the cell (0 = dead, 1 = alive)
     */
    @Override
    public int getCellState(int y, int x) {
        if (y < 0 || x < 0) {
            throw new IllegalArgumentException(ErrorCode.GRID_STRUCTURE_INVALID_COORDINATE.getMessage() + " [" + ErrorCode.GRID_STRUCTURE_INVALID_COORDINATE.getCode() + "]");
        }

        return grid[y][x].getState();
    }

    /**
     * Sets the state of a cell at a specified coordinates.
     *
     * @param x x-coordinate of the cell
     * @param y y-coordinate of the cell
     * @param state new state of the cell (0 = dead, 1 = alive)
     */
    @Override
    public void setCellState(int y, int x, int state) throws IllegalArgumentException {
        if (y < 0 || x < 0) {
            throw new IllegalArgumentException(ErrorCode.GRID_STRUCTURE_INVALID_COORDINATE.getMessage() + " [" + ErrorCode.GRID_STRUCTURE_INVALID_COORDINATE.getCode() + "]");
        }

        grid[y][x].setState(state);
    }

    /**
     * Sets the number of living neighbor cells for the cell at the specified coordinates.
     *
     * @param x              x-coordinate of the cell
     * @param y              y-coordinate of the cell
     * @param aliveNeighbors number of living neighbor cells
     */
    @Override
    public void setCellAliveNeighbors(int y, int x, int aliveNeighbors) throws IllegalArgumentException {
        if (y < 0 || x < 0) {
            throw new IllegalArgumentException(ErrorCode.GRID_STRUCTURE_INVALID_COORDINATE.getMessage() + " [" + ErrorCode.GRID_STRUCTURE_INVALID_COORDINATE.getCode() + "]");
        }

        grid[y][x].setAliveNeighbors(aliveNeighbors);
    }

    /**
     * Gets the grid.
     *
     * @return the grid
     */
    @Override
    public ICell[][] getGrid() {

        return grid;
    }

    /**
     * Getter method for a cell of the structure at the specified coordinates.
     *
     * @param y y-coordinate of the cell
     * @param x x-coordinate of the cell
     * @return ICell cell at the specified coordinates
     */
    @Override
    public ICell getCell(int y, int x) {
        if (y < 0 || x < 0) {
            throw new IllegalArgumentException(ErrorCode.GRID_STRUCTURE_INVALID_COORDINATE.getMessage() + " [" + ErrorCode.GRID_STRUCTURE_INVALID_COORDINATE.getCode() + "]");
        }

        return grid[y][x];
    }

    /**
     * Getter method for the type of the structure.
     *
     * @return the type of the structure
     */
    public GridStructureType getType() {
        return gridStructureType;
    }
}