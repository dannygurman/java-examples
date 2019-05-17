package designPatterns.behavioral.mediator.ex1;

/**
 * Created by DannyG on 20/01/2015.
 */

import javax.swing.*;
import java.awt.event.ActionListener;

//A concrete colleague
class BtnView extends JButton implements Command {

    IMediator mediator ;

    BtnView(ActionListener al, IMediator mediator) {
        super("View");
        addActionListener(al);
        this.mediator = mediator;
        mediator.registerView(this);
    }

    public void execute() {
        mediator.view();
    }

}