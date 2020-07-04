package it.unipv.ingsw.d20.vendingmachine.commandline;

import it.unipv.ingsw.d20.vendingmachine.commandline.command.ICommand;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

public class CommandExecutor {
	
	private VendingMachine vendingMachine;

	public CommandExecutor(VendingMachine vm) {
		vendingMachine = vm;
	}
	
	public String executeCommand(ICommand command, String arg) {
        return command.execute(vendingMachine, arg);
    }

}
