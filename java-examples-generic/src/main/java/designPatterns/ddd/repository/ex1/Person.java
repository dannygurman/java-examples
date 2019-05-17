package designPatterns.ddd.repository.ex1;

/**
 * Created by DannyG on 02/05/2016.
 */

//A concrete entity could look like this:
public class Person extends Identity {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}