package pistons.instrument;

public class PistonCombination{
	public boolean firstPiston = false;
	public boolean secondPiston = false;
	public boolean thirdPiston = false;
	
	public PistonCombination() {
	}

	public void setValues(PistonCombination pistonCombination) {
		firstPiston = pistonCombination.firstPiston;
		secondPiston = pistonCombination.secondPiston;
		thirdPiston = pistonCombination.thirdPiston;
	}
	
	public String toString() {
		return String.format("%b %b %b", firstPiston, secondPiston, thirdPiston);
		
	}

}
