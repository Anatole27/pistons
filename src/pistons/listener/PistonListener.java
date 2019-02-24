package pistons.listener;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import pistons.instrument.PistonCombination;

public class PistonListener implements KeyEventDispatcher {

	boolean combinationValidated = false;
	PistonCombination pistonCombination = new PistonCombination();

	public PistonListener(JFrame frame) {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
	}

	/**
	 * Holds until validation key is hit
	 * 
	 * @param pistonCombination
	 */
	public void getPistonCombination(PistonCombination pistonCombination) {
		while (!combinationValidated) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		combinationValidated = false;
		pistonCombination.setValues(this.pistonCombination);
	}

	@Override
	public boolean dispatchKeyEvent(final KeyEvent e) {
		switch (e.getID()) {
		case KeyEvent.KEY_PRESSED:
			switch (e.getKeyChar()) {
			case 'j':
				pistonCombination.firstPiston = true;
				break;
			case 'k':
				pistonCombination.secondPiston = true;
				break;
			case 'l':
				pistonCombination.thirdPiston = true;
				break;
			case 'a':
				combinationValidated = true;
				break;
			}
			break;

		case KeyEvent.KEY_RELEASED:
			switch (e.getKeyChar()) {
			case 'j':
				pistonCombination.firstPiston = false;
				break;
			case 'k':
				pistonCombination.secondPiston = false;
				break;
			case 'l':
				pistonCombination.thirdPiston = false;
				break;
			case 'a':
				combinationValidated = false;
				break;
			}
			break;
		}
		return false;
	}
}
