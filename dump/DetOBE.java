package dump;

import java.util.Scanner;

public class DetOBE {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int dim = scan.nextInt();

        Matrix mat = new Matrix(dim, dim);
        mat.readMatrix();
        mat.displayMatrix();
        System.out.println();

        for (int k = 1; k < mat.rows; k++) {
            for (int i = k; i < mat.rows; i++) {
                double p = mat.contents[i][k - 1];
                double q = mat.contents[k - 1][k - 1];

                if (q != 0) {
                    for (int j = 0; j < mat.rows; j++) {
                        mat.contents[i][j] = mat.contents[i][j] - (p / q) * (mat.contents[k - 1][j]);
                    }
                }
            }
        }

        mat.displayMatrix();
        System.out.println();
        double det = 1;
        for (int i = 0; i < mat.rows; i++) {
            det *= mat.contents[i][i];
        }

        System.out.println("Determinant: " + det);
        scan.close();
    }
}
