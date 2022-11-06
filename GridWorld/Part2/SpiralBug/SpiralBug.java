package info.gridworld.actor;
import info.gridworld.actor.Bug;
public class SpiralBug extends Bug{
    private  int step;
    private  int clength;

    public SpiralBug(int length)
    {
        step = 0 ;
        clength = length ;

    }

    public void act()
    {
        if (step <clength&&canMove() )
        {   move();
            step++;
        }
        else {
            turn();
            turn();
            clength++;
            step = 0;
        }
    }

}
