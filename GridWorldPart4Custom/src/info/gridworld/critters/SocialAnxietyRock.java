package info.gridworld.critters;

import java.util.ArrayList;

import info.gridworld.actor.*;
import info.gridworld.grid.Location;

public class SocialAnxietyRock extends Critter
{
private boolean willBecomeCritter;
	private int rockTurnNum = 0;//for debugging
	
	
	

	public void processActors(ArrayList<Actor> actors) {
		System.out.println("---\n# of turns as a Rock = "+rockTurnNum);
		System.out.println("SARock processing Actors");

		willBecomeCritter = false;
		if (actors.size() == 0) { //if there are no Actor Neighbors
			willBecomeCritter = true;
			return;
		}
		else {
			int numCritters = 0;
			for (Actor a: actors) {
				if (a instanceof Critter) {
					numCritters++;

				}
			}

			if (numCritters == 0) {//if there are actor neighbors but none are Critters
				willBecomeCritter = true;
			}

		}

	}


	public void makeMove(Location loc) {

		System.out.println("SAR becomeCritter(): 1");
		if ( willBecomeCritter) {
			System.out.println("SAR: Turn to SACritter");
			becomeCritter();

		}
		else {
			System.out.println("SAR becomeCritter(): B");
			System.out.println("SAR: Do nothing");
		}
		rockTurnNum++;
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
