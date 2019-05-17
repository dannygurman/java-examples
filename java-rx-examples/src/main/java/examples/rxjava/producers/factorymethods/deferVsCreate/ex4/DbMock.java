package examples.rxjava.producers.factorymethods.deferVsCreate.ex4;

import java.io.IOException;

public class DbMock {

    public void writeToDisk(SomeEntity entity) throws IOException {
        System.out.println("Writing "+ entity.getValue() + "to disk");

    }
}
