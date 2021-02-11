import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.critters.SocialAnxietyCritter;
import info.gridworld.grid.Location;

public class RunnerE
{
	public static void main(String[] args)
	{
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new Critter());
        world.add(new Location(7, 3), new SocialAnxietyCritter());
        world.show();
	}
}
