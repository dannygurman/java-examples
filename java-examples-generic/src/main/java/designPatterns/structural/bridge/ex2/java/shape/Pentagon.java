package designPatterns.structural.bridge.ex2.java.shape;

import designPatterns.structural.bridge.ex2.java.color.Color;

/**
 * Created by dannyg on 1/7/2018.
 */
public class Pentagon extends Shape{

    public Pentagon(Color c) {
        super(c);
    }

    @Override
    public void applyColor() {
        System.out.print("Pentagon filled with color ");
        color.applyColor();
    }

}