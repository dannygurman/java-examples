package interview.exercises.shapeSort.phase2.shape;

/**
 * Decides which shape a parameter describes.
 * <p>
 * The selection rule is the part of this problem most likely to change, so it is
 * an abstraction of its own rather than a branch inside the sorter.
 */
public interface ShapeFactory {

    Shape create(int parameter);
}
