package shortestpath.domain;

import shortestpath.datastructures.BinaryHeap;
import shortestpath.datastructures.Coordinate;
import shortestpath.datastructures.Grid;
import shortestpath.datastructures.List;
import shortestpath.datastructures.Node;
import shortestpath.util.Direction;
import shortestpath.util.MathUtil;

/**
 * Implementation of JPS (Jump Point Search) using octile distance as heuristic.
 * Cutting corners trough walls is not allowed in other words when moving to
 * node (x+dx, y+dy) (x+dx, y) and (x, y+dy) must be empty.
 *
 * @author Jake
 */
public class JPS implements PathFinder {

    private Node start;
    private Node end;
    private Grid grid;

    public JPS(Grid grid) {
        this.grid = grid;
    }

    /**
     * Finds the shortest path from start to end using JPS.
     *
     * @param start starting point of the path
     * @param end end point of the path
     * @return shortest path from start to end
     */
    @Override
    public double search(Coordinate start, Coordinate end) {
        // Most explanations of JPS I found allow cutting trough the corners of walls for example
        // https://zerowidth.com/2013/a-visual-explanation-of-jump-point-search.html
        // We can't do that so I have included some comments on the differences

        // For the sake of convenience all state is stored in the nodes instead
        // of seperate arrays like in A*
        this.start = grid.getNode(start.getX(), start.getY());
        this.end = grid.getNode(end.getX(), end.getY());;

        // Same nodes are used to save the search state for multiple searches
        // so we need to reset them
        grid.resetNodes();

        BinaryHeap<Node> queue = new BinaryHeap<>();
        queue.add(this.start);
        this.start.setOpen(true);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.equals(this.end)) {
                return current.getFScore();
            }
            if (current.visited()) {
                continue;
            }
            current.setVisited(true);
            List<Node> successors = getSuccessors(current);

            for (int i = 0; i < successors.size(); i++) {
                Node successor = successors.get(i);
                // Octile distance is used for both distance calculation and as heuristic
                double tentativeGScore = current.getGScore() + heuristic(current, successor);
                double gScore = successor.getGScore();

                if (tentativeGScore < gScore || !successor.isOpen()) {
                    successor.setParent(current);
                    successor.setOpen(true);
                    successor.setGScore(tentativeGScore);
                    successor.setFScore(tentativeGScore + heuristic(successor, this.end));
                    queue.add(successor);
                }
            }
        }
        return -1;
    }

    private List<Node> getSuccessors(Node current) {
        List<Node> successors = new List<>();
        List<Node> neighbours = getNeighbours(current);

        for (int i = 0; i < neighbours.size(); i++) {
            Node neighbour = neighbours.get(i);
            Node next = jump(current, neighbour);

            if (next != null && !next.visited()) {
                successors.add(next);
            }
        }
        return successors;
    }

    private List<Node> getNeighbours(Node node) {
        List<Node> neighbours = new List<>();
        Node parent = node.getParent();
        int x = node.getX();
        int y = node.getY();

        // Return all valid neighbours
        if (parent == null) {
            for (Direction direction : Direction.values()) {
                int dx = direction.getDx();
                int dy = direction.getDy();

                if (!grid.traversable(x + dx, y + dy)) {
                    continue;
                }
                // Prevent corner cutting
                if (direction.isDiagonal() && (!grid.traversable(x + dx, y)
                        || !grid.traversable(x, y + dy))) {
                    continue;
                }
                neighbours.add(grid.getNode(x + dx, y + dy));
            }
            return neighbours;
        }

        // Direction
        int dx = MathUtil.clamp(x - parent.getX(), -1, 1);
        int dy = MathUtil.clamp(y - parent.getY(), -1, 1);

        // Prune neighbours
        // Diagonal direction
        if (dx != 0 && dy != 0) {
            // Can only go forward diagonally if both sides are clear
            if (grid.traversable(x + dx, y) && grid.traversable(x, y + dy)) {
                neighbours.add(grid.getNode(x + dx, y + dy));
                neighbours.add(grid.getNode(x + dx, y));
                neighbours.add(grid.getNode(x, y + dy));
            } else if (grid.traversable(x + dx, y)) {
                neighbours.add(grid.getNode(x + dx, y));
            } else if (grid.traversable(x, y + dy)) {
                neighbours.add(grid.getNode(x, y + dy));
            }
        } else if (dy == 0) {
            // Horizontal direction            
            // Forward
            if (grid.traversable(x + dx, y)) {
                neighbours.add(grid.getNode(x + dx, y));
            }
            // Up
            if (grid.traversable(x, y + 1)) {
                neighbours.add(grid.getNode(x, y + 1));
            }
            // Down
            if (grid.traversable(x, y - 1)) {
                neighbours.add(grid.getNode(x, y - 1));
            }
            // Forward diagonals
            if (grid.traversable(x + dx, y) && grid.traversable(x, y + 1)) {
                neighbours.add(grid.getNode(x + dx, y + 1));
            }
            if (grid.traversable(x + dx, y) && grid.traversable(x, y - 1)) {
                neighbours.add(grid.getNode(x + dx, y - 1));
            }
        } else {
            // Vertical direction
            // Forward
            if (grid.traversable(x, y + dy)) {
                neighbours.add(grid.getNode(x, y + dy));
            }
            // Right
            if (grid.traversable(x + 1, y)) {
                neighbours.add(grid.getNode(x + 1, y));
            }
            // Left
            if (grid.traversable(x - 1, y)) {
                neighbours.add(grid.getNode(x - 1, y));
            }
            // Forward diagonals
            if (grid.traversable(x, y + dy) && grid.traversable(x + 1, y)) {
                neighbours.add(grid.getNode(x + 1, y + dy));
            }
            if (grid.traversable(x, y + dy) && grid.traversable(x - 1, y)) {
                neighbours.add(grid.getNode(x - 1, y + dy));
            }
        }
        return neighbours;
    }

    private Node jump(Node previous, Node node) {
        if (node == null || node.visited()) {
            return null;
        }
        int x = node.getX();
        int y = node.getY();

        // Direction
        int dx = MathUtil.clamp(x - previous.getX(), -1, 1);
        int dy = MathUtil.clamp(y - previous.getY(), -1, 1);

        if (!grid.traversable(x, y)) {
            return null;
        }
        if (x == end.getX() && y == end.getY()) {
            return node;
        }
        // Looking ahead and finding forced neighbours
        // Diagonal direction
        if (dx != 0 && dy != 0) {
            // We actually don't have to perform any extra checks here because 
            // if we had blocked nodes behind us we couldn't have reached this 
            // spot by moving diagonally

            // Looking ahead horizontally to see if we find something
            if (jump(node, grid.getNode(x + dx, y)) != null) {
                return node;
            }
            // Looking ahead vertically to see if we find something
            if (jump(node, grid.getNode(x, y + dy)) != null) {
                return node;
            }
        } else if (dy == 0) {
            // Horizontal direction
            // Instead of checking directly above and below us we need to check 
            // above and below the previous node because we can't assume path exists 
            // from previous node to the nodes above and below us
            if (!grid.traversable(x - dx, y + 1) && grid.traversable(x, y + 1)) {
                return node;
            }
            if (!grid.traversable(x - dx, y - 1) && grid.traversable(x, y - 1)) {
                return node;
            }
        } else {
            // Vertical direction
            // Same thing here. We check the nodes right and left of the previous node
            if (!grid.traversable(x + 1, y - dy) && grid.traversable(x + 1, y)) {
                return node;
            }
            if (!grid.traversable(x - 1, y - dy) && grid.traversable(x - 1, y)) {
                return node;
            }
        }

        // If we don't check here we could end up cutting corners
        if (!grid.traversable(x + dx, y) || !grid.traversable(x, y + dy)) {
            return null;
        }
        // Didn't find anything. Continue looking ahead to current direction
        return jump(node, grid.getNode(x + dx, y + dy));
    }

    private double heuristic(Node a, Node b) {
        // Octile distance
        int dx = MathUtil.abs(a.getX() - b.getX());
        int dy = MathUtil.abs(a.getY() - b.getY());
        return (dx + dy) + (MathUtil.SQRT_OF_TWO - 2) * MathUtil.min(dx, dy);
    }
}
