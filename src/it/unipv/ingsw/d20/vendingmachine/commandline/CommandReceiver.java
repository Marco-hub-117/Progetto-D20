package it.unipv.ingsw.d20.vendingmachine.commandline;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import it.unipv.ingsw.d20.vendingmachine.model.VendingMachine;

public class CommandReceiver {
	
	private CommandProcessor cmdProcessor;

	public CommandReceiver(VendingMachine vm) throws IOException {
		cmdProcessor = new CommandProcessor(vm);
		
		Terminal terminal = new Terminal();
		terminal.getCommandLine().addKeyListener(new KeyListener() {
		    @Override
		    public void keyPressed(KeyEvent e){
		        if(e.getKeyCode() == KeyEvent.VK_ENTER){
		        	e.consume();
		        	terminal.newLine();
		        	terminal.print(cmdProcessor.processCommand(terminal.getCommand().substring(2)));
		        	terminal.newLine();
		        	terminal.setPrompt();
		        }
		    }
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
	}

}
