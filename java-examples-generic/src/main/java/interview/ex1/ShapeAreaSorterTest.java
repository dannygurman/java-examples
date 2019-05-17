package interview.ex1;

import interview.ex1.sort.ShapeAreaSorter;
import interview.ex1.sort.ShapeSorter;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ShapeAreaSorterTest {


    private final List<Integer> input = Arrays.asList(78, 2, 1, 4, 5, 76);
    private Map<Integer, String[]> expectedTerms;
    private ShapeSorter shapeSorter;

    @Before
    public void setUp() throws Exception {
        expectedTerms = new HashMap<Integer, String[]>();

        expectedTerms.put(0, new String[]{"1", "Circle", "3.1"});
        expectedTerms.put(1, new String[]{"2", "Square", "4"});
        expectedTerms.put(2, new String[]{"4", "Square", "16"});
        expectedTerms.put(3, new String[]{"5", "Circle", "78.5"});
        expectedTerms.put(4, new String[]{"76", "Square", "5776"});
        expectedTerms.put(5, new String[]{"78", "Square", "6084"});

        shapeSorter = new ShapeAreaSorter();
    }

    @Test
    public void shapeAreaSorterShouldReturnInputInRightOrder() throws Exception {
        List<String> actualResults = shapeSorter.sortShapes(input);

        validateResults(actualResults);
    }

    private void validateResults(List<String> results) {
        //validate collections are same size
        assertEquals(input.size(), results.size());

        //validate order and type and formatting
        for (int i = 0; i < results.size(); i++) {
            String[] actualTerm = results.get(i).split(",");
            assertArrayEquals(expectedTerms.get(i), actualTerm);
        }
    }
}