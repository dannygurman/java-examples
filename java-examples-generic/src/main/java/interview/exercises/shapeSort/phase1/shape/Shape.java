package interview.exercises.shapeSort.phase1.shape;

/**
 * A shape knows its own geometry and what it is called.
 * <p>
 * It does not know how it was chosen, how it compares to other shapes, or how it
 * will be rendered.
 */
public interface Shape {

    /** The input number this shape was built from. */
    int value();

    String name();

    double perimeter();
}
