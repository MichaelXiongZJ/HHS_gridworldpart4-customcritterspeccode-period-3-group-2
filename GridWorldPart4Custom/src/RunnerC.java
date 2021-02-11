import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import info.gridworld.critters.SocialAnxietyCritter;


import java.awt.Color;

public class RunnerC {
	  public static void main(String[] args)
	    {
	        ActorWorld world = new ActorWorld();
	      
	      
	        
	        world.add(new Location(1, 2), new Rock(Color.PINK));
	        world.add(new Location(1, 3), new Rock(Color.PINK));
	        world.add(new Location(3, 1), new Rock(Color.PINK));
	        world.add(new Location(2, 1), new Rock(Color.PINK));
	        world.add(new Location(1, 0), new Rock(Color.PINK));
	        world.add(new Location(3, 2), new Rock(Color.PINK));
	        world.add(new Location(3, 3), new Rock(Color.PINK));


	        world.add(new Location(2, 2), new Critter());
	        world.add(new Location(0, 0), new Critter());
	        
	        world.add(new Location(1, 1), new SocialAnxietyCritter());
	        world.show();
	    }
}
