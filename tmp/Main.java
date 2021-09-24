import java.util.Scanner;

public class Main {
  static boolean isProgramRunning = true;
  static Scanner sc = new Scanner(System.in);

  static void hitungDeterminantCofactor() {
    System.out.print("Masukkan ordo matriks persegi: ");
    int rows = sc.nextInt();

    Matrix mat = new Matrix(rows, rows);

    System.out.println("Masukkan matriks: ");
    mat.readMatrix();

    System.out.println("Determinan dari matriks tersebut adalah " + mat.getDeterminantByCofactor());
  }

  static void hitungSPLCramer() {
    System.out.print("Masukkan banyak baris: ");
    int rows = sc.nextInt();
    System.out.print("Masukkan banyak kolom: ");
    int cols = sc.nextInt();

    Matrix mat = new Matrix(rows, cols);

    System.out.println("Masukkan matriks: ");
    mat.readMatrix();

    
    System.out.println("Solusi dari persamaan augmented yang Anda masukkan (dengan Cramer): ");
    double[] res = mat.solveByCramerRule();
    for (int i = 0; i < res.length; i ++) {
      System.out.println("x" + (i + 1) + " = " + res[i]);
    }
  }

  static void cariInvers() {
    System.out.print("Masukkan ordo matriks: ");
    int rows = sc.nextInt();

    Matrix mat = new Matrix(rows, rows);

    System.out.println("Masukkan matriks: ");
    mat.readMatrix();

    System.out.println("Inversnya adalah: ");
    mat.createInverseMatrix().displayMatrix();
  }

  static void hitungSPLInvers() {
    System.out.print("Masukkan banyak baris: ");
    int rows = sc.nextInt();
    System.out.print("Masukkan banyak kolom: ");
    int cols = sc.nextInt();

    Matrix mat = new Matrix(rows, cols);

    System.out.println("Masukkan matriks: ");
    mat.readMatrix();
    
    System.out.println("Solusi dari persamaan augmented yang Anda masukkan (dengan Inverse): ");
    double[] res = mat.solveByInverse();
    for (int i = 0; i < res.length; i ++) {
      System.out.println("x" + (i + 1) + " = " + res[i]);
    }
  }

  static void menu() {
    System.out.println("=== MENU ===");
    System.out.println("1. Hitung determinan (metode ekspansi kofaktor)");
    System.out.println("2. Penyelesaian SPL (metode Cramer)");
    System.out.println("3. Cari invers matriks");
    System.out.println("4. Penyelesaian SPL (metode invers)");
    System.out.println("Input lain akan menyebabkan program berhenti.");
    System.out.print("Input Anda: ");
    int op = sc.nextInt();
    if (op == 1) {
      System.out.println("=== 1. Hitung determinan (metode ekspansi kofaktor)");
      hitungDeterminantCofactor();
    } else if (op == 2) {
      System.out.println("=== 2. Penyelesaian SPL (metode Cramer)");
      hitungSPLCramer();
    } else if (op == 3) {
      System.out.println("=== 3. Cari invers matriks");
      cariInvers();
    } else if (op == 4) {
      System.out.println("=== 4. Penyelesaian SPL (metode invers)");
      hitungSPLInvers();
    } else {
      isProgramRunning = false;
    }
  }
  public static void main(String[] args) {
    while (isProgramRunning) {
      menu();
    }
  }
}
