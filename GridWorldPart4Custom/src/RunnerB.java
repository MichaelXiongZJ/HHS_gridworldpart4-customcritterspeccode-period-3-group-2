import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.critters.SocialAnxietyCritter;
import info.gridworld.grid.Location;

public class RunnerB {
	public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(4, 4), new Critter());
        world.add(new Location(5, 4), new SocialAnxietyCritter());
        world.show();
    }
}
