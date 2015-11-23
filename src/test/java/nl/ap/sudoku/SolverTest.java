package nl.ap.sudoku;

import junit.framework.TestCase;
import org.junit.Before;

import java.util.stream.IntStream;

public class SolverTest extends TestCase {

    Solver solver;

    @Before
    public void setUp() throws Exception {
        solver = new Solver();
    }

    public void testSolverAllZeros() {
        int[][] matrix = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}};

        validateOutput(solver.solvePuzzle(matrix));
    }

    public void testSolverHardPuzzle() {
        int[][] matrix = {{0, 0, 0, 0, 7, 0, 4, 0, 3},
                {0, 0, 9, 8, 3, 0, 0, 0, 5},
                {0, 6, 1, 0, 0, 0, 0, 0, 0},
                {7, 0, 0, 0, 0, 5, 8, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 3, 0},
                {0, 0, 8, 6, 0, 0, 0, 0, 4},
                {0, 0, 0, 0, 0, 0, 2, 4, 0},
                {6, 0, 0, 0, 9, 2, 5, 0, 0},
                {2, 0, 7, 0, 6, 0, 0, 0, 0}};

        validateOutput(solver.solvePuzzle(matrix));
    }

    public void testSolverSolvedPuzzle()
    {
        int[][] matrix = {{8, 5, 2, 1, 7, 9, 4, 6, 3},
                {4, 7, 9, 8, 3, 6, 1, 2, 5},
                {3, 6, 1, 2, 5, 4, 9, 8, 7},
                {7, 3, 6, 9, 4, 5, 8, 1, 2},
                {5, 1, 4, 7, 2, 8, 6, 3, 9},
                {9, 2, 8, 6, 1, 3, 7, 5, 4},
                {1, 9, 5, 3, 8, 7, 2, 4, 6},
                {6, 8, 3, 4, 9, 2, 5, 7, 1},
                {2, 4, 7, 5, 6, 1, 3, 9, 8}};

        validateOutput(solver.solvePuzzle(matrix));
    }

    public void testSolverImpossiblePuzzle() {
        int[][] matrix = {{7, 0, 0, 0, 7, 0, 4, 0, 3},
                {0, 0, 9, 8, 3, 0, 0, 0, 5},
                {0, 6, 1, 0, 0, 0, 0, 0, 0},
                {7, 0, 0, 0, 0, 5, 8, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 3, 0},
                {0, 0, 8, 6, 0, 0, 0, 0, 4},
                {0, 0, 0, 0, 0, 0, 2, 4, 0},
                {6, 0, 0, 0, 9, 2, 5, 0, 0},
                {2, 0, 7, 0, 6, 0, 0, 0, 0}};

        assertNull(solver.solvePuzzle(matrix));
    }

    protected void validateOutput(int[][] expected) {
        if (expected == null)
            fail();

        for (int i = 0; i < 9; i++) {
            assertEquals(45, IntStream.of(expected[i]).sum());

            int sum = 0;
            for (int j = 0; j < 9; j++) {
                sum += expected[i][j];
            }

            assertEquals(45, sum);
        }
    }
}