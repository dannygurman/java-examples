package interview.exercises.shapeSort.phase2;

import java.util.List;

public interface ShapeSorter {

    /**
     * Builds a shape from every parameter, then returns them ordered by perimeter
     * as a JSON array.
     */
    String sortShapes(List<Integer> parameters);
}
