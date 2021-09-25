package dump;

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

        mat.solveByGauss();
    }
}
