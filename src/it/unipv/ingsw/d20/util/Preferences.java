package it.unipv.ingsw.d20.util;

import javax.swing.ImageIcon;

public class Preferences {
	
	private Preferences() {}
	
	public static ImageIcon getGearIcon() {
		String iconPath = Paths.RES_FOLDER + Paths.IMG_FOLDER + Paths.GEAR_ICON;
		return new ImageIcon(iconPath);
	}

}
