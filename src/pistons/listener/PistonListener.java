package pistons.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import pistons.instrument.PistonCombination;

public class PistonListener implements KeyListener {

	boolean combinationValidated = false;
	PistonCombination pistonCombination = new PistonCombination();
	
	public PistonListener(JFrame frame) {
		frame.addKeyListener(this);
	}
	
	/**
	 * Holds until validation key is hit
	 * @param pistonCombination
	 */
	public void getPistonCombination(PistonCombination pistonCombination) {
		while(!combinationValidated) {
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
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyChar()) {
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

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyChar()) {
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

		
	}
	
}
