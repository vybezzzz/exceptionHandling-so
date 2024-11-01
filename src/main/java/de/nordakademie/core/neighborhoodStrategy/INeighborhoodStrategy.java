package de.nordakademie.core.neighborhoodStrategy;

import de.nordakademie.implementation.gridStructure.IGridStructure;

public interface INeighborhoodStrategy {

    int computeAliveNeighbors(IGridStructure grid, int y, int x);

}
