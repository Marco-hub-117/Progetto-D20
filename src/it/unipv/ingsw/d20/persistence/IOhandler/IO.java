package it.unipv.ingsw.d20.persistence.IOhandler;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class IO {
	
	private String nomeFile;
	private Scanner inputStream;
		
	public IO(String nomeFile) {
		this.nomeFile = "localFile/"+nomeFile;
		this.createFile(this.nomeFile);
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

