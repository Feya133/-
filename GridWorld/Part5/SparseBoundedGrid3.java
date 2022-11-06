package info.gridworld.grid;

import java.util.ArrayList;
import java.util.HashMap;


public class SparseBoundedGrid3 <E>extends AbstractGrid<E> {

    private int rows,cols;
    private HashMap<Location,E> map;

    @Override
    public int getNumRows() {
        return rows;
    }

    @Override
    public int getNumCols() {
        return cols;
    }


    public SparseBoundedGrid3(int row , int col)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");

        map = new HashMap<Location,E>();
        rows = row;
        cols = col;

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

    return map.put(loc,obj);
    }

    @Override
    public E remove(Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
         return map.remove(loc);
    }

    @Override
    public E get(Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return map.get(loc);
    }

    @Override
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        for(Location loc : map.keySet())
        {
            theLocations.add(loc);
        }
        return theLocations;

    }
}
