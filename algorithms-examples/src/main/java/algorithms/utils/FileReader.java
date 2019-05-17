package algorithms.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by dannyg on 1/7/2018.
 */
public class FileReader {

    Scanner scanner = null;



    public FileReader(String relativeFilename)  {
        try {
            URI fileUri = ClassLoader.getSystemResource(relativeFilename).toURI();
            Path filePath = Paths.get(fileUri);
            if (filePath == null) {
                System.err.println("filePath null");
            }
            scanner = new Scanner(filePath);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public List<Integer>  readInts(boolean print) {
        List<Integer> ints = new ArrayList<>();
        while (scanner.hasNextInt()) {
            ints.add(scanner.nextInt());
        }
        if (print) {
            for (Integer cur_int : ints) {
                System.out.print (cur_int + " ");
            }
        }
        return  ints;

    }


    public static void main(String[] args) {
        FileReader fileUtils = new FileReader("in_ints.txt");
        fileUtils.readInts(true);
    }


//
//
//    List<Integer> integers = new ArrayList<>();
//while (scanner.hasNext()) {
//        if (scanner.hasNextInt()) {
//            integers.add(scanner.nextInt());
//        } else {
//            scanner.next();
//        }

}
