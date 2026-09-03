package interview.exercises.shapeSort.phase1.shape;

public record Square(int value) implements Shape {

    @Override
    public String name() {
        return "Square";
    }

    @Override
    public double perimeter() {
        return 4.0 * value;
    }
}
