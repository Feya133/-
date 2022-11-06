package info.gridworld.grid;

import java.util.ArrayList;
import java.util.LinkedList;

public class SparseBoundedGrid2<E> extends AbstractGrid<E> {

    private int col,row;
   private ArrayList<LinkedList<OccupantInCol>> occupantArray;




    @Override
    public int getNumRows() {
        return row;
    }

    @Override
    public int getNumCols() {
        return col;
    }

    public SparseBoundedGrid2(int rows , int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");

        for(int i = 0; i < row;i++ )
        {
            LinkedList<OccupantInCol> l = new LinkedList<OccupantInCol>();
            occupantArray.add(l);
        }
        row = rows;
        col = cols;
    }
    @Override
    public boolean isValid(Location loc) {

        return loc.getRow() >= 0 && loc.getRow() <getNumRows() &&
                loc.getCol() >= 0 && loc.getCol() < getNumCols();
    }

    @Override
    public E put(Location loc, E obj) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        int locrow = loc.getRow();
        int loccol = loc.getCol();
        OccupantInCol occ;

        LinkedList<OccupantInCol> l = occupantArray.get(locrow);
        for(int i = 0;i < l.size(); i++)
        {
            occ = l.get(i);
            if(occ.getCol() == loccol){
                occ.setOccupant(obj);
                return oldOccupant;
            }

        }

        return oldOccupant;
    }

    @Override
    public E remove(Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");

        // Remove the object from the grid.
        E r = get(loc);

        int locrow = loc.getRow();
        int loccol = loc.getCol();

        LinkedList<OccupantInCol> l = occupantArray.get(locrow);
        for (int i = 0; i < l.size(); i++) {
            OccupantInCol occ = l.get(i);
            if (occ.getCol() == loccol) {
                l.remove(i);
            }
        }
        return r;
    }
    @Override
    public E get(Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        int locrow = loc.getRow();
        int loccol = loc.getCol();

        LinkedList<OccupantInCol> l = occupantArray.get(locrow);
        for (int i = 0; i < l.size(); i++) {
            OccupantInCol occ = l.get(i);
            if (occ.getCol() == loccol) {
                return (E) occ.getOccupant();
            }
        }
        return null;
        // unavoidable war
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {

            for (int c = 0; c < getNumCols(); c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }
}
