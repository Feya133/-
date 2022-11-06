package info.gridworld.grid;

import java.util.ArrayList;

public class UnboundedGrid2<E> extends AbstractGrid<E>
{
    private Object[][] occupantArray;
    private int Size = 16;
    private int cSize;

    public UnboundedGrid2() {
        cSize = 16;
        occupantArray = new Object[Size][Size];
    }
    public int getNumRows() {
        return -1;
    }
    public int getNumCols() {
        return -1;
    }
    public boolean isValid(Location loc) {
        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }


    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        for (int r = 0; r < cSize; r++) {
            for (int c = 0; c < cSize; c++) {

                Location location = new Location(r, c);
                if (get(location) != null)
                    theLocations.add(location);
            }
        }
        return theLocations;
    }

    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location" + loc + " is not valid");
        }
        if (loc.getRow() >= cSize || loc.getCol() >= cSize) {
            return null;
        }

        return (E) occupantArray[loc.getRow()][loc.getCol()];
    }

    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location" + loc + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }

        int row = loc.getRow();
        int col = loc.getCol();
        // judge and extend the size of grid
        int newSize = cSize;
        while (row > newSize - 1 || col > newSize - 1) {
            newSize *= 2;
        }
        if (newSize != cSize) {
            extendGrid(newSize);
        }

        E oldOccpuant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccpuant;
    }
    // extend the size of grid
    private void extendGrid(int newSize) {
        // copy the current array to the new array
        Object[][] newArray = new Object[newSize][newSize];
        for (int i = 0; i < cSize; i++) {
            System.arraycopy(occupantArray[i], 0, newArray[i], 0, cSize);
        }
        occupantArray = newArray;
        cSize = newSize;
    }
    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location" + loc + " is not valid");
        }

        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}
