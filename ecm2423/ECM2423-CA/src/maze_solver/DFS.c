#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define MAX_ROWS 100
#define MAX_COLS 100
#define MAX_LINE_LENGTH 1024

char maze[MAX_ROWS][MAX_COLS];
int rows, cols;

char** fileToArray(char* filePath, int* numRows, int* numCols) {
    FILE* file;
    char* line = NULL;
    size_t len = 0;
    ssize_t read;
    int rows = 0;
    int cols = 0;
    char** mazeArray = NULL;

    file = fopen(filePath, "r");
    if (file == NULL) {
        printf("Failed to open file\n");
        exit(EXIT_FAILURE);
    }

    // Count number of columns in first line
    if ((read = getline(&line, &len, file)) != -1) {
        for (int i = 0; i < strlen(line); i++) {
            if (line[i] != ' ' && line[i] != '\n') {
                cols++;
            }
        }
    }

    // Count number of rows
    while ((read = getline(&line, &len, file)) != -1) {
        if (strlen(line) > 1) {
            rows++;
        }
    }

    // Allocate memory for 2D array
    mazeArray = (char**) malloc(rows * sizeof(char*));
    for (int i = 0; i < rows; i++) {
        mazeArray[i] = (char*) malloc(cols * sizeof(char));
    }

    // Read file into 2D array
    fseek(file, 0, SEEK_SET); // Reset file position to beginning
    int row = 0;
    while ((read = getline(&line, &len, file)) != -1) {
        if (strlen(line) > 1) {
            int col = 0;
            char* token = strtok(line, " \n");
            while (token != NULL) {
                mazeArray[row][col] = token[0];
                col++;
                token = strtok(NULL, " \n");
            }
            row++;
        }
    }

    fclose(file);
    if (line) {
        free(line);
    }

    *numRows = rows;
    *numCols = cols;

    return mazeArray;
}

int dfs(int row, int col) {
    if (maze[row][col] == '#') {
        return 0;
    }
    if (maze[row][col] == '-') {
        maze[row][col] = '#';
        if (row == rows - 1 && col == cols - 1) {
            return 1;
        }
        if (row > 0 && dfs(row - 1, col)) {
            return 1;
        }
        if (row < rows - 1 && dfs(row + 1, col)) {
            return 1;
        }
        if (col > 0 && dfs(row, col - 1)) {
            return 1;
        }
        if (col < cols - 1 && dfs(row, col + 1)) {
            return 1;
        }
    }
    return 0;
}

int main() {
    clock_t start, end;
    double cpu_time_used;

    start = clock(); // start the timer

    FILE *fp;
    int i, j;
    
    fp = fopen("C:\\Users\\reube\\Downloads\\maze-Easy.txt", "r");
    if (fp == NULL) {
        printf("Failed to open file.\n");
        return 1;
    }
    fscanf(fp, "%d %d", &rows, &cols);
    for (i = 0; i < rows; i++) {
        fscanf(fp, "%s", maze[i]);
    }
    fclose(fp);
    
    if (dfs(0, 0)) {
        printf("Path found!\n");
    } else {
        printf("Path not found.\n");
    }

    end = clock(); // end the timer

    cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC; // calculate the elapsed time in seconds

    printf("Execution time: %f seconds\n", cpu_time_used);
    
    return 0;
}
