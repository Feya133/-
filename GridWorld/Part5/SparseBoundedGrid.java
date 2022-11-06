package info.gridworld.grid;

import java.util.ArrayList;

public class SparseBoundedGrid<E> extends AbstractGrid<E>{
    private SparseGridNode [] occupantArray ;
    private  int cols,rows;


    public SparseBoundedGrid(int row,int col)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");

        occupantArray = new SparseGridNode[rows];
        this.rows = rows;
        this.cols = cols;
    }

    @Override
    public int getNumRows() {
        return rows;
    }

    @Override
    public int getNumCols() {
        return cols;
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

        E oldOccupant = get(loc);//

        int locrow = loc.getRow();
        int loccol = loc.getCol();
        SparseGridNode s = occupantArray[locrow];
        occupantArray[locrow] = new SparseGridNode(obj,loccol,s);
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
        SparseGridNode s = occupantArray[locrow];
            if(s.getCol() == loccol)
            occupantArray[locrow] = s.getNext();

            return r;

    }


    @Override
    public E get(Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        int locrow = loc.getRow();
        int loccol = loc.getCol();
        SparseGridNode s = occupantArray[locrow];
        while(s != null)
        {
            if(loccol == s.getCol())
            {
                return (E)s.getOccupant();
            }
            s = s.getNext();
        }
            return  null;
    }

    @Override
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        for (int r = 0; r < getNumRows(); r++)
        {
            SparseGridNode s = occupantArray[r];
            while ( s != null)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, s.getCol());
                    theLocations.add(loc);
                    s = s.getNext();
            }
        }

        return theLocations;
    }
    }

