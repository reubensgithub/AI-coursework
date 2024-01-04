package maze_solver;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class DFS {
    private static Node startNode;
    private static Node endNode;
    private Node[][] maze;
    private int rows, cols;
    private Stack<Node> stack;
    private FileWriter output;

    public DFS (Node[][] maze) { // constructor method
        this.maze = maze;
        this.rows = maze.length;
        this.cols = maze[0].length;
        this.stack = new Stack<Node>();
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
    public static void setEndNode (Node[][] maze) { // sets the end node of the maze
        for (Node node : maze[maze.length-1]) {
            if (node.getData().equals('-')) {
                endNode = node;
            }
        }
    }

    /**
     * Solves the maze using DFS
     * @param outputPath The output path where we want to store our output file at
     */
    public void solve(String outputPath) {
        try {
            output = new FileWriter(outputPath);
            int explored = 0;
            List<Node> finalPath = new ArrayList<>();
            stack.push(startNode);
            startNode.setVisited(true);


            while (!stack.isEmpty()) {
                Node current = stack.pop();

                // if we have reached the end node
                if (current.getRow() == endNode.getRow() && current.getCol() == endNode.getCol()) {
                    Node pointer = endNode;
                    while (pointer != startNode) {
                        finalPath.add(pointer);
                        pointer = pointer.getParent();
                    }
                    finalPath.add(startNode);
                    Collections.reverse(finalPath);
                    for (Node node: finalPath) {
                        output.write("(" + node.getRow() + "," + node.getCol() + ")\n");
                    }
                    output.write("Maze solved!\n");
                    output.close();
                    System.out.println("Resultant path of DFS is of length: " + finalPath.size());
                    System.out.println("Number of nodes explored by using DFS: " + explored);
                    return;
                }


                List<Node> list = current.getNeighbors();
                for (Node neighbor : list) {
                    if (!neighbor.isVisited()) {
                        neighbor.setVisited(true);
                        stack.push(neighbor);
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
}
