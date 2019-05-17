package designPatterns.creational.builder.ex2;

/**
 * Created by DannyG on 08/01/2015.
 *
 * /*
 //We will use same example of creating Cake using Builder design pattern in Java.
 // we have static nested builder class inside Cake which is used to create object.

 Guidelines for Builder design pattern in Java
 1) Make a static nested class called Builder inside the class whose object will be build by Builder.
 In this example its Cake.

 2) Builder class will have exactly same set of fields as original class.
 3) Builder class will expose method for adding ingredients e.g. sugar() in this example.
 each method will return same Builder object.
 Builder will be enriched with each method call.

 4) Builder.build() method will copy all builder field values into actual class and return object of Item class.

 5) Item class (class for which we are creating Builder) should have private constructor to create its object from build() method and prevent outsider to access its constructor.
 */
public class  Cake {

    private final double sugar;   //cup
    private final double butter;  //cup
    private final int eggs;
    private final int vanila;     //spoon
    private final double flour;   //cup
    private final double bakingpowder; //spoon
    private final double milk;  //cup
    private final int cherry;


    ///Inner static class
    public static class Builder {

        private double sugar;   //cup
        private double butter;  //cup
        private int eggs;
        private int vanila;     //spoon
        private double flour;   //cup
        private double bakingpowder; //spoon
        private double milk;  //cup
        private int cherry;

        //builder methods for setting property
        public Builder sugar(double cup){
            this.sugar = cup; return this;
        }

        public Builder butter(double cup){
            this.butter = cup; return this;
        }

        public Builder eggs(int number){
            this.eggs = number; return this;
        }

        public Builder vanila(int spoon){
            this.vanila = spoon; return this; }

        public Builder flour(double cup){
            this.flour = cup; return this;
        }

        public Builder bakingpowder(double spoon){
            this.sugar = spoon; return this;
        }

        public Builder milk(double cup){
            this.milk = cup; return this;
        }

        public Builder cherry(int number){
            this.cherry = number; return this;
        }


        //return fully build object
        public Cake build() {
            return new Cake(this);
        }
    }

    //private constructor to enforce object creation through builder
    private Cake(Builder builder) {
        this.sugar = builder.sugar;
        this.butter = builder.butter;
        this.eggs = builder.eggs;
        this.vanila = builder.vanila;
        this.flour = builder.flour;
        this.bakingpowder = builder.bakingpowder;
        this.milk = builder.milk;
        this.cherry = builder.cherry;
    }

    @Override
    public String toString() {
        return "Cake{" + "sugar=" + sugar + ", butter=" + butter + ", eggs=" + eggs + ", vanila=" + vanila + ", flour=" + flour + ", bakingpowder=" + bakingpowder + ", milk=" + milk + ", cherry=" + cherry + '}';

    }

}


