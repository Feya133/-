import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.MazeBug;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class MazeBugRunner {
        public static void main(String[] args)
        {

            ActorWorld world = new ActorWorld();

            world.add(new Location(0,0), new MazeBug());
            world.add(new Rock());
            world.show();
        }


}
