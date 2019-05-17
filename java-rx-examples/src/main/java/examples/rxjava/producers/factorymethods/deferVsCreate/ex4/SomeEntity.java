package examples.rxjava.producers.factorymethods.deferVsCreate.ex4;

public class SomeEntity {

    protected  String value;

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "SomeEntity{" +
                "value='" + value + '\'' +
                '}';
    }
}
