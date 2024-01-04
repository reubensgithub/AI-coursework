package maze_solver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AStar {
    private static Node startNode;
    private static Node endNode;
    private Node[][] maze;
    private int rows, cols;
    private PriorityQueue<Node> pq;
    private FileWriter output;

    public AStar (Node[][] maze) { // constructor method
        this.maze = maze;
        this.rows = maze.length;
        this.cols = maze[0].length;
        this.pq = new PriorityQueue<Node>(new NodeComparator());
    }

    /**
     * Sets the start node for the maze that is parsed in
     * @param maze The maze that we want to calculate the start node for
     */
    public static void setStartNode (Node[][] maze) {
        for (Node node : maze[0]) {
            if (node.getData().equals('-')) {
                startNode = node;
            }
        }
    }

    /**
     * Sets the end node for the maze that is parsed in
     * @param maze The maze that we want to calculate the end node for
     */
    public static void setEndNode (Node[][] maze) {
        for (Node node : maze[maze.length-1]) {
            if (node.getData().equals('-')) {
                endNode = node;
            }
        }
    }

    /**
     * Calculates the heuristic distance from the current node to the end node
     * @param currentNode The current node to calculate the heuristic from
     * @param endNode The end node that we want to reach
     * @return The calculated heuristic value for the current node
     */
    public int heuristic (Node currentNode, Node endNode) {
        return Math.abs(currentNode.getRow() - endNode.getRow()) + Math.abs(currentNode.getCol() - endNode.getCol());
    }

    /**
     * Calculates the cost to reach the current node from the start node
     * @param startNode The node that we started the maze search from
     * @param currentNode The current node to calculate the cost from
     * @return The cost taken to reach the current node from the start node
     */
    public int cost (Node startNode, Node currentNode) {
        List<Node> distance = new ArrayList<>();
        Node pointer = currentNode;
        while (pointer != startNode && pointer == null) {
            distance.add(pointer);
            pointer = pointer.getParent();
        }
        distance.add(startNode);
        return distance.size();
    }

    /**
     * Calculates the f value of a node
     * @param node The node that we want to calculate the f value for
     * @return The f value of the node that was parsed in.
     */
    public int fvalue (Node node) {
        return heuristic(node, endNode) + cost(startNode, node);
    }

    /**
     * Solves the maze using A* search
     * @param outputPath The output path where we want to store our output file at
     */
    public void solve(String outputPath) {
        try {
            output = new FileWriter(outputPath);
            int explored = 0;
            List<Node> finalPath = new ArrayList<>();
            pq.add(startNode);
            startNode.setVisited(true);

            while (pq.peek() != null) {
                // do A* search
                Node current = pq.poll();
                output.write("(" + current.getRow() + "," + current.getCol() + ")\n");

                if (current.getRow() == endNode.getRow() && current.getCol() == endNode.getCol()) {
                    output.write("Maze solved!");
                    output.close();
                    Node pointer = endNode;
                    while (pointer != startNode) {
                        finalPath.add(pointer);
                        pointer = pointer.getParent();
                    }
                    finalPath.add(startNode);
                    Collections.reverse(finalPath);
                    System.out.println("Resultant path after using A* algorithm is of length: " + finalPath.size());
                    System.out.println("Number of nodes explored by using A* algorithm: " + explored);
                    return;
                }
                List<Node> list = current.getNeighbors();
                for (Node neighbor : list) {
                    if (!neighbor.isVisited()) {
                        neighbor.setVisited(true);
                        pq.add(neighbor);
                    } if (neighbor.getParent() == null) {
                        neighbor.setParent(current);
                    }
                }
                explored++;
            }

            output.write("ERROR: MAZE CANNOT BE SOLVED.\n");
            output.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    class NodeComparator implements Comparator<Node>{
        @Override
        public int compare(Node one, Node two) {
            int fvalueone = fvalue(one);
            int fvaluetwo = fvalue(two);
            if (fvalueone > fvaluetwo) { // negative = put later in queue, positive opposite
                return 1;
            } else if (fvalueone < fvaluetwo) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
