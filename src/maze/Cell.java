
package maze;


public class Cell
{

    private boolean north;
    private boolean east;
    private boolean south;
    private boolean west;
    private int num;

    // constructor
    public Cell() {
        this.north = true;
        this.east = true;
        this.south = true;
        this.west = true;
        this.num = -1;
    }
    
    // setters
    public void setNum(int num) {
        this.num = num;
    }
    
    public void setNorth(boolean north) {
        this.north = north;
    }

    public void setEast(boolean east) {
        this.east = east;
    }

    public void setSouth(boolean south) {
        this.south = south;
    }

    public void setWest(boolean west) {
        this.west = west;
    }

    // getters
    public int getNum() {
        return num;
    }
    
    public boolean isNorth() {
        return north;
    }

    public boolean isEast() {
        return east;
    }

    public boolean isSouth() {
        return south;
    }

    public boolean isWest() {
        return west;
    }
    
    
}
