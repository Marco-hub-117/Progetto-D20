package it.unipv.ingsw.d20.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

/**
 * Permette di ottenere preferenze grafiche.
 *
 */
public class Preferences {
	
	private Preferences() {}
	
	/**
	 * Ritorna l'icona per il bottone che porta alla modalità operatore.
	 * @return icona operatore
	 */
	public static ImageIcon getGearIcon() {
		String iconPath = Paths.RES_FOLDER + Paths.IMG_FOLDER + Paths.GEAR_ICON;
		return new ImageIcon(iconPath);
	}
	
	/**
	 * Ritorna il font da utilizzare per il diplay della GUI.
	 * @return display font
	 */
	public static Font getDisplayFont() {
		String fontPath = Paths.RES_FOLDER + Paths.FONT_FOLDER + Paths.DISPLAY_FONT;
		Font displayFont = null;
		
		try {
			displayFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(displayFont);
		
		displayFont = displayFont.deriveFont(Font.BOLD, 50);
		
		return displayFont;
	}

}
