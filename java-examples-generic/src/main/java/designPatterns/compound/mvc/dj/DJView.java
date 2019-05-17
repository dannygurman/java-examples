package designPatterns.compound.mvc.dj;
    
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
The two parts of the view  the view of the model, and
the view with the user interface controls  are displayed
in two windows, but live together in one Java class. */

//DJView is an observer for both real-time beats and BPM changes
public class DJView implements ActionListener,  BeatObserver, BPMObserver {

	//The view holds a reference to both the model and the controller.
	// The controller is only used by the control interface.
    BeatModelInterface model;
	ControllerInterface controller;


//Dispaly components
    JFrame viewFrame;
    JPanel viewPanel;
	BeatBar beatBar;
	JLabel bpmOutputLabel;
    JFrame controlFrame;
    JPanel controlPanel;
    JLabel bpmLabel;
    JTextField bpmTextField;
    JButton setBPMButton;
    JButton increaseBPMButton;
    JButton decreaseBPMButton;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem startMenuItem;
    JMenuItem stopMenuItem;



    public DJView(ControllerInterface controller, BeatModelInterface model) {
       // The constructor gets a reference to the controller and the model,
       // and we store references to those in the instance variables.
		this.controller = controller;
		this.model = model;

      //  We also register as a BeatObserver and a  BPMObserver of the model.
		model.registerObserver((BeatObserver)this);
		model.registerObserver((BPMObserver)this);
    }


    //The updateBPM() method is called when a state change    occurs in the model.
    // When that happens we update the    display with the current BPM.
    // We can get this value    y requesting it directly from the model
    public void updateBPM() {
        if (model != null) {
            int bpm = model.getBPM();//Get value direct from model
            if (bpm == 0) {
                if (bpmOutputLabel != null) {
                    bpmOutputLabel.setText("offline");
                }
            } else {
                if (bpmOutputLabel != null) {
                    bpmOutputLabel.setText("Current BPM: " + model.getBPM());
                }
            }
        }
    }

    //The updateBeat() method is called  when the model starts a new beat.
    // When that happens, we need to pulse our “beat bar.”
    // We do this by setting it to its maximum value (100)  and letting it handle the animation of the pulse.
    public void updateBeat() {
        if (beatBar != null) {
            beatBar.setValue(100);
        }
    }

    public void createView() {
		// Create all Swing components here
        viewPanel = new JPanel(new GridLayout(1, 2));
        viewFrame = new JFrame("View");
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewFrame.setSize(new Dimension(100, 80));
        bpmOutputLabel = new JLabel("offline", SwingConstants.CENTER);
		beatBar = new BeatBar();
		beatBar.setValue(0);
        JPanel bpmPanel = new JPanel(new GridLayout(2, 1));
		bpmPanel.add(beatBar);
        bpmPanel.add(bpmOutputLabel);
        viewPanel.add(bpmPanel);
        viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);
        viewFrame.pack();
        viewFrame.setVisible(true);
	}

   // The createControls method creates all the controls and places them in the   interface.
   // It also takes care of the menu.
   // When the stop  or start items are chosen, the corresponding methods are  called on the controlle
    public void createControls() {
		// Create all Swing components here
        JFrame.setDefaultLookAndFeelDecorated(true);
        controlFrame = new JFrame("Control");
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.setSize(new Dimension(100, 80));

        controlPanel = new JPanel(new GridLayout(1, 2));

        menuBar = new JMenuBar();
        menu = new JMenu("DJ Control");
        startMenuItem = new JMenuItem("Start");
        menu.add(startMenuItem);
        startMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                controller.start();
            }
        });
        stopMenuItem = new JMenuItem("Stop");
        menu.add(stopMenuItem); 
        stopMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                controller.stop();
            }
        });
        JMenuItem exit = new JMenuItem("Quit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        menu.add(exit);
        menuBar.add(menu);
        controlFrame.setJMenuBar(menuBar);

        bpmTextField = new JTextField(2);
        bpmLabel = new JLabel("Enter BPM:", SwingConstants.RIGHT);
        setBPMButton = new JButton("Set");
        setBPMButton.setSize(new Dimension(10,40));
        increaseBPMButton = new JButton(">>");
        decreaseBPMButton = new JButton("<<");
        setBPMButton.addActionListener(this);
        increaseBPMButton.addActionListener(this);
        decreaseBPMButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

		buttonPanel.add(decreaseBPMButton);
		buttonPanel.add(increaseBPMButton);

        JPanel enterPanel = new JPanel(new GridLayout(1, 2));
        enterPanel.add(bpmLabel);
        enterPanel.add(bpmTextField);
        JPanel insideControlPanel = new JPanel(new GridLayout(3, 1));
        insideControlPanel.add(enterPanel);
        insideControlPanel.add(setBPMButton);
        insideControlPanel.add(buttonPanel);
        controlPanel.add(insideControlPanel);
        
        bpmLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        bpmOutputLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        controlFrame.getRootPane().setDefaultButton(setBPMButton);
        controlFrame.getContentPane().add(controlPanel, BorderLayout.CENTER);

        controlFrame.pack();
        controlFrame.setVisible(true);
    }


   // All these methods allow the start and  stop items in the menu to be enabled and    disabled.
   // We’ll see that the controller uses   these to change the interface.
	public void enableStopMenuItem() {
    	stopMenuItem.setEnabled(true);
	}

	public void disableStopMenuItem() {
    	stopMenuItem.setEnabled(false);
	}

	public void enableStartMenuItem() {
    	startMenuItem.setEnabled(true);
	}

	public void disableStartMenuItem() {
    	startMenuItem.setEnabled(false);
	}



  //  This method is called when a button is clicked
    public void actionPerformed(ActionEvent event) {
		if (event.getSource() == setBPMButton) {
          //  If the Set button is  clicked then it is passed  on to the controller along with the new bpm
			int bpm = Integer.parseInt(bpmTextField.getText());
        	controller.setBPM(bpm);

           // Likewise, if the increase or decrease buttons are clicked,
           // this information is   passed on to the controller
		} else if (event.getSource() == increaseBPMButton) {
			controller.increaseBPM();
		} else if (event.getSource() == decreaseBPMButton) {
			controller.decreaseBPM();
		}
    }

}
