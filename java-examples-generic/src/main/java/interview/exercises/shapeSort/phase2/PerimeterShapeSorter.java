package interview.exercises.shapeSort.phase2;

import interview.exercises.shapeSort.phase2.json.ShapeSerializer;
import interview.exercises.shapeSort.phase2.shape.ParityShapeFactory;
import interview.exercises.shapeSort.phase2.shape.Shape;
import interview.exercises.shapeSort.phase2.shape.ShapeFactory;
import interview.exercises.shapeSort.phase2.sort.ShapeOrder;

import java.util.List;

public class PerimeterShapeSorter implements ShapeSorter {

    private final ShapeFactory shapeFactory = new ParityShapeFactory();
    private final ShapeSerializer serializer = new ShapeSerializer();

    @Override
    public String sortShapes(List<Integer> parameters) {
        List<Shape> shapes = parameters.stream()
                .map(shapeFactory::create)
                .sorted(ShapeOrder.BY_PERIMETER)
                .toList();

        return serializer.toJson(shapes);
    }
}
