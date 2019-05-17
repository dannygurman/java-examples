package designPatterns.behavioral.mediator.ex1;

import javax.swing.*;
import java.awt.event.ActionListener;

//A concrete colleague
class BtnBook extends JButton implements Command {

    IMediator mediator;

    BtnBook(ActionListener al, IMediator mediator) {
        super("Book");
        addActionListener(al);
        this.mediator = mediator;
        mediator.registerBook(this);
    }

    public void execute() {
        mediator.book();
    }

}