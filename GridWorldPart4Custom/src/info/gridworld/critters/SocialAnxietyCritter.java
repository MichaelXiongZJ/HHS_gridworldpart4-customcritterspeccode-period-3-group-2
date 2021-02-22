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
	
	public ArrayList<Location> getMoveLocations() {
		//Find closest actor
		ArrayList<Actor> listOfActors = getActors();
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
		//Find the location to go
		int xDiff = x - listOfActors.get(actorIndex).getLocation().getCol();
		int yDiff = y - listOfActors.get(actorIndex).getLocation().getRow();
		if( (xDiff==0&&yDiff<0) || ())
		//Check if location valid
		
		return null;
	}
	
	 public void selectMoveLocations(Location loc) {

		 }
		 
	 }
}
