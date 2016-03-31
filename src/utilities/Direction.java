package utilities;

/**
 * Created by Mariana on 25/02/16.
 */
public enum Direction {
    UP(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    DOWN(0, 1);

    private int dirCol;
    private int dirRow;

    Direction(int dirCol, int dirRow) {
        this.dirCol = dirCol;
        this.dirRow = dirRow;
    }

    public int getDirCol() {
        return dirCol;
    }

    public int getDirRow() {
        return dirRow;
    }
}
