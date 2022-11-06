package info.gridworld.actor;
import info.gridworld.actor.Bug;
public class DancingBug extends Bug {

    private int angle[];
    private int imax;
    private int i;

    public DancingBug(int[] array)
    {
        angle = array;
        imax = array.length;
        i = 0;
    }

    public void act() {

        if (canMove())
        {
            move();
        }
        if (i < imax)
        {
            setDirection(getDirection() + angle[i] * 45);
            i++;

        }

    }
}
