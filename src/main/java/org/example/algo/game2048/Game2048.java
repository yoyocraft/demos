package org.example.algo.game2048;

/**
 * @author yoyocraft
 * @date 2024/09/14
 */
public class Game2048 {

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 2, 2, 0},
                {0, 0, 2, 2},
                {2, 4, 4, 2},
                {2, 4, 4, 4},
        };

        printMatrix(matrix);
        System.out.println("----------");
        String direction = "up";
        DirectionEnum directionEnum = DirectionEnum.resolve(direction);
        move(matrix, directionEnum);
        printMatrix(matrix);
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void move(int[][] matrix, DirectionEnum direction) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] next = getNextDirection(direction);
        switch (direction) {
            case UP:
                for (int j = 0; j < cols; j++) {
                    calculate(matrix, 0, j, next);
                }
                break;
            case DOWN:
                for (int j = cols - 1; j >= 0; j--) {
                    calculate(matrix, rows - 1, j, next);
                }
                break;
            case LEFT:
                for (int i = 0; i < rows; i++) {
                    calculate(matrix, i, 0, next);
                }
                break;
            case RIGHT:
                for (int i = rows - 1; i >= 0; i--) {
                    calculate(matrix, i, cols - 1, next);
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }

    private static boolean inRange(int i, int j, int rows, int cols) {
        return i >= 0 && i < rows && j >= 0 && j < cols;
    }

    private static int[][] getNextDirection(DirectionEnum direction) {
        switch (direction) {
            case UP:
                return new int[][]{{1, 0}};
            case DOWN:
                return new int[][]{{-1, 0}};
            case LEFT:
                return new int[][]{{0, 1}};
            case RIGHT:
                return new int[][]{{0, -1}};
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }

    private static int[] nextNonZeroValue(int[][] matrix, int i, int j, int[][] next) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        if (!inRange(i, j, rows, cols)) {
            return null;
        }

        int nextI = i + next[0][0];
        int nextJ = j + next[0][1];
        while (inRange(nextI, nextJ, rows, cols)) {
            if (matrix[nextI][nextJ] != 0) {
                return new int[]{nextI, nextJ, matrix[nextI][nextJ]};
            }
            nextI += next[0][0];
            nextJ += next[0][1];
        }
        return null;
    }

    private static void calculate(int[][] matrix, int i, int j, int[][] next) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (!inRange(i, j, rows, cols)) {
            return;
        }

        int[] result = nextNonZeroValue(matrix, i, j, next);
        if (result == null) {
            return;
        }

        int nextI = result[0];
        int nextJ = result[1];
        int nextValue = result[2];

        if (matrix[i][j] == 0) {
            matrix[i][j] = nextValue;
            matrix[nextI][nextJ] = 0;
            calculate(matrix, i, j, next);
        } else if (matrix[i][j] == nextValue) {
            matrix[i][j] *= 2;
            matrix[nextI][nextJ] = 0;
        }

        int newI = i + next[0][0];
        int newJ = j + next[0][1];
        calculate(matrix, newI, newJ, next);
    }
}
