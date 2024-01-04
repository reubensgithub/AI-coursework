
--- Getting Started ---
To run the code, locate MazeConverter.java. It can be found by unzipping the submission folder,
then navigating like so:
ECM2423-CA -> src -> maze_solver -> MazeConverter.java
Open the file in a text editor, ready to edit according to the instructions listed below.
Or alternatively, you can open the ECM2423 folder in an IDE and navigate like mentioned above
to find the file, edit the code and run it there.
After finding the file, go to the main() function within MazeConverter.java and
un-comment the section you would like to run (first section of code within main() contains all the
necessary code to execute depth-first search and the second section of code within main() contains
all the necessary code to execute A* search).

Before running, get the absolute path of the maze text file you would like to execute the search on,
paste it into the fileToArray function that is listed within the mazeToNodes function within line 153
for DFS and line 166 for A* search. Before running, you can change the output file name to a different name,
but for testing and readability purposes I have named the output files in the format: dfsmazelarge_output,
where the word before the maze is the algorithm used and the part after the algorithm used & before the
underscore is named after the maze that is to be searched through. Just like specifying the path of the maze,
you can also specify the absolute path for where you would like to output the output files. If you just
specify a name, it will output the file in the root folder that the function is executing on (in my case,
it would output the file in ECM2423-CA).

--- Footnote ---
Please reference the output files for questions 1.2.4 and 1.3.2, as I decided not to include some final paths
due to the size and how many coordinates were outputted into the output file.