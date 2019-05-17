package lambdaAndStream.joinAndSplit;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class JoinAndSplitExamples {

    @Test
    //1 - Join Two Arrays
    public void whenJoiningTwoArrays_thenJoined() {
        String[] animals1 = new String[] { "Dog", "Cat" };
        String[] animals2 = new String[] { "Bird", "Cow" };

        Stream <String> stream1 =  Arrays.stream(animals1);
        Stream <String> stream2 =  Arrays.stream(animals2);

        Stream <String> concated = Stream.concat(stream1 , stream2);

        String[] result = concated.toArray(String[]::new);

        assertArrayEquals(result, new String[] { "Dog", "Cat", "Bird", "Cow" });
    }

    //2 - Join Two Collections
    @Test
    public void whenJoiningTwoCollections_thenJoined() {
        Collection <String> collection1 = Arrays.asList("Dog", "Cat");
        Collection <String> collection2 = Arrays.asList("Bird", "Cow", "Moose");

        Stream <String> concated = Stream.concat(collection1.stream() , collection2.stream());

        Collection<String> result = concated.collect(Collectors.toList());

        assertTrue(result.equals(Arrays.asList("Dog", "Cat", "Bird", "Cow", "Moose")));
    }



    // 3 - Join Two Collections with Filter -
    @Test
    public void whenJoiningTwoCollectionsWithFilter_thenJoined() {
        Collection<String> collection1 = Arrays.asList("Dog", "Cat");
        Collection<String> collection2 = Arrays.asList("Bird", "Cow", "Moose");

        Stream <String> concated = Stream.concat(collection1.stream() , collection2.stream());

        Predicate<String> equal3Predicate = e -> e.length() == 3;

        Collection<String> result = concated.filter(equal3Predicate).collect(Collectors.toList());

        assertTrue(result.equals(Arrays.asList("Dog", "Cat", "Cow")));
    }


    // 4 -  Join an Array Into a String
    // Next, let’s join an Array into a String using a Collector:

    @Test
    public void whenConvertArrayToString_thenConverted() {
        String[] animals = new String[] { "Dog", "Cat", "Bird", "Cow" };
        Stream <String> arrayStream = Arrays.stream(animals);

        String delimiter = ",";
        String result = arrayStream.collect(Collectors.joining(delimiter));

        assertEquals(result, "Dog, Cat, Bird, Cow");
    }

    //Join a Collection Into a String
    @Test
    public void whenConvertCollectionToString_thenConverted() {
        Collection<String> animals = Arrays.asList("Dog", "Cat", "Bird", "Cow");
        String delimiter = ",";
        String result = animals.stream().collect(Collectors.joining(delimiter));

        assertEquals(result, "Dog, Cat, Bird, Cow");
    }

    //Join a Map Into a String
    @Test
    public void whenConvertMapToString_thenConverted() {
        Map<Integer, String> animals = new HashMap<>();
        animals.put(1, "Dog");
        animals.put(2, "Cat");
        animals.put(3, "Cow");


        Stream <Map.Entry<Integer, String>> entrySetStream = animals.entrySet().stream();

        String result = entrySetStream.map(entry -> entry.getKey() + " = " + entry.getValue())
                .collect(Collectors.joining(", "));

        assertEquals(result, "1 = Dog, 2 = Cat, 3 = Cow");
    }


    //Join Nested Collections Into a String

    @Test
    public void whenConvertNestedCollectionToString_thenConverted() {
        Collection<List<String>> nested = new ArrayList<>();
        nested.add(Arrays.asList("Dog", "Cat"));
        nested.add(Arrays.asList("Cow", "Pig"));

        String result = nested.stream().map(
                nextList -> nextList.stream()
                        .collect(Collectors.joining("-")))
                .collect(Collectors.joining("; "));

        assertEquals(result, "Dog-Cat; Cow-Pig");
    }

    // Handle Null Values When Joining
    @Test
    public void whenConvertCollectionToStringAndSkipNull_thenConverted() {
        Collection<String> animals = Arrays.asList("Dog", "Cat", null, "Moose");
        String result = animals.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.joining(", "));

        assertEquals(result, "Dog, Cat, Moose");
    }

    // Split a Collection in Two
    @Test
    public void whenSplitCollectionHalf_thenConverted() {
        Collection<String> animals = Arrays.asList(
                "Dog", "Cat", "Cow", "Bird", "Moose", "Pig");
        Collection<String> result1 = new ArrayList<>();
        Collection<String> result2 = new ArrayList<>();
        AtomicInteger count = new AtomicInteger();
        int midpoint = Math.round(animals.size() / 2);

        animals.forEach(next -> {
            int index = count.getAndIncrement();
            if (index < midpoint) {
                result1.add(next);
            } else {
                result2.add(next);
            }
        });

        assertTrue(result1.equals(Arrays.asList("Dog", "Cat", "Cow")));
        assertTrue(result2.equals(Arrays.asList("Bird", "Moose", "Pig")));
    }



    //Split an Array by Word Length
    @Test
    public void whenSplitArrayByWordLength_thenConverted() {
        String[] animals = new String[] { "Dog", "Cat", "Bird", "Cow", "Pig", "Moose"};
        Map<Integer, List<String>> result = Arrays.stream(animals)
                .collect(Collectors.groupingBy(String::length));

        assertTrue(result.get(3).equals(Arrays.asList("Dog", "Cat", "Cow", "Pig")));
        assertTrue(result.get(4).equals(Arrays.asList("Bird")));
        assertTrue(result.get(5).equals(Arrays.asList("Moose")));
    }


    // Split a String Into an Array
    @Test
    public void whenConvertStringToArray_thenConverted() {
        String animals = "Dog, Cat, Bird, Cow";
        String[] result = animals.split(", ");

        assertArrayEquals(result, new String[] { "Dog", "Cat", "Bird", "Cow" });
    }

    //Split String Into a Collection
    //This example is similar to the previous one, there is just an extra step to convert from Array to a Collection:

    @Test
    public void whenConvertStringToCollection_thenConverted() {
        String animals = "Dog, Cat, Bird, Cow";
        Collection<String> result = Arrays.asList(animals.split(", "));

        assertTrue(result.equals(Arrays.asList("Dog", "Cat", "Bird", "Cow")));
    }

    //Split a String Into a Map
    //We will need to split our string twice, once for each entry, and one last time for the key and values:
    @Test
    public void whenConvertStringToMap_thenConverted() {
        String animals = "1 = Dog, 2 = Cat, 3 = Bird";

        Map<Integer, String> result = Arrays.stream(
                animals.split(", ")).map(next -> next.split(" = "))
                .collect(Collectors.toMap(entry -> Integer.parseInt(entry[0]), entry -> entry[1]));

        assertEquals(result.get(1), "Dog");
        assertEquals(result.get(2), "Cat");
        assertEquals(result.get(3), "Bird");
    }

//13 -Split String with Multiple Separators
    // Finally, let’s split a String that has multiple separators using a regular expression,
    // we will also remove any empty results:

    @Test
    public void whenConvertCollectionToStringMultipleSeparators_thenConverted() {
        String animals = "Dog. , Cat, Bird. Cow";

        Collection<String> result = Arrays.stream(animals.split("[,|.]"))
                .map(String::trim)
                .filter(next -> !next.isEmpty())
                .collect(Collectors.toList());

        assertTrue(result.equals(Arrays.asList("Dog", "Cat", "Bird", "Cow")));
    }

}
