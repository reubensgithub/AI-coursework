package maze_solver;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;


public class MazeConverter {
    /**
     * Converts the text file that is parsed in, into a 2-D array.
     * @param filePath The filepath of the text file that we want to convert into a 2-D array
     * @return A 2-D array of chars.
     */
    public static char[][] fileToArray(String filePath) {
        int rows = 0;
        int cols = 0;
        String file_path = filePath;
        char[][] mazeArray = new char[0][];
        try (RandomAccessFile mazeFile = new RandomAccessFile(file_path, "rw")) {
            String line;
            char[] firstLine = mazeFile.readLine().toCharArray();
            for (int i = 0; i < firstLine.length; i++) {
                cols++;
            }
            mazeFile.seek(0); //reset position in file back to beginning

            while ((line = mazeFile.readLine()) != null) {
                char[] lineToArray = line.toCharArray();
                if (lineToArray.length > 0) {
                    rows++;
                }
            }
            mazeFile.seek(0);
            mazeFile.close();

            mazeArray = new char[rows][cols];
            Scanner scanner = new Scanner(new File(file_path));

            for (int row = 0; scanner.hasNextLine() && row < rows; row++) {
                char[] chars = scanner.nextLine().replaceAll(" ", "").toCharArray();
                for (int i = 0; i < cols && i < chars.length; i++) {
                    mazeArray[row][i] = chars[i];
                }
            }


        } catch (Exception e) {
            System.out.println("FAIL" + e);
        }
        return mazeArray;
    }

    /**
     * Takes in a 2-D array of chars and converts each char into a Node.
     * @param mazeToConvert The 2-D array of chars to be converted
     * @return A 2-D array of Nodes.
     */
    public static Node[][] mazeToNodes(char[][] mazeToConvert) {
        Node [][] mazeNodes = new Node [mazeToConvert.length][mazeToConvert[0].length];
        for (int i = 0; i < mazeToConvert.length; i++) {
            for (int j = 0; j < mazeToConvert[0].length; j++) {
                //if (maze[i][j] != ' ') {
                mazeNodes[i][j] = new Node<>(mazeToConvert[i][j], i, j, false, null);
                // }
            }
        }
        return mazeNodes;
    }

    /**
     * Adds a right neighbour to every node in the 2-D array of Nodes.
     * @param mazeNodes The 2-D array of Nodes that we want to add a right neighbour for every Node in the array.
     */
    public static void addNeighbourRight(Node[][] mazeNodes) {
        for (int i = 0; i < mazeNodes.length; i++) {
            for (int j = 0; j < mazeNodes[0].length - 1; j++) {
                if (mazeNodes[i][j] == null || mazeNodes[i][j+1] == null
                        || mazeNodes[i][j].getData().equals('#') || mazeNodes[i][j+1].getData().equals('#')
                        || mazeNodes[i][j].getData().equals(' ') || mazeNodes[i][j+1].getData().equals(' ')) {
                    continue;
                } else {
                    mazeNodes[i][j].addNeighbor(mazeNodes[i][j+1]);
                }
            }
        }
    }
    /**
     * Adds a left neighbour to every node in the 2-D array of Nodes.
     * @param mazeNodes The 2-D array of Nodes that we want to add a left neighbour for every Node in the array.
     */
    public static void addNeighbourLeft(Node[][] mazeNodes) {
        for (int i = 0; i < mazeNodes.length; i++) {
            for (int j = 1; j < mazeNodes[0].length; j++) {
                if (mazeNodes[i][j] == null || mazeNodes[i][j-1] == null
                        || mazeNodes[i][j].getData().equals('#') || mazeNodes[i][j-1].getData().equals('#')
                        || mazeNodes[i][j].getData().equals(' ') || mazeNodes[i][j-1].getData().equals(' ')) {
                    continue;
                } else {
                    mazeNodes[i][j].addNeighbor(mazeNodes[i][j-1]);
                }
            }
        }
    }
    /**
     * Adds an above neighbour to every node in the 2-D array of Nodes.
     * @param mazeNodes The 2-D array of Nodes that we want to add an above neighbour for every Node in the array.
     */
    public static void addNeighbourUp(Node[][] mazeNodes) {
        for (int i = 1; i < mazeNodes.length; i++) {
            for (int j = 0; j < mazeNodes[0].length; j++) {
                if (mazeNodes[i][j] == null || mazeNodes[i-1][j] == null
                        || mazeNodes[i][j].getData().equals('#') || mazeNodes[i-1][j].getData().equals('#')) {
                    continue;
                } else {
                    mazeNodes[i][j].addNeighbor(mazeNodes[i-1][j]);
                }
            }
        }
    }
    /**
     * Adds a below neighbour to every node in the 2-D array of Nodes.
     * @param mazeNodes The 2-D array of Nodes that we want to add a below neighbour for every Node in the array.
     */
    public static void addNeighbourDown(Node[][] mazeNodes) {
        for (int i = 0; i < mazeNodes.length - 1; i++) {
            for (int j = 0; j < mazeNodes[0].length; j++) {
                if (mazeNodes[i][j] == null || mazeNodes[i+1][j] == null
                        || mazeNodes[i][j].getData().equals('#') || mazeNodes[i+1][j].getData().equals('#')){
                    continue;
                } else {
                    mazeNodes[i][j].addNeighbor(mazeNodes[i+1][j]);
                }
            }
        }
    }

    /**
     * adds an above, below, left and right neighbour for every Node in the 2-D array of Nodes that was parsed in
     * @param mazeNodes The 2-D array of Nodes that we want to add neighbours to.
     */
    public static void addAllNeighbours(Node[][] mazeNodes) {
        addNeighbourUp(mazeNodes);
        addNeighbourDown(mazeNodes);
        addNeighbourLeft(mazeNodes);
        addNeighbourRight(mazeNodes);
    }


    public static void main(String[] args) {

        // un-comment this section to run DFS algorithm
        /*
        long startTime = System.nanoTime();
        Node[][] mazeNodes = mazeToNodes(fileToArray("C:\\Users\\reube\\IdeaProjects\\ECM2423-CA\\src\\maze-VLarge.txt"));
        addAllNeighbours(mazeNodes);
        DFS dfs = new DFS(mazeNodes);
        dfs.setStartNode(mazeNodes);
        dfs.setEndNode(mazeNodes);
        dfs.solve("dfsmazevlarge_output");
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Total execution time in milliseconds: "
                + elapsedTime/1000000);
        */


        // un-comment this section to run A* algorithm
        /*
        long startTime = System.nanoTime();
        Node[][] mazeNodes = mazeToNodes(fileToArray("C:\\Users\\reube\\IdeaProjects\\ECM2423-CA\\src\\maze-VLarge.txt"));
        addAllNeighbours(mazeNodes);
        AStar astar = new AStar(mazeNodes);
        astar.setStartNode(mazeNodes);
        astar.setEndNode(mazeNodes);
        astar.solve("astarmazevlarge_output");
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Total execution time in milliseconds: "
                + elapsedTime/1000000);
        */
    }
}
