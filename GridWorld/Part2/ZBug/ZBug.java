package info.gridworld.actor;
import info.gridworld.actor.Bug;
public class ZBug extends Bug{

    private  int step;
    private  int clength;
    private int c;

    public ZBug(int length)

    {
        step = 0 ;
        clength = length ;
        c = 0;
    }

    public void act() {
        if (canMove()) {
            if (step < clength) {
                step++;
                move();

            } else {
                switch (c)
                {
                    case 0:
                        setDirection(225);
                        break;
                    case 1:
                        setDirection(90);
                        break;
                    default:
                        clength = 0;
                        step = 0;
                        return;
                }

                c++;
                step = 0;
            }
        }

    }
}
