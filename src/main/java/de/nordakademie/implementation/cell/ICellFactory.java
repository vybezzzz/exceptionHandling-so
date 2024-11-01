package de.nordakademie.implementation.cell;

/**
 * Interface for a cell factory that creates cells.
 */
public interface ICellFactory {

    /**
     *  Creates a cell with a specific x and y position/coordinate.
     *
     *
     * @param yPosition y position of the cell
     * @param xPosition x position of the cell
     * @return a new cell instance
     */
    Cell createCell(int yPosition, int xPosition);
}
