package pistons.solfege;

import java.io.Serializable;

public class Note  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6285980263992425660L;
	public NoteAccidental accidental;
	public Pitch pitch;

	public Note() {
		pitch = new Pitch();
	}

	public Note(NoteAccidental acc, NoteName name, int octaveNb) {
		accidental = acc;
		pitch = new Pitch(name, octaveNb);
	}

	public Note clone() {
		Note note = new Note();
		note.accidental = accidental;
		note.pitch.noteName = pitch.noteName;
		note.pitch.octaveNb = pitch.octaveNb;
		return note;
	}

	public boolean equals(Note note) {
		if(accidental.equals(note.accidental)
				&& pitch.noteName.equals(note.pitch.noteName)
				&& pitch.octaveNb == note.pitch.octaveNb) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean equals(String lilyNote) {
		return lilyNote.equals(toLilypond());
	}

	@Override
	public String toString() {
		return String.format("%s %d %s", pitch.noteName.name(), pitch.octaveNb, accidental.name());

	}

	public String toLilypond() {
		String name = new String();
		String acc = new String();
		String ocNb = new String();
		switch(pitch.noteName) {
		case DO:
			name = "c";
			break;
		case RE:
			name = "d";
			break;
		case MI:
			name = "e";
			break;
		case FA:
			name = "f";
			break;
		case SOL:
			name = "g";
			break;
		case LA:
			name = "a";
			break;
		case SI:
			name = "b";
			break;
		}
		
		switch(pitch.octaveNb) {
		case 2:
			ocNb = "";
			break;
		case 3:
			ocNb = "'";
			break;
		case 4:
			ocNb = "''";
			break;
		}
		
		switch(accidental) {
		case BEMOL:
			acc = "es";
			break;
		case BECARRE:
			acc = "";
			break;
		case DIESE:
			acc = "is";
			break;
		}

		return name + acc + ocNb;
	}
	
}
