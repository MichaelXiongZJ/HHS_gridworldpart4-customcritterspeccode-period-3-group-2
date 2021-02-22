package info.gridworld.critters;

import java.util.ArrayList;
import info.gridworld.actor.*;
import info.gridworld.grid.*;

/**
 * Added so we don't have compilation errors
 *
 */
public class SocialAnxietyCritter extends Critter
{
	private boolean willBecomeRock = false;
	ArrayList<Critter> crittersToRunFrom = new ArrayList<Critter>();

	

	

	/**
	 * A SocialAnxietyCritter gets the actors within 2 squares of it. 
	 * @return actors a list of actors occupying these locations
	 */
	
	public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int row = getLocation().getRow();
        int col = getLocation().getCol();
        for (int i = -2; i < 3; i++) {
        	if (row + i < 0) continue; 
        	for (int j = -2; j < 3; j++) {
        		if (col + j < 0) continue;
        		Location currLoc = new Location(row + i, col + j);
        		Actor a = getGrid().get(currLoc);
                if (a != null)
                    actors.add(a); 
        	}
        }
        
        return actors;
    }
	
	/**Processes the Actors in the Grid and determines whether this SACritter will turn into a SARock
	 * Modifies the crittersToRunFrom ArrayList, which gives you all the possible Critters that this SACritter could move away from
	 * @param actors is the actors ArrayList from the getActors() method
	 * @author Christopher L
	 */
	public void processActors(ArrayList<Actor> actors) {
		ArrayList<Actor> adjacentActors = getGrid().getNeighbors(getLocation());
		willBecomeRock = false;
		for (Actor a: adjacentActors) {
			if( a instanceof Critter) {
				willBecomeRock = true;
			}
		}
        int row = getLocation().getRow();
        int col = getLocation().getCol();
		//i is a row #, j is a column #
		for (int i = -2; i<= 2; i++) {
			for (int j = -2; j<= 2; j++) {
				boolean selectThisCell = false;
				if (i == -2 || i == 2) {
					selectThisCell = true;
				}
				else if(i == -1 || i == 0 || i == 1) {
					if (j == -2 || j==2) {
						selectThisCell = true;
					}
				}
				
			
				
				if (selectThisCell) {
					Location loc = new Location(row + i, col + j);
					if ( getGrid().isValid(loc)) {
						Actor a = getGrid().get(loc);
		                if (a != null && a instanceof Critter)
		                    crittersToRunFrom.add((Critter)a); 
					}
				}
				
			}
			
			
		}
		
		
		
		
	}

	/**Find the closest critter and move away from the closest critter
	 * @author Michael Xiong
	 * @return the location of the next step.
	 */
	public ArrayList<Location> getMoveLocations() {
		//Find closest actor
		ArrayList<Critter> listOfActors = crittersToRunFrom;
		int x = getLocation().getCol();
		int y = getLocation().getRow();
		int actorIndex = 0;
		double[] listOfDistances = new double [getActors().size()];
		double shortestDistance = Double.MAX_VALUE;
		for (int a=0; a<listOfActors.size(); a++) {
			double distance = Math.sqrt(Math.pow(listOfActors.get(a).getLocation().getRow()-y, 2) + Math.pow(listOfActors.get(a).getLocation().getCol()-x, 2));
			listOfDistances[a] = distance;
		}
		for (int a=0; a<listOfDistances.length; a++) {
			if (shortestDistance < listOfDistances[a]) {
				shortestDistance = listOfDistances[a];
				actorIndex = a;
			}
		}
		//Get the angle it will turn to
		int xDiff = x - listOfActors.get(actorIndex).getLocation().getCol();
		int yDiff = y - listOfActors.get(actorIndex).getLocation().getRow();
		double deg = 0;
		if (yDiff < 0) {
			deg = Math.atan(xDiff/yDiff);
		}else {
			deg = Math.atan(yDiff/xDiff);
		}
		deg = Math.toDegrees(deg) + 180;
		if (deg >360) {
			deg = deg - 360;
		}
		int facingDeg = roundNumber((int)deg, 45);
		//Get the next location
		ArrayList<Location> nextLoc = new ArrayList<Location>();
		nextLoc.add(0, getLocation().getAdjacentLocation(facingDeg));	
		return nextLoc;
	}

	
	
	//return the Location that is one cell in front of the Critter (just one method from Location)
	/**
	 * A SocialAnxietyCritter checks the Location that is one cell in front of the Critter
	 * @return the Location that is one cell in front of the Critter
	 */
	
	public void selectMoveLocations(Location loc) { 
		Location adjLoc = loc.getAdjacentLocation();
		return adjLoc;
	}
	
	public void makeMove(Location loc) {
		int newDirection = getLocation().getDirectionToward(loc);
		setDirection(newDirection);
		
		if (loc == null) {
            removeSelfFromGrid();
		}
        else {
            moveTo(loc); 
        }
	}
	
	//pass the number 45 numberToRoundTo to round to the nearest 45 degrees
	private int roundNumber(int number, int numberToRoundTo) {
		int val = (int) (numberToRoundTo * Math.round((number +0.0 )/ numberToRoundTo) );
		return val;
	}

}

