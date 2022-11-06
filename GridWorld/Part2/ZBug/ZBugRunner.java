import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;

import info.gridworld.actor.ZBug;
import info.gridworld.grid.Location;


public class ZBugRunner {
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        ZBug z = new ZBug(5);
        z.setDirection(90);
        world.add(new Location(2,2),z);

        world.show();
    }
}
