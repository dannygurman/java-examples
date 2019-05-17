package designPatterns.behavioral.mediator.ex1;

import javax.swing.*;
import java.awt.Font;


class LblDisplay extends JLabel {

    IMediator mediator;

    LblDisplay(IMediator mediator) {
        super("Just start...");
        this.mediator = mediator;
        mediator.registerDisplay(this);
        setFont(new Font("Arial", Font.BOLD, 24));
    }

}