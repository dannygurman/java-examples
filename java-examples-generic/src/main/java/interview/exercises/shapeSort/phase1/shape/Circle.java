package interview.exercises.shapeSort.phase1.shape;

public record Circle(int value) implements Shape {

    @Override
    public String name() {
        return "Circle";
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * value;
    }
}
