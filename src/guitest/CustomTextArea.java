package guitest;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JTextArea;

public class CustomTextArea extends JTextArea {
	
	private Image javaImg;
	
	public CustomTextArea() {
		super(30, 35);
		
		try {
			javaImg = ImageIO.read(new File("images/java.png"));
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
