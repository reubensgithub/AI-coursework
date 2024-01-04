package maze_solver;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private T data;
    private int row;
    private int col;
    private Boolean visited;
    private Node parent;
    private List<Node<T>> neighbors;

    public Node(T data, int row, int col, Boolean visited, Node parent) {
        this.data = data;
        this.row = row;
        this.col = col;
        this.visited = visited;
        this.parent = null;
        this.neighbors = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Boolean isVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public List<Node<T>> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Node<T> neighbor) {
        neighbors.add(neighbor);
    }
}
