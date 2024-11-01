package de.nordakademie.implementation.gridStructure;

import de.nordakademie.core.errorHandler.ErrorCode;
import de.nordakademie.implementation.cell.CellFactory;

/**
 * Implements a factory for different types of GridStructure objects.
 *
 * To decide which type of GridStructure objects has to be created, the GridStructureFactory uses the type of one
 * already initialised GridStructure object.
 */
public class GridStructureFactory implements IGridStructureFactory {

    GridStructureType gridStructureType;
    CellFactory cellFactory = new CellFactory();

    /**
     * Decides what type of GridStructure object was received as a parameter and creates a GridStructure object of
     * the same type.
     *
     * @param gridStructure gridStructure object
     * @return GridStructure object of the same type as gridStructure.
     */
    public IGridStructure createGridStructure(IGridStructure gridStructure) throws ExceptionInInitializerError {
        this.gridStructureType = gridStructure.getType();
        if (gridStructureType == GridStructureType.ARRAY) {
            return new GridStructureArray(gridStructure.getHeight(), gridStructure.getWidth(), cellFactory);
        } else if (gridStructureType == GridStructureType.HASHMAP) {
            return new GridStructureHashMap(gridStructure.getHeight(), gridStructure.getWidth(), cellFactory);
        } else {
            throw new IllegalArgumentException(ErrorCode.GRID_STRUCTURE_FACTORY_UNSUPPORTED_TYPE.getMessage() + " (" + gridStructureType + ") [" + ErrorCode.GRID_STRUCTURE_FACTORY_UNSUPPORTED_TYPE.getCode() + "]");
        }
    }

}
