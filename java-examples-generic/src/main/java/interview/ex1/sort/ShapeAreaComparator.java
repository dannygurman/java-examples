package interview.ex1.sort;

import interview.ex1.shapes.Shape;

import java.util.Comparator;

public class ShapeAreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return ((Double) o1.calculateFunction()).compareTo(o2.calculateFunction());
    }
}
