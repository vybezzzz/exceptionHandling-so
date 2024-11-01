package de.nordakademie.implementation.cell;

/**
 * Factory class for creating cells.
 */
public class CellFactory implements ICellFactory {

    /**
     * Creates a cell with specific x and y coordinates.
     *
     * @param yPosition y position of the cell
     * @param xPosition x position of the cell
     * @return a new cell with the specified coordinates
     */
    public Cell createCell(int yPosition, int xPosition) throws IllegalArgumentException {
        return new Cell(yPosition, xPosition);
    }
}
