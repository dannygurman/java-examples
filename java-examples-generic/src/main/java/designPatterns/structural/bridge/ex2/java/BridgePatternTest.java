package designPatterns.structural.bridge.ex2.java;

import designPatterns.structural.bridge.ex2.java.color.GreenColor;
import designPatterns.structural.bridge.ex2.java.color.RedColor;
import designPatterns.structural.bridge.ex2.java.shape.Pentagon;
import designPatterns.structural.bridge.ex2.java.shape.Shape;
import designPatterns.structural.bridge.ex2.java.shape.Triangle;

/**
 * Created by dannyg on 1/7/2018.
 */
public class BridgePatternTest {

    public static void main(String[] args) {
        Shape tri = new Triangle(new RedColor());
        tri.applyColor();

        Shape pent = new Pentagon(new GreenColor());
        pent.applyColor();
    }

    //Out:
    //Triangle filled with color red.
    //Pentagon filled with color green.

   // Bridge design pattern can be used when both abstraction and implementation can
    // have different hierarchies independently and we want to hide the implementation from the client application.



}