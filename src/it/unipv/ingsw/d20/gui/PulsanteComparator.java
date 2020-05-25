package it.unipv.ingsw.d20.gui;

import java.util.Comparator;

public class PulsanteComparator implements Comparator<Pulsante>{

	@Override
	public int compare(Pulsante arg0, Pulsante arg1) {
		if(arg0.getPos()>arg1.getPos()) return 1;
		if(arg0.getPos()==arg1.getPos()) return 0;
		return -1;
	}

}

