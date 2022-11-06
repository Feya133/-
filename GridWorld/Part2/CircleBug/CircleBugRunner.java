import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.CircleBug;
import info.gridworld.actor.Rock;


public class CircleBugRunner {

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new CircleBug(5));
        world.add(new Rock());
        world.show();
    }
}
