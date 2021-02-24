package info.gridworld.critters;

import java.util.ArrayList;

import info.gridworld.actor.*;

public class SocialAnxietyRock extends Critter
{

	
	public void processActors(ArrayList<Actor> actors) {
		if (actors.size() == 0) {
			return;
		}
		else {
			int numCritters = 0;
			for (Actor a: actors) {
				if (a instanceof Critter) {
					numCritters++;
				}
			}
			
			if (numCritters == 0) {
				
			}
			
			
			
			
		}
	}
	
	
	private void becomeCritter() {
		System.out.println("SAR becomeCritter(): 1");

		removeSelfFromGrid();
		System.out.println("SAR becomeCritter(): 2");

		SocialAnxietyCritter critterForm = new SocialAnxietyCritter();
		System.out.println("SAR becomeCritter(): 3");

		critterForm.putSelfInGrid(getGrid(), getLocation());
		System.out.println("SAR becomeCritter(): 4");

//		getGrid().
		
		
	}
	
	
}
