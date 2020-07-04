package it.unipv.ingsw.d20.vendingmachine.commandline.command;

import it.unipv.ingsw.d20.vendingmachine.commandline.exception.CommandFormatException;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;
import it.unipv.ingsw.d20.vendingmachine.model.paymentsystem.exceptions.InvalidCoinException;

public class AddCoinCommand implements ICommand {

	@Override
	public String execute(VendingMachine vm, String args) {
		try {
			double coinValue = Double.parseDouble(args);
			
			if (coinValue != 2 && coinValue != 1 && coinValue != 0.5 && coinValue != 0.2 && coinValue != 0.1 && coinValue != 0.05)
				throw new CommandFormatException("Argomento non valido per il comando 'addcoin'");
			else
				vm.insertCoin(coinValue);
		} catch (NumberFormatException | NullPointerException | InvalidCoinException | CommandFormatException e) {
			return e.getMessage();
		}
		
		return "Moneta inserita. Credito attuale: €" + String.format("%.2f", vm.getCredit());
	}

}
