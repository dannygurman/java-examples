package interview.exercises.shapeSort.phase1.sort;

import interview.exercises.shapeSort.phase1.shape.Shape;

import java.util.Comparator;

/**
 * How shapes are ordered. Isolated so that ordering by something other than
 * perimeter is a one-line change with no effect on the shapes themselves.
 */
public final class ShapeOrder {

    public static final Comparator<Shape> BY_PERIMETER =
            Comparator.comparingDouble(Shape::perimeter);

    private ShapeOrder() {
    }
}
