package interview.shapesSort.sort;

import interview.shapesSort.shapes.Shape;

import java.util.Comparator;

public class ShapeAreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return ((Double) o1.calculateFunction()).compareTo(o2.calculateFunction());
    }
}
