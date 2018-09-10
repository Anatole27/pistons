package pistons.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import pistons.solfege.Key;
import pistons.solfege.KeySignature;
import pistons.solfege.Note;
import pistons.solfege.NoteAccidental;
import pistons.solfege.NoteName;
import pistons.storage.SimpleMap;

public class NoteDisplay extends JPanel {

	private static final long serialVersionUID = 1L;
	private SimpleMap noteImagesMap; 

	private KeySignature keySig;
	private Vector<Note> noteSerie;

	public NoteDisplay() {
		noteImagesMap = new SimpleMap();
		noteSerie = new Vector<Note>();

		try {             
			populateImages();

		} catch (IOException ex) {
			// handle exception...
		}
	}

	private void populateImages() throws IOException {
		BufferedImage image = null;
		File f = new File("/home/anatole/eclipse-workspace/pistons/img");
		String[] files = f.list();
		for(String imgFilename:files) {

			Note note = new Note();

			// Read image
			image = ImageIO.read(new File(f.getAbsoluteFile() + "/" + imgFilename));

			// Note name
			switch(imgFilename.charAt(0)){
			case 'a':
				note.pitch.noteName = NoteName.LA;
				break;

			case 'b':
				note.pitch.noteName = NoteName.SI;
				break;

			case 'c':
				note.pitch.noteName = NoteName.DO;
				break;

			case 'd':
				note.pitch.noteName = NoteName.RE;
				break;

			case 'e':
				note.pitch.noteName = NoteName.MI;
				break;

			case 'f':
				note.pitch.noteName = NoteName.FA;
				break;

			case 'g':
				note.pitch.noteName = NoteName.SOL;
				break;

			default:
				break;
			}

			int charIdx = 1;

			// Alteration
			if(imgFilename.charAt(1) == 'e' || imgFilename.charAt(1) == 'i') {
				charIdx = 3; // Next information at index 3
				switch(imgFilename.charAt(1)) {
				case 'e':
					note.accidental = NoteAccidental.BEMOL;
					break;
				case 'i':
					note.accidental = NoteAccidental.DIESE;
					break;
				}
			}
			else {
				note.accidental = NoteAccidental.BECARRE;
			}

			// Octave number
			note.pitch.octaveNb = 2;
			if(imgFilename.charAt(charIdx) == '\'') {
				note.pitch.octaveNb++;
				charIdx++;
				if(imgFilename.charAt(charIdx) == '\'') {
					note.pitch.octaveNb++;
				}
			}

			noteImagesMap.put(note, image);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		//		Iterator<Note> noteIt = noteSerie.iterator();
		//		while(noteIt.hasNext()) {
		//			if(noteImagesMap.containsKey(noteIt.next()))
		//			{
		//				g.drawImage(noteImagesMap.get(noteSerie.get(0)), 0, 0, this); // see javadoc for more info on the parameters            
		//			}
		//		}

		g.drawImage(noteImagesMap.get(noteSerie.get(0)), 0, 0, this); // see javadoc for more info on the parameters

	}

	public void setKeySignature(KeySignature keySig) {

	}

	public void setKey(Key key) {

	}

	public void setNote(Note note) {
		noteSerie.clear();
		noteSerie.addElement(note.clone());
		if(!noteImagesMap.containsKey(note)){
			System.err.println(note.toString()+" is not in database");
		}
	}

	public void setNoteSerie(Vector<Note> noteSerie) {
		this.noteSerie.clear();
		Iterator<Note> noteIt = noteSerie.iterator();
		while(noteIt.hasNext()) {
			this.noteSerie.addElement(noteIt.next().clone());
		}
	}
}
