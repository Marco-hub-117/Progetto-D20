package it.unipv.ingsw.d20.persistence.IOhandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import it.unipv.ingsw.d20.vendingmachine.model.Constants;

public class IO {
	
	public IO(String idVending) {
		createFile(Constants.FILEPATH+Constants.BVCATPATH+"_"+idVending);
		createFile(Constants.FILEPATH+Constants.TANKSPATH+"_"+idVending);
		createFile(Constants.FILEPATH+Constants.VENDINGPATH+"_"+idVending);
	}
	
	public void createFile(String name) {
		try {
		      File myObj = new File(name);
		      if (myObj.createNewFile()) {
		    	  System.out.println("File created: " + myObj.getName());
		      } else {
		    	  System.out.println("File already exists.");
		      }
		 } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		 }
	}

}

