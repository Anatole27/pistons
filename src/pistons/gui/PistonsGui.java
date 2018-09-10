package pistons.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import pistons.gui.NoteDisplay;
import pistons.instrument.PistonCombination;
import pistons.instrument.TrumpetPistons;
import pistons.listener.PistonListener;
import pistons.solfege.Note;
import pistons.solfege.NoteSet;
import pistons.solfege.RandomNoteGenerator;

public class PistonsGui {



	private static void createAndShowGUI() {
		//Create and set up the window.
		JFrame frame = new JFrame("Pistons");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add the ubiquitous "Hello World" label.
		JLabel label = new JLabel("Yolo");
		frame.getContentPane().add(label);


		//Center frame
		frame.setLocationRelativeTo(null);

		NoteDisplay noteDisplay = new NoteDisplay();
		NoteSet notesAllowed = new NoteSet();

		parametersMenu(frame, notesAllowed);

		startExercise(frame, noteDisplay,  notesAllowed);
	}

	private static void parametersMenu(JFrame frame, NoteSet notesAllowed) {
		ParametersPanel paramsMenu = new ParametersPanel(notesAllowed);
		frame.setContentPane(paramsMenu);
		frame.setVisible(true);
		frame.setSize(578, 293);
		while(!paramsMenu.quitRequested()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void startExercise(JFrame frame, NoteDisplay noteDisplay, NoteSet notesAllowed) {


		frame.setSize(150, 100);
		
		RandomNoteGenerator randNoteGen = new RandomNoteGenerator();
		randNoteGen.allowedKeys = (NoteSet) notesAllowed.clone();
		PistonListener pistonListener = new PistonListener(frame);
		PistonCombination pistonCombination = new PistonCombination();
		TrumpetPistons trumpet = new TrumpetPistons();

		//Display the window.
		frame.setContentPane(noteDisplay);
		frame.setVisible(true);


		for(;;) {
			// Display note
			Note note = randNoteGen.getRandomNote();
			noteDisplay.setNote(note);
			noteDisplay.revalidate();
			noteDisplay.repaint();

			boolean goodAnswer;
			do {
				// Wait for answer
				pistonListener.getPistonCombination(pistonCombination);
				goodAnswer = trumpet.correctCombination(note, pistonCombination);
				System.out.println(goodAnswer);

			}
			while(!goodAnswer);

		}
	}

	public static void main(String[] args) {

		createAndShowGUI();

	}


}
