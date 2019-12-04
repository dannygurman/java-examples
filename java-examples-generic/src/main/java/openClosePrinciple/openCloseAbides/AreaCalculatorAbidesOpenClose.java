package openClosePrinciple.openCloseAbides;

public class AreaCalculatorAbidesOpenClose {
    /**
     * A solution that abides by the Open/Closed Principle
     */

    public double getArea(Shape2[] shapes) {
        double area = 0;
        for (Shape2 shape : shapes) {
            area += shape.getArea();
        }
        return area;
    }
}