package lambdaAndStream;

/**
 * Created by dannyg on 11/16/2017.
 */
public class City {

    String name;

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
