package designPatterns.structural.proxy.virtualproxy;

import java.net.*;
import java.awt.*;
import javax.swing.*;

class ImageProxy implements Icon {
	ImageIcon imageIcon; //The imageIcon is the REAL icon that we 	eventually want to display when it�s loaded
	URL imageURL;
	Thread retrievalThread;
	boolean retrieving = false;

	public ImageProxy(URL url) { 
		//		We pass the URL of the image into the constructor. 
		//This is the imagewe need to display once it�s loaded
		imageURL = url; 
	}


	//We return a default width and heightuntil the imageIcon is loaded;
	//then we  turn it over to the imageIcon.
	public int getIconWidth() {
		if (imageIcon != null) {
			return imageIcon.getIconWidth();
		} else {
			return 800;
		}
	}

	public int getIconHeight() {
		if (imageIcon != null) {
			return imageIcon.getIconHeight();
		} else {
			return 600;
		}
	}

	public void paintIcon(final Component c, Graphics  g, int x,  int y) {
		//This code paints the icon on the screen (by delegating to the imageIcon). 
		//However, if we don�t have a fully created ImageIcon, then we create one.
		if (imageIcon != null) {
			//If we�ve got an icon already, we go ahead and tell it to paint itself.
			imageIcon.paintIcon(c, g, x, y);
		} else {
			//Otherwise we display the  �loading� message.
			g.drawString("Loading CD cover, please wait...", x+300, y+190);

			//If we aren�t already trying to retrieve the image..
			if (!retrieving) {
				retrieving = true;

				//then it�s time to start retrieving it (only one thread calls paint, so we should be okay here in terms of thread safety).
				retrievalThread = new Thread(new Runnable() {
					/*	Here�s where we load the REAL icon image. 
					 * Note that  the image loading with IconImage is synchronous:
					 *  the  ImageIcon constructor doesn�t return until the image is loaded.
					 *   That doesn�t give us much of a chance to do screen updates and have our message displayed,
					 *    so we�re doing this asynchronously.*/
					public void run() {
						try {
							//In our thread we instantiate the 	Icon object. 
							//Its  constructor will not return until the image is loaded.
							imageIcon = new ImageIcon(imageURL, "CD Cover");
							//When we have the image, we tell Swing that we need to be repainted.
							c.repaint();
							//So, the next time the display is painted after the ImageIcon is instantiated, 
							//the paintIcon method will paint the image, not the loading message.
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				retrievalThread.start();
			}
		}
	}
}
