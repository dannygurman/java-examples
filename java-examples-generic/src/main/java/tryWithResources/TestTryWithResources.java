package tryWithResources;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class TestTryWithResources {

    private static void printFile_java8() throws IOException {

        //This is the try-with-resources construct.
        // The FileInputStream variable is declared inside the parentheses after the try keyword.
        // Additionally, a FileInputStream is instantiated and assigned to the variable.

        //When the try block finishes the FileInputStream will be closed automatically.
        // This is possible because FileInputStream implements the Java interface java.lang.AutoCloseable.
        // All classes implementing this interface can be used inside the try-with-resources construct.

        //InputStream implements Closeable
       // FileInputStream extends InputStream
       // public interface Closeable extends AutoCloseable {
        try(FileInputStream input = new FileInputStream("file.txt")) {

            int data = input.read();
            while(data != -1){
                System.out.print((char) data);
                data = input.read();
            }
        }
    }


    private static void printFileMultyResources() throws IOException {

        try(  FileInputStream     input         = new FileInputStream("file.txt");
              BufferedInputStream bufferedInput = new BufferedInputStream(input)
        ) {

            int data = bufferedInput.read();
            while(data != -1){
                System.out.print((char) data);
                data = bufferedInput.read();
            }
        }
    }

    private static void printFile_java9() throws IOException {

        //Before Java 9 a resource that is to be automatically closed must be created inside the parentheses of the try block of a try-with-resources construct.
        // From Java 9, this is no longer necessary.
        // If the variable referencing the resource is effectively final, you can simply enter a reference to
        // the variable inside the try block parentheses.:

        FileInputStream input = new FileInputStream("file.txt");
        try(input) {
            int data = input.read();
            while(data != -1){
                System.out.print((char) data);
                data = input.read();
            }
        }
    }
}
