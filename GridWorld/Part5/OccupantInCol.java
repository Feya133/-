package info.gridworld.grid;

public class OccupantInCol {
    private Object occupant;
    private int col;
    public OccupantInCol(Object object, int co)
    {
        occupant = object;
        col = co;
    }

    public void setOccupant(Object occupant) {
        this.occupant = occupant;
    }
    public void setCol(int col)
    {
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public Object getOccupant() {
        return occupant;
    }
}
