package pistons.solfege;

public class NoteLearn extends Note {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int RECORD_LENGTH = 10;
	private double[] responseDuration = new double[10];
	private int iTry = 0;
	private int idx = 0;

	public NoteLearn(NoteAccidental acc, NoteName name, int octaveNb) {
		super(acc, name, octaveNb);
	}

	public NoteLearn() {
		super();
	}

	public void addNewDuration(double dur) {
		responseDuration[idx] = dur;
		idx++;
		idx %= RECORD_LENGTH;
		iTry++;
	}

	public double getMeanDur() {
		double meanDur = 0;
		int iMax = 0;
		if (iTry > RECORD_LENGTH) {
			iMax = RECORD_LENGTH;
		} else {
			iMax = iTry;
		}
		if (iMax == 0) {
			return 0;
		}
		for (int i = 0; i < iMax; i++) {
			meanDur += responseDuration[i];
		}
		meanDur /= iMax;
		return meanDur;
	}
}
