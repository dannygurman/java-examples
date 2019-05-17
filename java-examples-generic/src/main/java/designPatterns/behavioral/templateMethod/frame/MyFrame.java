package designPatterns.behavioral.templateMethod.frame;

import java.awt.*;
import javax.swing.*;

public class MyFrame extends JFrame {

	public MyFrame(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(300,300);
		this.setVisible(true);
	}

	//JFrame�s update algorithm calls paint(). By 
	//default, paint() does nothing... it�s a hook. 
	//We�re overriding paint(), and telling the 
	//JFrame to draw a message in the window
	public void paint(Graphics graphics) {
		super.paint(graphics);
		String msg = "I rule!!";
		graphics.drawString(msg, 100, 100);
	}

	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame("Head First Design Patterns");
	}
}
