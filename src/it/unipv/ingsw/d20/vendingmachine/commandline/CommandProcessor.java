package it.unipv.ingsw.d20.vendingmachine.commandline;

import it.unipv.ingsw.d20.vendingmachine.commandline.command.*;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

/**
 * Classe che permette di processare e mandare in esecuzione i programmi
 * digitati dal terminale.
 *
 */
public class CommandProcessor {
	
	private CommandExecutor cmdExecutor;

	public CommandProcessor(VendingMachine vm) {
		cmdExecutor = new CommandExecutor(vm);
	}
	
	/**
	 * Riceve la riga di comando con gli argomenti e manda in esecuzione
	 * il relativo comando.
	 * @param commandln la riga di comando 
	 * @return result il risultato del comando
	 */
	public String processCommand(String commandln) {
		String[] commandSplit = commandln.split(" ");
	    String command = commandSplit[0].trim();
	    String cmdArgs = null;
	    if (commandln.length() > command.length() + 1)
	    	cmdArgs = commandln.substring(command.length() + 1);
	    
	    String result;
	    if (command.equalsIgnoreCase("help")) {
	    	result = cmdExecutor.executeCommand(new HelpCommand(), cmdArgs);
		} else if (command.equalsIgnoreCase("addcoin")) {
	    	result = cmdExecutor.executeCommand(new AddCoinCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("stop")) {
	    	result = cmdExecutor.executeCommand(new StopCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("insertcode")) {
	    	result = cmdExecutor.executeCommand(new InsertCodeCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("getcatalog")) {
	    	result = cmdExecutor.executeCommand(new GetCatalogCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("dispenserest")) {
	    	result = cmdExecutor.executeCommand(new DispenseRestCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("getcredit")) {
	    	result = cmdExecutor.executeCommand(new GetCreditCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("insertkey")) {
	    	result = cmdExecutor.executeCommand(new InsertKeyCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("ejectkey")) {
	    	result = cmdExecutor.executeCommand(new EjectKeyCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("refilltank")) {
	    	result = cmdExecutor.executeCommand(new RefillTankCommand(), cmdArgs);
	    } else if (command.equalsIgnoreCase("withdrawcash")) {
	    	result = cmdExecutor.executeCommand(new WithdrawCashCommand(), cmdArgs);
	    } else {
	    	result = "Comando non trovato";
	    }
	    
	    return result;
	}
	
}
