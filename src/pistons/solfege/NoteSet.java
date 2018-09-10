package pistons.solfege;

import java.io.Serializable;
import java.util.Vector;

public class NoteSet extends Vector<Note> implements Serializable {

	private static final long serialVersionUID = -807519686709671665L;

	public void putAll() {
		for(NoteName name:NoteName.values()) {	
			for(NoteAccidental acc:NoteAccidental.values()) {
				for(int octaveNb = 2; octaveNb <= 4; octaveNb++) {
					if((octaveNb == 2 && (name == NoteName.DO ||
							name == NoteName.RE ||
							name == NoteName.MI ||
							(name == NoteName.FA && acc != NoteAccidental.DIESE)))
							||
							(octaveNb == 4 && name == NoteName.SI && acc == NoteAccidental.DIESE)
							||
							(name == NoteName.DO && acc == NoteAccidental.BEMOL) ||
							(name == NoteName.SI && acc == NoteAccidental.DIESE) ||
							(name == NoteName.FA && acc == NoteAccidental.BEMOL) ||
							(name == NoteName.MI && acc == NoteAccidental.DIESE)) {
						continue;
					}
					else {
						add(new Note(acc, name, octaveNb));
					}

				}
			}
		}
	}

	public boolean contains(Note note) {
		return indexOf(note, 0) >= 0;
	}

	public int indexOf(Note note, int index) {
        if (note == null) {
            for (int i = index ; i < elementCount ; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = index ; i < elementCount ; i++)
                if (note.equals((Note)elementData[i]))
                    return i;
        }
        return -1;
	}
	
	public void copyTo(NoteSet noteSet) {
		noteSet.clear();
		for(Note note:this) {
			noteSet.add(note);
		}
	}
}
