package pistons.sounds;

import java.util.Arrays;
import java.util.List;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

import pistons.solfege.Note;

/**
 * Playing trumpet tunes (beware of tune transposition)
 * 
 */
public class TrumpetSound {

	private static List<String> notes = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");
	private static MidiChannel[] channels;
	private static int TRUMPET_CHAN = 0; // 0 is a piano, 9 is percussion, other channels are for other instruments
	private static int PERCU_CHAN = 9;
	private static int TRUMPET_NUM = 56;
	private static int PERCU_NUM = 8;
	private static int VOLUME_TRUMPET = 50; // between 0 et 127
	private static int VOLUME_PERCU = 127; // between 0 et 127
	private static int TRANSPOSE_OFFSET = -2;
	Synthesizer synth;

	public TrumpetSound() throws MidiUnavailableException {
		// * Open a synthesizer
		synth = MidiSystem.getSynthesizer();
		synth.open();
		channels = synth.getChannels();
		Instrument[] inst = synth.getAvailableInstruments();
		synth.loadInstrument(inst[TRUMPET_NUM]);
		synth.loadInstrument(inst[PERCU_NUM]);
		channels[TRUMPET_CHAN].programChange(inst[TRUMPET_NUM].getPatch().getProgram());
		channels[PERCU_CHAN].programChange(inst[PERCU_NUM].getPatch().getProgram());
	}

	public static void main(String[] args) {

		try {
			TrumpetSound sound = new TrumpetSound();

			// * Play some notes
			sound.play("4D", 1000);
			sound.rest(500);

			sound.play("4D", 300);
			sound.play("4C#", 300);
			sound.percu(42);
			sound.play("4D", 1000);
			sound.rest(500);

			sound.play("4D", 300);
			sound.percu(42);
			sound.play("4C#", 300);
			sound.percu(42);
			sound.play("4D", 1000);
			sound.percu(42);
			sound.play("4E", 300);
			sound.play("4E", 400);
			sound.play("4G", 300);
			sound.play("4G", 400);
			sound.rest(500);

			// * finish up
			sound.synth.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Plays the given note for the given duration
	 */
	public void play(String note, int duration) throws InterruptedException {
		// * start playing a note
		channels[TRUMPET_CHAN].noteOn(id(note), VOLUME_TRUMPET);
		// * wait
		Thread.sleep(duration);
		// * stop playing a note
		channels[TRUMPET_CHAN].noteOff(id(note));
	}

	/**
	 * Plays the given note for the given duration
	 */
	public void play(Note note, int duration) throws InterruptedException {
		char octave = (char) note.pitch.octaveNb;
		char noteLetter;
		switch (note.pitch.noteName) {
		case DO:
			noteLetter = 'C';
			break;
		case FA:
			noteLetter = 'F';
			break;
		case LA:
			noteLetter = 'A';
			break;
		case MI:
			noteLetter = 'E';
			break;
		case RE:
			noteLetter = 'D';
			break;
		case SI:
			noteLetter = 'B';
			break;
		case SOL:
			noteLetter = 'G';
			break;
		default:
			noteLetter = '0';
			break;
		}

		boolean isSharp = false;
		switch (note.accidental) {
		case BECARRE:
			break;
		case BEMOL:
			noteLetter -= 1;
			if (noteLetter == '@') {
				noteLetter = 'G';
			}
			isSharp = true;
			break;
		case DIESE:
			isSharp = true;
			break;
		default:
			break;
		}

		String noteString = Integer.toString(octave) + Character.toString(noteLetter);
		if (isSharp) {
			noteString += "#";
		}
		play(noteString, duration);
	}

	/**
	 * Plays a percussion sound
	 */
	public void percu(int percuNum) throws InterruptedException {
		// * start playing a note
		channels[PERCU_CHAN].noteOn(percuNum, VOLUME_PERCU);
	}

	/**
	 * Plays nothing for the given duration
	 */
	public void rest(int duration) throws InterruptedException {
		Thread.sleep(duration);
	}

	/**
	 * Returns the MIDI id for a given note: eg. 4C -> 40
	 * 
	 * @return
	 */
	private int id(String note) {
		int octave = Integer.parseInt(note.substring(0, 1));
		return notes.indexOf(note.substring(1)) + 12 * octave + 12 + TRANSPOSE_OFFSET;
	}
}