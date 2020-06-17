package it.unipv.ingsw.d20.company.webapp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//DA ELIMINARE UNA VOLTA COLLEGATO CON DB

public class Vendings {
	
	private static List<Vending> vendings = new ArrayList<>();

	public static void add(int id, String location, String status, String type) {
		vendings.add(new Vending(id, location, status, type));
	}
	
	public static List<Vending> all() {
		return vendings
				.stream()
				.collect(Collectors.toList());
	}

	public static Vending get(int id) {
		return all()
				.stream()
				.filter(it -> it.getId() == id)
				.findFirst()
				.get();
	}

}
