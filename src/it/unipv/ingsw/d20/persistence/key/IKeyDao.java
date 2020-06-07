package it.unipv.ingsw.d20.persistence.key;

public interface IKeyDao {

	double setCredit(String serialCode, double credit);

	double getCredit(String serialCode);

}
