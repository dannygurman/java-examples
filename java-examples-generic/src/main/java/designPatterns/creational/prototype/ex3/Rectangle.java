package designPatterns.creational.prototype.ex3;

/**
 *Step 2
 Create concrete classes extending the above class.
*/
public class Rectangle extends Shape {

    public Rectangle(){
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}