package interview.exercises.shapeSort.phase2.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import interview.exercises.shapeSort.phase2.shape.Shape;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Renders shapes as JSON. Nothing in the shape model knows this class exists.
 */
public class ShapeSerializer {

    private static final int ONE_DECIMAL = 1;

    private final ObjectMapper mapper = new ObjectMapper();

    public String toJson(List<Shape> shapes) {
        ArrayNode array = mapper.createArrayNode();

        for (Shape shape : shapes) {
            ObjectNode node = array.addObject();
            node.put("value", shape.value());
            node.put("shape", shape.name());
            node.put("perimeter", round(shape.perimeter()));
        }

        return array.toString();
    }

    /**
     * At most one decimal place, with no trailing zero: {@code 18.85 -> 18.8},
     * {@code 44.0 -> 44}, {@code 40.0 -> 40}.
     */
    private static BigDecimal round(double perimeter) {
        BigDecimal rounded = BigDecimal.valueOf(perimeter)
                .setScale(ONE_DECIMAL, RoundingMode.HALF_UP)
                .stripTrailingZeros();

        // stripTrailingZeros turns 40.0 into 4E+1; pull it back to a plain integer.
        return rounded.scale() < 0 ? rounded.setScale(0) : rounded;
    }
}
