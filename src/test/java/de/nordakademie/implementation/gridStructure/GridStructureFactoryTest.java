package de.nordakademie.implementation.gridStructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test class for GridStructureFactory.
 * Contains unit tests to verify the functionality of the GridStructureFactory class.
 */
public class GridStructureFactoryTest {

    GridStructureFactory gridStructureFactory;
    IGridStructure gridStructure;
    GridStructureHashMap gridStructureHashMap;
    GridStructureArray gridStructureArray;

    /**
     * Sets up the environment before every test case.
     * Initializes a mock IGridStructure, as well as a GridStructureFactory object.
     */
    @BeforeEach
    public void setUp() {
        gridStructureFactory = new GridStructureFactory();
    }

    /**
     * Tests whether the GridStructureFactory reads the type of the entered IGridStructure object.
     */
    @Test
    public void testReadingTheTypeOfAGridStructure() {
        gridStructure = mock(IGridStructure.class);
        when(gridStructure.getType()).thenReturn(GridStructureType.ARRAY);
        when(gridStructure.getHeight()).thenReturn(10);
        when(gridStructure.getWidth()).thenReturn(10);

        gridStructureFactory.createGridStructure(gridStructure);

        verify(gridStructure).getType();
    }

    /**
     * Tests whether the GridStructureFactory creates a GridStructureArray object, if the input GridStructure
     * object is of the same type.
     */
    @Test
    public void testCreatingAGridStructureArrayWhenInputIsGridStructureArray() {
        gridStructureArray = mock(GridStructureArray.class);
        when(gridStructureArray.getType()).thenReturn(GridStructureType.ARRAY);
        when(gridStructureArray.getHeight()).thenReturn(10);
        when(gridStructureArray.getWidth()).thenReturn(10);

        IGridStructure newGridStructure = gridStructureFactory.createGridStructure(gridStructureArray);

        assertEquals(newGridStructure.getType(), gridStructureArray.getType());
    }

    /**
     * Tests whether the GridStructureFactory creates a GridStructureHashmap object, if the input GridStructure
     * object is of the same type.
     */
    @Test
    public void testCreatingAGridStructureHashMapWhenInputIsGridStructureHashmap() {
        gridStructureHashMap = mock(GridStructureHashMap.class);                   
        when(gridStructureHashMap.getType()).thenReturn(GridStructureType.HASHMAP);
        when(gridStructureHashMap.getHeight()).thenReturn(10);
        when(gridStructureHashMap.getWidth()).thenReturn(10);

        IGridStructure newGridStructure = gridStructureFactory.createGridStructure(gridStructureHashMap);

        assertEquals(newGridStructure.getType(), gridStructureHashMap.getType());
    }

    @Test
    public void testThrowingIllegalArgumentExceptionWhenGridStructureTypeIsNotSupported() {
        gridStructure = mock(IGridStructure.class);
        when(gridStructure.getType()).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> gridStructureFactory.createGridStructure(gridStructure));
    }
}
