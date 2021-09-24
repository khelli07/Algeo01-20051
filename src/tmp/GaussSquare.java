package src.tmp;

import java.util.Scanner;

public class GaussSquare {
    private double[] solutions;

    GaussSquare(int sols) {
        this.solutions = new double[sols];
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int rows = scan.nextInt();
        int cols = scan.nextInt();

        Matrix mat = new Matrix(rows, cols);
        mat.readMatrix();
        System.out.println();
        scan.close();

        mat.displayMatrix();
        System.out.println();

        for (int k = 1; k < mat.rows; k++) {
            double divisor = mat.contents[k - 1][k - 1];
            for (int col = 0; col < mat.cols; col++) {
                mat.contents[k - 1][col] = mat.contents[k - 1][col] / divisor;
            }

            for (int i = k; i < mat.rows; i++) {
                double p = mat.contents[i][k - 1];
                double q = mat.contents[k - 1][k - 1];

                if (q != 0) {
                    for (int col = 0; col < mat.cols; col++) {
                        mat.contents[i][col] = mat.contents[i][col] - (p / q) * (mat.contents[k - 1][col]);
                    }
                }
            }

            mat.displayMatrix();
            System.out.println();
        }

        // LAST ROW
        int lastRow = mat.rows - 1;
        int lastCol = mat.cols - 1;
        double divisor = mat.contents[lastRow][lastRow];
        for (int col = 0; col < mat.cols; col++) {
            mat.contents[lastRow][col] = mat.contents[lastRow][col] / divisor;
        }

        mat.displayMatrix();
        System.out.println();

        // SOLUTIONS -> SEBANYAK ROW
        GaussSquare g = new GaussSquare(mat.rows);

        for (int i = lastRow; i > -1; i--) {
            if (i == lastRow) {
                g.solutions[i] = mat.contents[lastRow][lastCol];
            } else {
                g.solutions[i] = mat.contents[i][lastCol];
                for (int j = i + 1; j < lastCol; j++) {
                    g.solutions[i] -= mat.contents[i][j] * g.solutions[j];
                }
            }
        }

        for (int i = 0; i < mat.rows; i++) {
            System.out.printf("x%d = %2.f\n", i, g.solutions[i]);
        }

    }
}
