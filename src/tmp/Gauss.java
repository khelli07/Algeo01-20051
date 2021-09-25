package src.tmp;

import java.util.Scanner;

public class Gauss {
    static double VAL_UNDEF = -999;

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
            // Swap baris terlebih dahulu
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
                // Yang 0 semua dipindahkan ke bawah sekali
            }

            // Baris yang harus dibagi untuk mendapat 1 utama
            int idxUtama = mat.getIdxUtama(k);
            if (idxUtama != -1) {
                double divisor = mat.contents[k][idxUtama];
                for (int col = 0; col < mat.cols; col++) {
                    mat.contents[k][col] = mat.contents[k][col] / divisor;

                }

                // Reduksi baris di bawah 1 utama
                for (int i = k + 1; i < mat.rows; i++) {
                    double multiplier = mat.contents[i][idxUtama];
                    for (int j = 0; j < mat.cols; j++) {
                        mat.contents[i][j] -= multiplier * mat.contents[k][j];
                    }
                }
            }
        }

        // Hitung solusi -> sebanyak kolom - 1
        int lastRow = mat.rows - 1;
        int lastCol = mat.cols - 1;
        int idxUtama;

        // Cek apakah punya solusi atau tidak
        boolean matrixSolvable = true;
        for (int i = lastRow; i > -1; i--) {
            // Kalau ada yang elemen utamanya di daerah paling kanan,
            // maka tidak solveable.
            if (mat.getIdxUtama(i) == lastCol) {
                matrixSolvable = false;
            }
        }

        if (matrixSolvable) {
            Matrix matSols = new Matrix(mat.cols - 1, mat.cols);

            for (int i = 0; i < matSols.rows; i++) {
                for (int j = 0; j < matSols.cols; j++) {
                    matSols.contents[i][j] = 0;
                }
            }

            for (int j = 0; j < matSols.cols - 1; j++) {
                matSols.contents[j][0] = VAL_UNDEF;
            }

            for (int i = lastRow; i > -1; i--) {
                idxUtama = mat.getIdxUtama(i);
                if (idxUtama != -1) {
                    // Inisialisasi Koefisien
                    for (int col = idxUtama + 2; col < matSols.cols; col++) {
                        matSols.contents[idxUtama][col] = -1 * mat.contents[i][col - 1];
                    }

                    // Inisialisasi Konstanta
                    matSols.contents[idxUtama][0] = mat.contents[i][lastCol];

                    // Operasi
                    for (int j = 1; j < matSols.cols; j++) {
                        double coeff = matSols.contents[idxUtama][j];
                        for (int col = 0; col < matSols.cols; col++) {
                            if (matSols.contents[j - 1][col] != VAL_UNDEF) {
                                matSols.contents[idxUtama][col] += coeff * matSols.contents[j - 1][col];
                            }
                        }
                        if (matSols.contents[j - 1][0] != VAL_UNDEF) {
                            matSols.contents[idxUtama][j] = 0;
                        }
                    }

                }
            }

            for (int i = 0; i < matSols.rows; i++) {
                boolean isFirst = true;
                System.out.printf("x%d = ", i + 1);
                double constant = matSols.contents[i][0];

                int asciiCounter = 97;
                if (constant != VAL_UNDEF && constant != 0) {
                    System.out.printf("%f", constant);
                    isFirst = false;
                }
                if (constant == VAL_UNDEF) {
                    System.out.printf("" + (char) (asciiCounter + i));
                    isFirst = false;
                }
                for (int j = 1; j < matSols.cols; j++) {
                    if (matSols.contents[i][j] < 0) {
                        if (!isFirst) {
                            System.out.printf(" - " + (-1 * matSols.contents[i][j]) + (char) (asciiCounter));
                        } else {
                            System.out.printf("-" + (-1 * matSols.contents[i][j]) + (char) (asciiCounter));
                            isFirst = false;
                        }
                    } else if (matSols.contents[i][j] > 0) {
                        if (!isFirst) {
                            System.out.printf(" + " + matSols.contents[i][j] + (char) (asciiCounter));
                        } else {
                            System.out.printf("" + (-1 * matSols.contents[i][j]) + (char) (asciiCounter));
                            isFirst = false;
                        }
                    } else {
                        System.out.printf("");
                    }
                    asciiCounter++;
                }
                System.out.println();
            }

        } else

        {
            System.out.println("Martriks tidak memiliki solusi.");
        }
    }
}
