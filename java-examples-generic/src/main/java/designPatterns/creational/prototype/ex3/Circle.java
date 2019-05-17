package designPatterns.creational.prototype.ex3;

/*Step 2
       Create concrete classes extending the above class.
       */

public class Circle extends Shape {

    public Circle(){
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}