package de.nordakademie.implementation.gridStructure;

/**
 * Interface for a GridStructureFactory to create a GridStructure object.
 */
public interface IGridStructureFactory {
    /**
     * Decides what type of GridStructure object was received as a parameter and creates a GridStructure object of
     * the same type.
     *
     * @param gridStructure gridStructure object
     * @return GridStructure object of the same type as gridStructure.
     */
    IGridStructure createGridStructure(IGridStructure gridStructure);
}
