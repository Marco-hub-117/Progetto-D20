package it.unipv.ingsw.d20.persistence.IOhandler;
import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors

public class OutputFile {
	
	public void createFile() {
		try {
		      File myObj = new File("localFile/filename.txt");
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
	
	 public void write() {
		    try {
		      FileWriter myWriter = new FileWriter("localFile/filename.txt");
		      myWriter.write("Files in Java might be tricky, but it is fun enough!");
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

	 }
}



