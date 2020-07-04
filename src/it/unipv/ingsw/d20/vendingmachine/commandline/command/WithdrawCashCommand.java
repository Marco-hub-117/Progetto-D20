package it.unipv.ingsw.d20.vendingmachine.commandline.command;

import it.unipv.ingsw.d20.vendingmachine.commandline.exception.CommandFormatException;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

public class WithdrawCashCommand implements ICommand {

	@Override
	public String execute(VendingMachine vm, String args) {
		double cash;
		try {
			if (args != null)
				throw new CommandFormatException("Argomento non valido per il comando 'withdrawcash'");
			
			cash = vm.withdrawAmount();
		} catch (CommandFormatException e) {
			return e.getMessage();
		}
		
		String returnString;
		if (cash > 0)
			returnString = "Sono stati restituiti €" + String.format("%.2f", cash);
		else
			returnString = "Non possono essere ritirate ulteriori monete";
		return returnString;
	}

}
