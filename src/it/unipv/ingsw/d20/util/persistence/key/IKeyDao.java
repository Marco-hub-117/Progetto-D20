package it.unipv.ingsw.d20.util.persistence.key;

public interface IKeyDao {

	double setCredit(String serialCode, double credit);

	double getCredit(String serialCode);

}
