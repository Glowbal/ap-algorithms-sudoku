package nl.ap.sudoku;

public class Solver {

    public static void main(String[] args) {
        Solver solver = new Solver();

        int[][] matrix2 = {{0, 0, 0, 0, 7, 0, 4, 0, 3},
                {0, 0, 9, 8, 3, 0, 0, 0, 5},
                {0, 6, 1, 0, 0, 0, 0, 0, 0},
                {7, 0, 0, 0, 0, 5, 8, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 3, 0},
                {0, 0, 8, 6, 0, 0, 0, 0, 4},
                {0, 0, 0, 0, 0, 0, 2, 4, 0},
                {6, 0, 0, 0, 9, 2, 5, 0, 0},
                {2, 0, 7, 0, 6, 0, 0, 0, 0}};

        solver.solvePuzzle(matrix2);
    }

    public int[][] solvePuzzle(int[][] matrix) {
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
            return matrix;
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

                if (solvePuzzle(matrix) != null) {
                    return matrix;
                }

                matrix[x][y] = 0;
            }
        }

        return null;
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