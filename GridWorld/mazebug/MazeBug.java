package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class MazeBug extends Bug{
    private Location next;
    private Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
    private  ArrayList<Location> branch;
    private boolean isEnd =false;
    private  boolean Visited[][];
    private Integer stepCount = 0 ;
    private int l,r,f,b;
    boolean show;

    public MazeBug()
    {
        setColor(Color.GREEN);
        Visited = new boolean[100][100];
        for(int i = 0; i < 100;i++)
           for(int j = 0; j <100;j++ )
           {
               Visited[i][j] = false;
           }
        Location lc =getLocation();
       branch = new ArrayList<>();
       branch.add(lc);
       l =1;
       r = 1;
       f =1;
       b =1;
    }

    public void act() {
        boolean M = canMove();
        if (isEnd == true)
        {
            if(show == false) {
                String message = stepCount.toString() + "steps";
                JOptionPane.showMessageDialog(null, message);
                show = true;
            }
        }
        else if(M)
        {
            Visited[next.getRow()][next.getCol()] = true;
            move();
            stepCount++;
        }
        else{
            Back();
            stepCount++;
        }
    }




    public ArrayList<Location> getValid(Location loc)
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return null;

        ArrayList<Location> Valid = new ArrayList<>();

        int direction[] = {Location.AHEAD, Location.LEFT, Location.RIGHT, Location.HALF_CIRCLE};
        for(int d :direction)
        {
          Location neighbour = loc.getAdjacentLocation(getDirection()+d);

            int row = neighbour.getRow();
            int col = neighbour.getCol();
          if(gr.isValid(neighbour))
          {
              Actor a = gr.get(neighbour);
               if((a==null|| a instanceof Flower) && !Visited[row][col])
               {
                   Valid.add(neighbour);
               } else if (a instanceof Rock ) {
                   isEnd = true;

               }
          }

          }
        return Valid;
    }


    public Location ChoseDirection(ArrayList<Location> locations)
    {
        int ll = 0;
        int rr = 0;
        int ff = 0;
        int bb = 0;
        int dic;

        for(Location loc : locations)
        {
            dic = getLocation().getDirectionToward(loc);
            if(dic == 0)
            {
                ff = f;
            }
            else if(dic == 90)
            {
                rr = r;
            } else if (dic == 180) {
                bb = b;

            } else if (dic == 270) {
                ll = l;

            }
        }

        int random = 1+(int)(Math.random()*(ll+ff+rr+bb));
        if (random <= ll) {
            dic = 270;
            ll++;
        }
        // 1-2
        else if (random <= (ll + rr)) {
            dic = 90;
            rr++;
        }
        // 2-3
        else if (random <= (ll + rr + ff)) {
            dic = 0;
            ff++;
        }
        // 3-4
        else {
            dic = 180;
            bb++;
        }
        Location best = null;
        for(Location loc : locations)
        {
            if(dic== getLocation().getDirectionToward(loc))
            {
                best = loc;
            }
        }
        return best;
    }

    public boolean canMove()
    {
        ArrayList<Location> loc = getValid(getLocation());
        if(loc.isEmpty())
            return false;
        else
        {
            branch.add(getLocation());
            int lsize = loc.size();
            if(lsize >= 2)
            {
                crossLocation.push(branch);
                branch = new ArrayList<Location>();
                next = ChoseDirection(loc);
            }
            else {
                next = loc.get(0);
            }
        }
        return true;
    }

    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        if(gr.isValid(next))
        {
            setDirection(getLocation().getDirectionToward(next));
            moveTo(next);
        }
        else
        {
            removeSelfFromGrid();

        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }

    public  void Back()
    {
        if(branch.isEmpty())
        {
            branch = crossLocation.pop();
            Location loc = branch.get(branch.size()-1);
            int dic = getLocation().getDirectionToward(loc);
            if(dic == 0)
            {
                b--;
            }
            else if(dic == 90)
            {
                l--;
            } else if (dic == 180) {
                f--;

            }
            else
            {
                r--;
            }
        }

        next = branch.remove(branch.size()-1);
        move();
    }

}
