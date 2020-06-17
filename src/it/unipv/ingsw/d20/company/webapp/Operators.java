package it.unipv.ingsw.d20.company.webapp;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

//DA ELIMINARE UNA VOLTA COLLEGATO CON DB


public class Operators {

	private static List<Operator> operators = new ArrayList<>();

	public static void add(String name, String username, String password, String type) {
		operators.add(new Operator(name, username, password, type));
	}
	
	public static List<Operator> all() {
		return operators
				.stream()
				.collect(Collectors.toList());
	}

	public static Operator get(String username) {
		return all()
				.stream()
				.filter(it -> it.getUsername() == username)
				.findFirst()
				.get();
	}
	
	public static Operator getMy(String username) {
		
		for (Operator operator: operators) {
			if ((operator.getUsername()).equals(username))
					return operator;
		}
			return null;
	}
	
}
