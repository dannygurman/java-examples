package designPatterns.behavioral.mediator.ex1;

import javax.swing.*;
import java.awt.event.ActionListener;

//A concrete colleague
class BtnSearch extends JButton implements Command {

    IMediator mediator;

    BtnSearch(ActionListener al, IMediator mediator) {
        super("Search");
        addActionListener(al);
        this.mediator = mediator;
        mediator.registerSearch(this);
    }

    public void execute() {
        mediator.search();
    }

}