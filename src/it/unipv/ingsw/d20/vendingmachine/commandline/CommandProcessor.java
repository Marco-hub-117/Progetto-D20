package it.unipv.ingsw.d20.vendingmachine.commandline;

import it.unipv.ingsw.d20.vendingmachine.commandline.command.AddCoinCommand;
import it.unipv.ingsw.d20.vendingmachine.commandline.command.GetCatalogCommand;
import it.unipv.ingsw.d20.vendingmachine.commandline.command.InsertCodeCommand;
import it.unipv.ingsw.d20.vendingmachine.commandline.command.StopCommand;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

public class CommandProcessor {
	
	private CommandExecutor cmdExecutor;

	public CommandProcessor(VendingMachine vm) {
		cmdExecutor = new CommandExecutor(vm);
	}
	
	public String processCommand(String commandln) {
		String[] commandSplit = commandln.split(" ");
	    String command = commandSplit[0].trim();
	    String cmdArgs = null;
	    if (commandln.length() > command.length())
	    	cmdArgs = commandln.substring(command.length() + 1);
	    
	    if (command.equalsIgnoreCase("addcoin")) {
	    	return cmdExecutor.executeCommand(new AddCoinCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("stop")) {
	    	return cmdExecutor.executeCommand(new StopCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("insertcode")) {
	    	return cmdExecutor.executeCommand(new InsertCodeCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("getcatalog")) {
	    	return cmdExecutor.executeCommand(new GetCatalogCommand(), cmdArgs);
	    } else {
	    	return "Comando non trovato";
	    }
	}
	
}
