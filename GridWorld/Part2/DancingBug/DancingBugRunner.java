import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.DancingBug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.SpiralBug;
import info.gridworld.grid.Location;


public class DancingBugRunner {

    public static void main(String[] args)
    {  int[] a = {2, 1, 3, 5, 4, 7, 6, 8};
        ActorWorld world = new ActorWorld();
        DancingBug d = new DancingBug(a);
        world.add(new Location(5,5),d);
        world.show();


}
}
