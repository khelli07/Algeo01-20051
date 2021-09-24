package src.tmp;

import java.util.Scanner;

public class Gauss {
    private double[] solutions;

    Gauss(int sols) {
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

        for (int k = 0; k < mat.rows; k++) {
            // Swap baris dulu
            int minIdx = k;
            for (int row = k; row < mat.rows; row++) {
                if (mat.getIdxUtama(row) == -1) {
                    minIdx = row;
                } else if (mat.getIdxUtama(row) < mat.getIdxUtama(minIdx)) {
                    minIdx = row;
                }
            }
            mat.swapRow(k, minIdx);

            if (mat.getIdxUtama(k) == -1) {
                mat.swapRow(k, mat.rows - 1);
                // Yang 0 dipindahkan ke bawah sekali
            }

            // Baris yang harus dibagi untuk mendapat 1 utama
            int idxUtama = mat.getIdxUtama(k);
            if (idxUtama != -1) {
                double divisor = mat.contents[k][idxUtama];
                for (int col = 0; col < mat.cols; col++) {
                    mat.contents[k][col] = mat.contents[k][col] / divisor;

                }

                // Reduksi baris di bawahnya
                for (int i = k + 1; i < mat.rows; i++) {
                    double multiplier = mat.contents[i][idxUtama];
                    for (int j = 0; j < mat.cols; j++) {
                        mat.contents[i][j] -= multiplier * mat.contents[k][j];
                    }
                }
            }

            mat.displayMatrix();
            System.out.println();
        }
    }
}
