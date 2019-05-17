package lambdaAndStream.stream.iterate;

//Java 8 Streams are not collections and elements cannot be accessed using their indices,
// but there are still a few tricks to make this possible:
//using IntStream,
// StreamUtils,
// EntryStream, and
// Vavr‘s Stream.


import com.codepoetics.protonpack.Indexed;
import com.codepoetics.protonpack.StreamUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class StreamIndexIterateExamples {

    String[] namesArray  = { "Afrim", "Bashkim", "Besim", "Lulzim", "Durim", "Shpetim"};


    @Test
    public void testIterateUsingPlanJava () {

        //expected - names in even indexes
        List<String> expectedResult = Arrays.asList("Afrim", "Besim", "Durim");
        List<String> actualResult   = StreamIndexIterateExamples.getEvenIndexedStrings_plain_java(namesArray);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testIterateUsingCodePoeticStreamUtils() {

        List <String> namesList = Arrays.asList(namesArray);
        //expected - names in even indexes
        List<Indexed<String>> expectedResult = Arrays.asList(
                Indexed.index(0, "Afrim"),
                Indexed.index(2, "Besim"),
                Indexed.index(4, "Durim"));

        List<Indexed<String>> actualResult= StreamIndexIterateExamples.getEvenIndexedStrings_streamUtils(namesList);

        assertEquals(expectedResult, actualResult);
    }



    public static List<String> getEvenIndexedStrings_plain_java(String[] names) {
        //Using intStream
        //We can navigate through a Stream using an Integer range, and also benefit from the fact that the
        // original elements are in an array or a collection accessible by indices.

        IntPredicate evenNumFilter = i -> i % 2 == 0;
        List<String> evenIndexedNames = IntStream
                .range(0, names.length)
                .filter(evenNumFilter)
                .mapToObj(i -> names[i])
                .collect(Collectors.toList());

        return evenIndexedNames;
    }


    //Using codepoetics - stream utils
    public static List<Indexed<String>> getEvenIndexedStrings_streamUtils( List<String> names) {
        //Using codepoetics - stream utils
        Stream <String> originListStream = names.stream();

        Stream<Indexed<String>>  indexedStream  = StreamUtils.zipWithIndex(originListStream);

        Predicate<Indexed<String>>  evenNumFilter = i -> i.getIndex() % 2 == 0;

        List<Indexed<String>> list = indexedStream.filter(evenNumFilter).collect(Collectors.toList());

        return list;
    }
}