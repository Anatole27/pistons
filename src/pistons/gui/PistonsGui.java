package pistons.gui;

import java.awt.BorderLayout;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pistons.instrument.PistonCombination;
import pistons.instrument.TrumpetPistons;
import pistons.listener.PistonListener;
import pistons.solfege.Note;
import pistons.solfege.NoteLearn;
import pistons.solfege.NoteSet;
import pistons.solfege.RandomNoteGenerator;
import pistons.sounds.TrumpetSound;

public class PistonsGui {

	private static void createAndShowGUI() throws InterruptedException, MidiUnavailableException {
		// Create and set up the window.
		JFrame frame = new JFrame("Pistons");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Center frame
		frame.setLocationRelativeTo(null);

		// Create and set up the content pane.
		JTabbedPane tabbedPane = new JTabbedPane();

		NoteDisplay noteDisplay = new NoteDisplay();
		noteDisplay.setNote(new Note());
		NoteSet notesAllowed = new NoteSet();
		ParametersPanel paramsMenu = new ParametersPanel(notesAllowed);

		tabbedPane.addTab("Jeu", noteDisplay);
		tabbedPane.addTab("Réglages", paramsMenu);

		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		// Listener on params
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				switch (tabbedPane.getSelectedIndex()) {
				case 0:
					paramsMenu.refreshAllowedNotes();
					reset = true;
				}
			}
		});

		// Display the window.
		frame.pack();
		frame.setVisible(true);

		startExercise(frame, noteDisplay, notesAllowed);
	}

	private static boolean reset = false;

	private static void startExercise(JFrame frame, NoteDisplay noteDisplay, NoteSet notesAllowed)
			throws InterruptedException, MidiUnavailableException {

		RandomNoteGenerator randNoteGen = new RandomNoteGenerator();
		randNoteGen.allowedKeys = notesAllowed;
		PistonListener pistonListener = new PistonListener(frame);
		PistonCombination pistonCombination = new PistonCombination();
		TrumpetPistons trumpet = new TrumpetPistons();
		TrumpetSound sound = new TrumpetSound();

		Note prevNote = new Note();
		NoteLearn note;
		for (;;) {
			// Display note
			do {
				note = randNoteGen.getRandomNoteWeighted();
			} while (note.equals(prevNote));
			prevNote = note;

			System.out.println(note);
			noteDisplay.setNote(note);
			noteDisplay.revalidate();
			noteDisplay.repaint();
			// Play note
			sound.play(note, 100);
			boolean goodAnswer;

			// Time
			long time = System.currentTimeMillis();
			do {
				reset = false;

				// Wait for answer
				pistonListener.getPistonCombination(pistonCombination);
				goodAnswer = trumpet.correctCombination(note, pistonCombination);

			} while (!goodAnswer || reset);

			// Record time required to answer
			note.addNewDuration(System.currentTimeMillis() - time);

			// Play percu
			sound.percu(42);
			sound.play(note, 100);
			sound.rest(300);
		}

	}

	public static void main(String[] args) throws InterruptedException, MidiUnavailableException {

		createAndShowGUI();

	}

}
