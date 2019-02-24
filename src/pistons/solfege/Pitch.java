package pistons.solfege;

import java.io.Serializable;

public class Pitch implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8707596198205376035L;
	public NoteName noteName = NoteName.DO;
	public int octaveNb;

	public Pitch() {
	}

	public Pitch(NoteName name, int octaveNb) {
		this.noteName = name;
		this.octaveNb = octaveNb;
	}
}
