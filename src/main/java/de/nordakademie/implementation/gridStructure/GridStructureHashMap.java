package de.nordakademie.implementation.gridStructure;

import de.nordakademie.core.errorHandler.ErrorCode;
import de.nordakademie.implementation.cell.ICell;
import de.nordakademie.implementation.cell.ICellFactory;
import java.util.HashMap;

/**
 * Implementation of a grid structure using a HashMap to store cells.
 */
public class GridStructureHashMap extends GridStructure<HashMap<String, ICell>> implements IGridStructure {

    private HashMap<String, ICell> grid;

    /**
     * Constructs a GridStructureHashMap with a specified width and height.
     * Initializes the grid with new Cell instances.
     *
     * @param width width of the grid
     * @param height height of the grid
     */
    public GridStructureHashMap(int height, int width, ICellFactory cellFactory) throws IllegalArgumentException {
        if (height < 1 || width < 1) {
            throw new ExceptionInInitializerError(ErrorCode.GRID_STRUCTURE_INVALID_HEIGHT_OR_WIDTH.getMessage() + " [" + ErrorCode.GRID_STRUCTURE_INVALID_HEIGHT_OR_WIDTH.getCode() + "]");
        }

        this.height = height;
        this.width = width;
        this.grid = new HashMap<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid.put(createKey(y, x), cellFactory.createCell(y, x));
            }
        }
        this.gridStructureType = GridStructureType.HASHMAP;
    }

    /**
     * Constructs a GridStructureHashMap from an existing cell map.
     *
     * @param grid pre-initialized grid map
     * @param height height of the grid
     * @param width width of the grid
     */
    public GridStructureHashMap(HashMap<String, ICell> grid, int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = grid;
        this.gridStructureType = GridStructureType.HASHMAP;
    }

    /**
     * Creates a unique key for the HashMap based on coordinates.
     *
     * @param y y-coordinate
     * @param x x-coordinate
     * @return String key in format "y,x"
     */
    private String createKey(int y, int x) {
        return y + "," + x;
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

        return grid.get(createKey(y, x)).getState();
    }

    /**
     * Sets the state of a cell at specified coordinates.
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

        grid.get(createKey(y, x)).setState(state);
    }

    /**
     * Sets the number of living neighbor cells for the cell at the specified coordinates.
     *
     * @param x x-coordinate of the cell
     * @param y y-coordinate of the cell
     * @param aliveNeighbors number of living neighbor cells
     */
    @Override
    public void setCellAliveNeighbors(int y, int x, int aliveNeighbors) throws IllegalArgumentException {
        if (y < 0 || x < 0) {
            throw new IllegalArgumentException(ErrorCode.GRID_STRUCTURE_INVALID_COORDINATE.getMessage() + " [" + ErrorCode.GRID_STRUCTURE_INVALID_COORDINATE.getCode() + "]");
        }

        grid.get(createKey(y, x)).setAliveNeighbors(aliveNeighbors);
    }

    /**
     * Gets the grid.
     *
     * @return the grid as Map
     */
    @Override
    public HashMap<String, ICell> getGrid() {

        return grid;
    }

    /**
     * Gets a cell at the specified coordinates.
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

        return grid.get(createKey(y, x));
    }

    /**
     * Gets the type of the grid structure.
     *
     * @return the type of the structure
     */
    @Override
    public GridStructureType getType() {
        return gridStructureType;
    }
}