package interview.ex1.sort;

import interview.ex1.factory.ShapeFactory;
import interview.ex1.shapes.Shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShapeAreaSorter implements ShapeSorter {

    public List<String> sortShapes(List<Integer> parameters) {

        //Creating the shapes following input
        List<Shape> shapes = new ArrayList<Shape>(parameters.size());

        ShapeFactory factory = new ShapeFactory();
        for (Integer inputValue : parameters) {
            shapes.add(factory.create(inputValue));
        }

        //Sorting
        Collections.sort(shapes, new ShapeAreaComparator());

        //returning results string
        List<String> results = new ArrayList<String>(parameters.size());

        for (Shape shape : shapes) {
            results.add(shape.toString());
        }

        return results;
    }
}
