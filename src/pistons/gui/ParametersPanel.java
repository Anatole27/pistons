package pistons.gui;

import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import pistons.solfege.NoteLearn;
import pistons.solfege.NoteSet;

public class ParametersPanel extends JPanel {

	private static final long serialVersionUID = -3329621266563586633L;

	NoteSet selectedNotes;
	NoteSet notes = new NoteSet();
	Vector<JCheckBox> cbVector = new Vector<JCheckBox>();

	public ParametersPanel(NoteSet inputNoteSet) {
		selectedNotes = inputNoteSet;
		loadNotes();
		notes.putAll();

		for (NoteLearn note : notes) {
			JCheckBox cb = new JCheckBox(note.toString(), selectedNotes.contains(note));
			add(cb);
			cbVector.add(cb);
		}
		this.setLayout(new GridLayout(0, 4));
	}

	public void refreshAllowedNotes() {

		selectedNotes.clear();
		int i = 0;
		Iterator<JCheckBox> checkboxIt = cbVector.iterator();
		while (checkboxIt.hasNext()) {
			if (checkboxIt.next().isSelected()) {
				selectedNotes.add(notes.get(i).clone());
				System.out.println(notes.get(i));
			}
			i++;
		}
		System.out.println();

		// Save
		saveNotes();
	}

	private void loadNotes() {
		FileInputStream stream;
		try {
			stream = new FileInputStream("save.pistons");
			ObjectInputStream objStream = new ObjectInputStream(stream);
			NoteSet readObj = (NoteSet) objStream.readObject();
			readObj.copyTo(selectedNotes);
			objStream.close();
			stream.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			selectedNotes.putAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void saveNotes() {
		FileOutputStream stream;
		try {
			stream = new FileOutputStream("save.pistons");
			ObjectOutputStream objStream = new ObjectOutputStream(stream);
			objStream.writeObject(selectedNotes);
			objStream.close();
			stream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
