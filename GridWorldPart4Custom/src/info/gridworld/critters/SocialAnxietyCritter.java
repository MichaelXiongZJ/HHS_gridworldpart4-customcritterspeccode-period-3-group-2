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
        		
        		if(getGrid().isValid(currLoc)) {
        			Actor a = getGrid().get(currLoc);
                    if (a != null)
                        actors.add(a); 
        		}
        		
        	}
        }
        
        return actors;
    }
	
	/**Processes the Actors in the Grid and determines whether this SACritter will turn into a SARock
	 * Modifies the crittersToRunFrom ArrayList, which gives you all the possible Critters that this SACritter could move away from
	 * Christopher Lew
	 * @param actors is the actors ArrayList determined by the getActors() method
	 */
	public void processActors(ArrayList<Actor> actors) {
		ArrayList<Actor> adjacentActors = getGrid().getNeighbors(getLocation());
		willBecomeRock = false;
		crittersToRunFrom = new ArrayList<Critter>();
		
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
	
	//Chris: somehow the Critter does not get scared in the right direction?
	//Mic: I think it's fixed now.
	/**Find the closest critter and return a location to move away from the it.
	 * @author Michael Xiong
	 * @return the location of the next step.
	 */
	public ArrayList<Location> getMoveLocations() {
		if(crittersToRunFrom.size() == 0) {
			System.out.println("Random");
			return super.getMoveLocations();
		}else {
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
			/*
			int xDiff = x - listOfActors.get(actorIndex).getLocation().getCol();
			int yDiff = y - listOfActors.get(actorIndex).getLocation().getRow();
			double deg = 0;
			if (yDiff > 0) {
				deg = Math.atan(xDiff/yDiff);
			}else {
				deg = Math.atan(yDiff/xDiff);
			}*/
			/*
			if(yDiff>=0 && x<0) { //1st quadrant
				deg = Math.atan(yDiff/(-xDiff));
				deg = 2*Math.PI - deg;
			}else if(yDiff>0 && xDiff>=0) { //2nd quadrant
				deg = Math.atan(xDiff/yDiff);
				deg = 1.5*Math.PI - deg;
			}else if(yDiff<=0 && xDiff>0) { //3rd quadrant
				deg = Math.atan(-yDiff/xDiff);
				deg = Math.PI - deg;
			}else if(yDiff<0 && xDiff<=0) { //4th quadrant
				deg = Math.atan(-xDiff/-yDiff);
				deg = 1/2*Math.PI - deg;
			}else {
				System.out.println("Turn angle unknown");
			}
			deg = Math.toDegrees(deg);
			deg += 180;
			if(deg > 360) {
				deg = deg - 360;
			}
			System.out.println("New angle = " + roundNumber((int)deg, 45));
			*/
			
			//Get the next location
			ArrayList<Location> nextLoc = new ArrayList<Location>();
					
			//Chris: Uhhh here's a simpler way to do the angle calculations
			//Mic: Yea ur right, i forgot we have these methods lol.
			Location otherLoc = listOfActors.get(actorIndex).getLocation();
			int moveDir = 180+getLocation().getDirectionToward(otherLoc);
			nextLoc.add(0, getLocation().getAdjacentLocation(moveDir));
					
//			nextLoc.add(0, getLocation().getAdjacentLocation(roundNumber((int)deg, 45)));
			System.out.println("Loc = " + nextLoc.get(0));
			return nextLoc;
		}	
	}

	
	
	//return the Location that is one cell in front of the Critter (just one method from Location)
	/**
	 * A SocialAnxietyCritter checks the Location that is one cell in front of the Critter
	 * @return the Location that is one cell in front of the Critter
	 */
	 public Location selectMoveLocation(ArrayList<Location> locs) {
		 int n = locs.size();
		 if (n==0)
			 return getLocation();
		 
		 int x = (int) (n * Math.random());
		 boolean boo = true;
		 
		 
		 for (int i = x; boo;) {
			 if (getGrid().isValid(locs.get(i))) {
				 return locs.get(i);
			 }
			 else {
				 i++;
				 if (i>= n) {
					 i = 0;
				 }
			 }
			 
			 if (i == x) {
				 return getLocation();
			 }
		 }
		 return getLocation();
	 }
	
	 
	 /**Moves to a new Location or replaces itself with a SocialAnxietyRock
	  * Christopher Lew
	  * @param loc is the location to move to. This is determined by selectMoveLocations()
	  */
	public void makeMove(Location loc) {
		System.out.println();
		int newDirection = getLocation().getDirectionToward(loc);
		setDirection(newDirection);
		
		if (loc == null || willBecomeRock) {
            System.out.println("SAC.makeMove() A1");
			becomeRock();

		}
        else {
        	System.out.println("SAC.makeMove: B1");
        	moveTo(loc); 

        }
	}
	
	//pass the number 45 numberToRoundTo to round to the nearest 45 degrees
	private int roundNumber(int number, int numberToRoundTo) {
		int val = (int) (numberToRoundTo * Math.round((number +0.0 )/ numberToRoundTo) );
		return val;
	}

	
	private void becomeRock() {
        SocialAnxietyRock rockForm = new SocialAnxietyRock();
        rockForm.putSelfInGrid(getGrid(), getLocation());
        
        
        
//		System.out.println("SAC becomeRock(): 3"); 
//      getGrid().put(getLocation(), rockForm);//Chris: I'm not really sure if this works
//		System.out.println("SAC becomeRock(): 4");
//		removeSelfFromGrid();

	}
}

