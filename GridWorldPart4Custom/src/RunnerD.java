import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Critter;
import info.gridworld.critters.SocialAnxietyCritter;
import info.gridworld.grid.Location;

public class RunnerD {

	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
        world.add(new Location(3, 4), new SocialAnxietyCritter());
        world.add(new Location(5, 5), new Critter());
        world.show();

	}

}
