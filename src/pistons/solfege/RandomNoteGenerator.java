package pistons.solfege;

import java.util.Random;
import java.util.Vector;

public class RandomNoteGenerator {

	public NoteSet allowedKeys = new NoteSet();
	Random randGen = new Random();

	public RandomNoteGenerator() {
		allowedKeys.putAll(); // Put all notes
	}

	public Note getRandomNote() {
		Note note = new Note();
		note = allowedKeys.get(randGen.nextInt(allowedKeys.size())).clone();
		return note ;
	}
}
