package it.unipv.ingsw.d20.vendingmachine.commandline;

import it.unipv.ingsw.d20.vendingmachine.commandline.command.*;
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
	    if (commandln.length() > command.length() + 1)
	    	cmdArgs = commandln.substring(command.length() + 1);
	    
	    if (command.equalsIgnoreCase("addcoin")) {
	    	return cmdExecutor.executeCommand(new AddCoinCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("stop")) {
	    	return cmdExecutor.executeCommand(new StopCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("insertcode")) {
	    	return cmdExecutor.executeCommand(new InsertCodeCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("getcatalog")) {
	    	return cmdExecutor.executeCommand(new GetCatalogCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("dispenserest")) {
	    	return cmdExecutor.executeCommand(new DispenseRestCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("getcredit")) {
	    	return cmdExecutor.executeCommand(new GetCreditCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("insertkey")) {
	    	return cmdExecutor.executeCommand(new InsertKeyCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("ejectkey")) {
	    	return cmdExecutor.executeCommand(new EjectKeyCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("refilltank")) {
	    	return cmdExecutor.executeCommand(new RefillTankCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("withdrawcash")) {
	    	return cmdExecutor.executeCommand(new WithdrawCashCommand(), cmdArgs);
	    } else {
	    	return "Comando non trovato";
	    }
	}
	
}
