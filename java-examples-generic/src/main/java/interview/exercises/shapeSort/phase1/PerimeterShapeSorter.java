package interview.exercises.shapeSort.phase1;

import interview.exercises.shapeSort.phase1.json.ShapeSerializer;
import interview.exercises.shapeSort.phase1.shape.ParityShapeFactory;
import interview.exercises.shapeSort.phase1.shape.Shape;
import interview.exercises.shapeSort.phase1.shape.ShapeFactory;
import interview.exercises.shapeSort.phase1.sort.ShapeOrder;

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
