package shortestpath.util;

/**
 * Represents all cardinal and ordinal directions
 *
 * @author Jake
 */
public enum Direction {

    UP(0, -1, false),
    DOWN(0, 1, false),
    LEFT(-1, 0, false),
    RIGHT(1, 0, false),
    UP_RIGHT(1, -1, true),
    DOWN_RIGHT(1, 1, true),
    DOWN_LEFT(-1, 1, true),
    UP_LEFT(-1, -1, true);

    private final int dx;
    private final int dy;
    private final boolean diagonal;

    private Direction(int dx, int dy, boolean diagonal) {
        this.dx = dx;
        this.dy = dy;
        this.diagonal = diagonal;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public boolean isDiagonal() {
        return diagonal;
    }

}
