package info.gridworld.grid;

public class SparseGridNode {
    private Object occupant;
    private int col;
    private SparseGridNode next;

    public SparseGridNode (Object occ , int c, SparseGridNode n) {
        occupant = occ;
        col = c;
        next = n;


    }

    public int getCol() {
        return col;
    }

    public Object getOccupant() {
        return occupant;
    }

    public SparseGridNode getNext() {
        return next;
    }

    public void setOccupant(Object occupant) {
        this.occupant = occupant;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setNext(SparseGridNode next) {
        this.next = next;
    }
}
