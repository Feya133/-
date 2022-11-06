package info.gridworld.actor;

public class CircleBug extends Bug {

    private  int step;
    private int clength;//进行转弯的步数

    public CircleBug(int length)
    {
        step = 0;
        clength = length;
    }
    public void act()
    {
        if (step <clength&&canMove())
        {
            step++;
        }
        else {
            turn();
            step = 0;
        }
    }



}
