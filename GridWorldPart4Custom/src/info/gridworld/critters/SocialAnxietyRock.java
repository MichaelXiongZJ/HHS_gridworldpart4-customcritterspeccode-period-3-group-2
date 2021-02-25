package info.gridworld.critters;

import java.util.ArrayList;

import info.gridworld.actor.*;
import info.gridworld.grid.Location;

public class SocialAnxietyRock extends Critter
{
private boolean willBecomeCritter;
	
	public void processActors(ArrayList<Actor> actors) {
		System.out.println("\nSARock processing Actors");
		
		willBecomeCritter = false;
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
				willBecomeCritter = true;
			}
			
		}
	}
	
	
	public void makeMove(Location loc) {
		int newDirection = getLocation().getDirectionToward(loc);
		setDirection(newDirection);
		
		if (loc == null || willBecomeCritter) {
            System.out.println("SAR: no neighbor, will turn to SACritter");
			becomeCritter();
            
		}
		else {
			System.out.println("SAR: at least 1 CritterNeighbor so do nothing");
		}
       
	}
	
	
	private void becomeCritter() {
//		System.out.println("SAR becomeCritter(): 1");
//
//		System.out.println("SAR becomeCritter(): 2");

		SocialAnxietyCritter critterForm = new SocialAnxietyCritter();
		System.out.println("SAR becomeCritter(): 3");
		
		critterForm.putSelfInGrid(getGrid(), getLocation());
		System.out.println("SAR becomeCritter(): 4");

		
		
	}
	
	
}
