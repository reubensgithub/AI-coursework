**Getting Started**

To run the code, locate MazeConverter.java. This is the main file that converts the mazes into 2D arrays, and runs either DFS or A* depending on what is commented out in the main function. You can find it by following these steps:

1. Unzip the submission folder.
2. Navigate to the following directory: `ecm2423 -> ECM2423-CA -> src -> maze_solver -> MazeConverter.java`

Open the file in a text editor and you're ready to edit according to the instructions below. Alternatively, you can open the `ECM2423` folder in an IDE and navigate to the file as mentioned above. Once you've found the file, you can edit the code and run it directly in your IDE.

**Finding the maze file and setting output paths**

1. Locate the maze text file you want to use.
2. Get the absolute path of the maze file.
3. In the `MazeConverter.java` file, paste the absolute path into the `fileToArray` function within the `mazeToNodes` function:
    - Line 153 for Depth-First Search (DFS)
    - Line 166 for A* search
4. (Optional) Change the output file name to a different name. For testing purposes, the default output files are named in the format: `dfsmazelarge_output`, where "dfs" is the algorithm used and "mazelarge" is the name of the maze.
5. (Optional) Specify the absolute path for where you want to output the files. If you don't specify a path, the files will be saved in the same directory as the script is running (e.g., `ECM2423-CA`).

**Footnote**

Please refer to the output files for questions 1.2.4 and 1.3.2, as some final paths are omitted due to the size and number of coordinates outputted.

**Code**

There is no code provided in the instructions, only explanations on how to edit existing code. 
