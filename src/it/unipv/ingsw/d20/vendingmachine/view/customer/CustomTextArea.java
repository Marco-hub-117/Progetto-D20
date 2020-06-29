package it.unipv.ingsw.d20.vendingmachine.view.customer;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JTextArea;

import it.unipv.ingsw.d20.util.Paths;

/**
 * Area di testo personalizzata con il logo di Java.
 *
 */
@SuppressWarnings("serial")
public class CustomTextArea extends JTextArea {
	
	private Image javaImg;
	/**
	 *Costruttore della classe CustomTextArea
	 *
	 */
	public CustomTextArea() {
		super(30, 35);
		
		try {
			javaImg = ImageIO.read(new File(Paths.RES_FOLDER + Paths.IMG_FOLDER + Paths.JAVA_IMG));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(javaImg, 0, 0, null);
		super.paintComponent(g);

	}

}
