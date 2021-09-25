package dump;

import java.util.Scanner;

public class InversOBE {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int dim = scan.nextInt();

        Matrix mat = new Matrix(dim, dim);
        mat.readMatrix();
        System.out.println();
        scan.close();

        mat.inverseByOBE();
    }

}
