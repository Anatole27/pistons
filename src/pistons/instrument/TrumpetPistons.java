package pistons.instrument;

import pistons.solfege.Note;

public class TrumpetPistons implements InstrumentPistons {

	@Override
	public boolean correctCombination(Note note, PistonCombination combi) {
		
		
		if(!combi.firstPiston && !combi.secondPiston && !combi.thirdPiston) {
			switch(note.toLilypond()) {
			case "c'":
			case "g'":
			case "c''":
			case "e''":
			case "g''":
			case "c'''":
				return true;
			default:
				return false;
			}
		}

		if(combi.firstPiston && !combi.secondPiston && !combi.thirdPiston) {
			switch(note.toLilypond()) {
			case "bes":
			case "ais":
			case "f'":
			case "bes'":
			case "ais'":
			case "d''":
			case "f''":
			case "bes''":
			case "ais''":
				return true;
			default:
				return false;
			}
		}

		if(!combi.firstPiston && combi.secondPiston && !combi.thirdPiston) {
			switch(note.toLilypond()) {
			case "b":
			case "fis'":
			case "ges'":
			case "b'":
			case "dis''":
			case "ees''":
			case "fis''":
			case "ges''":
			case "b''":
				return true;
			default:
				return false;
			}
		}

		if(combi.firstPiston && combi.secondPiston && !combi.thirdPiston) {
			switch(note.toLilypond()) {
			case "a":
			case "e'":
			case "a'":
			case "cis''":
			case "des''":
			case "a''":
				return true;
			default:
				return false;
			}
		}

		if(combi.firstPiston && !combi.secondPiston && combi.thirdPiston) {
			switch(note.toLilypond()) {
			case "g":
			case "d'":
				return true;
			default:
				return false;
			}
		}

		if(!combi.firstPiston && combi.secondPiston && combi.thirdPiston) {
			switch(note.toLilypond()) {
			case "gis":
			case "aes":
			case "dis'":
			case "ees'":
			case "gis'":
			case "aes'":
			case "gis''":
			case "aes''":
				return true;
			default:
				return false;
			}
		}

		if(combi.firstPiston && combi.secondPiston && combi.thirdPiston) {
			switch(note.toLilypond()) {
			case "fis":
			case "ges":
			case "cis'":
			case "des'":
				return true;
			default:
				return false;
			}
		}
		
		return false;
	}

}
