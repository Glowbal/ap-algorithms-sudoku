package nl.ap.sudoku;

public class SudokuSolver {

    /**
     * Start the programme.
     *
     * @param args Programme arguments
     */
    public static void main(String[] args) {
        SudokuSolver sudokuSolver = new SudokuSolver();

        // Run an empty puzzle
        sudokuSolver.emptyPuzzle();

        // Run a hard puzzle
        sudokuSolver.hardPuzzle();

        // Run an impossible puzzle
        sudokuSolver.impossiblePuzzle();
    }

    /**
     * Solve the SUDOKU puzzle. Return the current state of the matrix or return null.
     * We keep iterating till we can't find any zeros anymore.
     *
     * @param matrix Original matrix
     * @return Current matrix or null
     */
    public int[][] solvePuzzle(int[][] matrix) {
        int x, y = 0;

        boolean found = false;

        // First we check if the matrix contains any zeros.
        // If it does we break out of the for loop and continue to solving the puzzle.
        for (x = 0; x < 9; x++) {
            for (y = 0; y < 9; y++) {
                if (matrix[x][y] == 0) {
                    found = true;
                    break;
                }
            }

            if (found) {
                break;
            }
        }

        // If the puzzle doesn't contain any zeros we return the matrix
        // We now know that this is the solved version of the puzzle
        if (!found) {
            return matrix;
        }

        // We need to know what digits are we still allowed to use
        // (not used in this row, not used in this column, not used in
        // the same 3x3 "sub-box").
        //
        // This array is used to track which digits have been used already on a specific row or column.
        // So imagine a row of tick boxes each with a digit written next to it (the array index).
        // These boxes are checked or unchecked to show a digit has been used or not.
        boolean digits[] = new boolean[11];

        // Re run through the rows/columns around the place (x,y)
        // we want to fill in this iteration
        for (int i = 0; i < 9; i++) {
            // take a used number from the row of x (matrix[x][i]) and mark it as used
            digits[matrix[x][i]] = true;
            // take a used number from the column of y (matrix[i][y]) and mark it as used
            digits[matrix[i][y]] = true;
        }

        // Find the top-left corner of the sub-box for the position (x,y) we want to fill in
        // Example: x is 8 -> 3 * 8/3 -> 6, so we start from 6
        int boxX = 3 * (x / 3), boxY = 3 * (y / 3);

        // Iterate through the sub-box horizontally and vertically
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Take a used number from the sub-box and mark it as used
                digits[matrix[boxX + i][boxY + j]] = true;
            }
        }

        // We loop over all the numbers we have to check if the next number fits in the puzzle
        // We update the matrix and check recursively by calling the same function again to
        // check if the puzzle is correct. If it's not correct we reset the matrix field to 0
        // and continue with the next value.
        for (int i = 1; i <= 9; i++) {
            if (!digits[i]) {
                matrix[x][y] = i;

                if (solvePuzzle(matrix) != null) {
                    return matrix;
                }

                matrix[x][y] = 0;
            }
        }

        // The puzzle can't be solved so we return null
        return null;
    }

    /**
     * Print the matrix in a nice SUDOKU way.
     *
     * @param matrix Original matrix
     */
    private void printMatrix(int[][] matrix) {
        int[][] solution = solvePuzzle(cloneMatrix(matrix));

        for (int i = 0; i < 9; i++) {
            System.out.print("\n");

            if (i % 3 == 0) {
                System.out.print("\n");
            }

            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) {
                    System.out.print(" ");
                }

                if (matrix[i][j] == 0) {
                    System.out.print(". ");
                } else if (matrix[i][j] >= 1 && matrix[i][j] <= 9) {
                    System.out.print(matrix[i][j] + " ");
                }
            }

            System.out.print("     ");

            if (solution != null) {
                for (int j = 0; j < 9; j++) {
                    if (j % 3 == 0) {
                        System.out.print(" ");
                    }

                    if (solution[i][j] == 0) {
                        System.out.print(". ");
                    } else if (solution[i][j] >= 1 && solution[i][j] <= 9) {
                        System.out.print(solution[i][j] + " ");
                    }
                }
            }
        }

        if (solution == null) {
            System.out.print("Unsolvable Sudoku");
        }

        System.out.println();
    }

    /**
     * Clone the matrix.
     *
     * @param matrix Original matrix
     * @return Cloned matrix
     */
    private int[][] cloneMatrix(int[][] matrix) {
        int[][] clone = new int[matrix.length][];

        for (int i = 0; i < matrix.length; i++)
            clone[i] = matrix[i].clone();

        return clone;
    }

    /**
     * Print the original matrix and the solution with some empty lines.
     *
     * @param matrix Original matrix
     */
    private void printSolution(int[][] matrix) {
        printMatrix(matrix);

        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

    /**
     * Run empty SUDOKU puzzle.
     */
    private void emptyPuzzle() {
        int[][] matrix = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        printSolution(matrix);
    }

    /**
     * Run hard SUDOKU puzzle.
     */
    private void hardPuzzle() {
        int[][] matrix = {
                {0, 0, 0, 0, 7, 0, 4, 0, 3},
                {0, 0, 9, 8, 3, 0, 0, 0, 5},
                {0, 6, 1, 0, 0, 0, 0, 0, 0},
                {7, 0, 0, 0, 0, 5, 8, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 3, 0},
                {0, 0, 8, 6, 0, 0, 0, 0, 4},
                {0, 0, 0, 0, 0, 0, 2, 4, 0},
                {6, 0, 0, 0, 9, 2, 5, 0, 0},
                {2, 0, 7, 0, 6, 0, 0, 0, 0}
        };

        printSolution(matrix);
    }

    /**
     * Run impossible SUDOKU puzzle.
     */
    private void impossiblePuzzle() {
        int[][] matrix = {
                {7, 0, 0, 0, 7, 0, 4, 0, 3},
                {0, 0, 9, 8, 3, 0, 0, 0, 5},
                {0, 6, 1, 0, 0, 0, 0, 0, 0},
                {7, 0, 0, 0, 0, 5, 8, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 3, 0},
                {0, 0, 8, 6, 0, 0, 0, 0, 4},
                {0, 0, 0, 0, 0, 0, 2, 4, 0},
                {6, 0, 0, 0, 9, 2, 5, 0, 0},
                {2, 0, 7, 0, 6, 0, 0, 0, 0}
        };

        printSolution(matrix);
    }
}