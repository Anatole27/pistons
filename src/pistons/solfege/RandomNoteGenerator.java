package pistons.solfege;

import java.util.Random;

public class RandomNoteGenerator {

	public NoteSet allowedKeys = new NoteSet();
	Random randGen = new Random();

	public RandomNoteGenerator() {
		allowedKeys.putAll(); // Put all notes
	}

	public NoteLearn getRandomNote() {
		NoteLearn note;
		note = allowedKeys.get(randGen.nextInt(allowedKeys.size())).clone();
		return note;
	}

	public NoteLearn getRandomNoteWeighted() {

		// Return in priority any note that has never been sent
		for (int iNote = 0; iNote < allowedKeys.size(); iNote++) {
			if (allowedKeys.get(iNote).getMeanDur() == 0) {
				return allowedKeys.get(iNote);
			}
		}

		// Send in priority notes with the highest response duration
		long[] cumulTime = new long[allowedKeys.size()];
		cumulTime[0] = (long) allowedKeys.get(0).getMeanDur();
		for (int iNote = 1; iNote < allowedKeys.size(); iNote++) {
			cumulTime[iNote] = cumulTime[iNote - 1] + (long) allowedKeys.get(iNote).getMeanDur();
		}

		double rand = randGen.nextDouble() * cumulTime[allowedKeys.size() - 1];
		for (int iNote = 0; iNote < allowedKeys.size(); iNote++) {
			if (rand <= cumulTime[iNote]) {
				return allowedKeys.get(iNote);
			}
		}
		return allowedKeys.get(0);
	}
}
