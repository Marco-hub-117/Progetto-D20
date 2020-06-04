package it.unipv.ingsw.d20.persistence.tankDao;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IO {
	
	private String nome_file;
	private Scanner inputStream;
		
	public IO() {
		nome_file = "filename.txt";
	}

	public void leggi() {
		try {
			inputStream = new Scanner(new File(nome_file));
		}catch(FileNotFoundException e) {
			System.out.println(e);
			System.exit(0);
		}
		String riga;
		String[] array = null;
		
		while(inputStream.hasNext()) {
			riga = inputStream.nextLine();
			array = riga.split(",");
				
							
		}
		System.out.println(array[0]);
			
	}
	}

