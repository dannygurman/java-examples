package interview.exercises.shapeSort.phase2.shape;

/**
 * Odd parameters are circle radii, even parameters are square sides.
 */
public class ParityShapeFactory implements ShapeFactory {

    @Override
    public Shape create(int parameter) {
        return isEven(parameter) ? new Square(parameter) : new Circle(parameter);
    }

    private static boolean isEven(int parameter) {
        return parameter % 2 == 0;
    }
}
