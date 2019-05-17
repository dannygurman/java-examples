package designPatterns.behavioral.observer.ex2;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SwingObserverExample {
	JFrame frame;

	public static void main(String [] args){
		SwingObserverExample  example=new SwingObserverExample();	
		example.go();
	}

	public void go(){
		frame=new JFrame();
		JButton button=new JButton();
		button.addActionListener(new AngelListener());//Make devil and angel objects listeners (observers) of the button
		button.addActionListener(new DevilListener());
		frame.getContentPane().add(BorderLayout.CENTER,button);
	}


	//Listeners inner classes
	//----------- 
	class AngelListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			System.out.println("Angel event!");
		}	
	}

	//--------
	

	//-----------
	class DevilListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			System.out.println("Devil event!");
		}	
	}

	//--------
}
