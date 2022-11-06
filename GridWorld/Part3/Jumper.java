package info.gridworld.actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import java.awt.Color;

public class Jumper extends Actor {

    public Jumper()
        {
        setColor(Color.pink);
        }
    public Jumper(Color JumperColor)
        {
            setColor(JumperColor);
        }

        public void act()
        {
            if(canMove())
            {
                move();
            }

            else if(canJump())
            {
                jump();
            }
            else
            {
                turn();
            }
        }

    private void turn() {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    private boolean canJump() {

        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next))
            return false;//next location如果无效，那么不能jump
        Location next2 = next.getAdjacentLocation((getDirection()));
        if(!gr.isValid(next2))// if the location two cells in front of the jumper is out of the grid
            return false;

        return gr.get(next2) == null;
    }

    private boolean canMove() {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next))
            return false;
        Actor neighbor = gr.get(next);
        return (neighbor == null) || (neighbor instanceof Flower);
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }

    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }

    public void jump()
    {

        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next))
        {
            Location next2 = next.getAdjacentLocation(getDirection());

            if(gr.isValid(next2))
            {
                moveTo(next2);
            }
            else
            {
                removeSelfFromGrid();
            }
        }

    }


}
