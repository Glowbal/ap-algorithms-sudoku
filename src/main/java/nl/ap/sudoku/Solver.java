package nl.ap.sudoku;

public class Solver {

    public static void main(String[] args) {
        Solver solver = new Solver();

        int[][] matrix = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}};

        int[][] matrix2 = {{0, 0, 0, 0, 7, 0, 4, 0, 3},
                {0, 0, 9, 8, 3, 0, 0, 0, 5},
                {0, 6, 1, 0, 0, 0, 0, 0, 0},
                {7, 0, 0, 0, 0, 5, 8, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 3, 0},
                {0, 0, 8, 6, 0, 0, 0, 0, 4},
                {0, 0, 0, 0, 0, 0, 2, 4, 0},
                {6, 0, 0, 0, 9, 2, 5, 0, 0},
                {2, 0, 7, 0, 6, 0, 0, 0, 0}};

        int[][] solution = {{8, 5, 2, 1, 7, 9, 4, 6, 3},
                {4, 7, 9, 8, 3, 6, 1, 2, 5},
                {3, 6, 1, 2, 5, 4, 9, 8, 7},
                {7, 3, 6, 9, 4, 5, 8, 1, 2},
                {5, 1, 4, 7, 2, 8, 6, 3, 9},
                {9, 2, 8, 6, 1, 3, 7, 5, 4},
                {1, 9, 5, 3, 8, 7, 2, 4, 6},
                {6, 8, 3, 4, 9, 2, 5, 7, 1},
                {2, 4, 7, 5, 6, 1, 3, 9, 8}};

        solver.solvePuzzle(matrix);
        solver.solvePuzzle(matrix2);
        solver.solvePuzzle(solution);
    }

    public boolean solvePuzzle(int[][] matrix) {
        int x, y = 0;

        boolean found = false;

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

        if (!found) {
            this.printMatrix(matrix);

            return true;
        }

        boolean digits[] = new boolean[11];
        for (int i = 0; i < 9; i++) {
            digits[matrix[x][i]] = true;
            digits[matrix[i][y]] = true;
        }

        int bx = 3 * (x / 3), by = 3 * (y / 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                digits[matrix[bx + i][by + j]] = true;
            }
        }

        for (int i = 1; i <= 9; i++) {
            if (!digits[i]) {
                matrix[x][y] = i;

                if (solvePuzzle(matrix)) {
                    return true;
                }

                matrix[x][y] = 0;
            }
        }

        return false;
    }

    private void printMatrix(int[][] matrix) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.printf("%5d ", matrix[i][j]);
            }

            System.out.println();
        }

        System.out.println("-- Done printing");
        System.out.println();
        System.out.println();
    }
}