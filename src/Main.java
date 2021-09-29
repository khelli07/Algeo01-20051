package src;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
  static boolean isProgramRunning = true;
  static Scanner sc = new Scanner(System.in);

  // ==========
  // MENU PERHITUNGAN DETERMINAN
  // ==========

  static void hitungDeterminantCofactor() {
    System.out.print("Masukan teks (0) atau input (1)? (0/1): ");
    int prompt = sc.nextInt();
    Matrix mat = new Matrix(0, 0);

    if (prompt == 1) {
      System.out.print("Masukkan ordo matriks persegi: ");
      int rows = sc.nextInt();

      mat = new Matrix(rows, rows);

      System.out.println("Masukkan matriks: ");
      mat.readMatrix();
    } else if (prompt == 0) {
      mat.readMatrixFromFile();
    }

    if (mat.matrixSize() > 0) {
      System.out.println("Determinan dari matriks tersebut adalah " + mat.getDeterminantByCofactor());
  
      System.out.print("Simpan hasil di dalam file? (0: tidak, 1: ya): ");
      prompt = sc.nextInt();
      if (prompt == 1) {
        String namaFile;
        System.out.print("Masukkan nama file (contoh: a.txt): ");
        namaFile = sc.next();
        mat.writeInputMatrix(namaFile, "Determinan dari matriks tersebut adalah " + mat.getDeterminantByCofactor());
      }
    }
  }

  static void hitungDeterminantOBE() {
    System.out.print("Masukan teks (0) atau input (1)? (0/1): ");
    int prompt = sc.nextInt();
    Matrix mat = new Matrix(0, 0);

    if (prompt == 1) {
      System.out.print("Masukkan ordo matriks persegi: ");
      int rows = sc.nextInt();

      mat = new Matrix(rows, rows);

      System.out.println("Masukkan matriks: ");
      mat.readMatrix();
    } else if (prompt == 0) {
      mat.readMatrixFromFile();
    }
    if (mat.matrixSize() > 0) {
      System.out.println("Determinan dari matriks tersebut adalah " + mat.getDeterminantByOBE());

      System.out.print("Simpan hasil di dalam file? (0: tidak, 1: ya): ");
      prompt = sc.nextInt();
      if (prompt == 1) {
        String namaFile;
        System.out.print("Masukkan nama file (contoh: a.txt): ");
        namaFile = sc.next();
        mat.writeInputMatrix(namaFile, "Determinan dari matriks tersebut adalah " + mat.getDeterminantByOBE());
      }
    }
  }
  // ==========
  // MENU PERHITUNGAN SPL
  // ==========

  static void hitungSPLCramer() {
    System.out.print("Masukan teks (0) atau input (1)? (0/1): ");
    int prompt = sc.nextInt();
    Matrix mat = new Matrix(0, 0);
    if (prompt == 1) {
      System.out.print("Masukkan banyak baris: ");
      int rows = sc.nextInt();
      System.out.print("Masukkan banyak kolom: ");
      int cols = sc.nextInt();

      mat = new Matrix(rows, cols);

      System.out.println("Masukkan matriks: ");
      mat.readMatrix();
    } else if (prompt == 0) {
      mat.readMatrixFromFile();
    }
    if (mat.matrixSize() > 0) {
      double[] res = mat.solveByCramerRule();
      if (res.length > 0) {
        System.out.println("Solusi dari persamaan augmented yang Anda masukkan (dengan Cramer): ");
        for (int i = 0; i < res.length; i++) {
          System.out.println("x" + (i + 1) + " = " + res[i]);
        }
      } else {
        System.out.println("Soal tidak bisa diselesaikan karena determinan matriks = 0.");
      }

      System.out.print("Simpan hasil di dalam file? (0: tidak, 1: ya): ");
      prompt = sc.nextInt();
      if (prompt == 1) {
        String namaFile;
        System.out.print("Masukkan nama file (contoh: a.txt): ");
        namaFile = sc.next();
        String output = "";
        if (res.length > 0) {
          output += "Solusi dari persamaan augmented yang Anda masukkan (dengan Cramer): \n";
          for (int i = 0; i < res.length; i++) {
            output += "x" + (i + 1) + " = " + res[i] + "\n";
          }
        } else {
          output += "Soal tidak bisa diselesaikan karena determinan matriks = 0.\n";
        }
        mat.writeInputMatrix(namaFile, output);
      }
    }
  }

  static void hitungSPLInvers() {
    System.out.print("Masukan teks (0) atau input (1)? (0/1): ");
    int prompt = sc.nextInt();
    Matrix mat = new Matrix(0, 0);
    if (prompt == 1) {
      System.out.print("Masukkan banyak baris: ");
      int rows = sc.nextInt();
      System.out.print("Masukkan banyak kolom: ");
      int cols = sc.nextInt();

      mat = new Matrix(rows, cols);

      System.out.println("Masukkan matriks: ");
      mat.readMatrix();
    } else if (prompt == 0) {
      mat.readMatrixFromFile();
    }
    if (mat.matrixSize() > 0) {
      double[] res = mat.solveByInverse();
      if (res.length > 0) {
        System.out.println("Solusi dari persamaan augmented yang Anda masukkan (dengan Inverse): ");
        for (int i = 0; i < res.length; i++) {
          System.out.println("x" + (i + 1) + " = " + res[i]);
        }
      } else {
        System.out.println("Soal tidak bisa diselesaikan karena determinan matriks = 0.");
      }

      System.out.print("Simpan hasil di dalam file? (0: tidak, 1: ya): ");
      prompt = sc.nextInt();
      if (prompt == 1) {
        String namaFile;
        System.out.print("Masukkan nama file (contoh: a.txt): ");
        namaFile = sc.next();
        String output = "";
        if (res.length > 0) {
          output += "Solusi dari persamaan augmented yang Anda masukkan (dengan Inverse): \n";
          for (int i = 0; i < res.length; i++) {
            output += "x" + (i + 1) + " = " + res[i] + "\n";
          }
        } else {
          output += "Soal tidak bisa diselesaikan karena determinan matriks = 0.\n";
        }
        mat.writeInputMatrix(namaFile, output);
      }
    } 
  }

  static void hitungSPLGauss() {
    System.out.print("Masukan teks (0) atau input (1)? (0/1): ");
    int prompt = sc.nextInt();
    Matrix mat = new Matrix(0, 0);

    if (prompt == 1) {
      System.out.print("Masukkan banyak baris: ");
      int rows = sc.nextInt();
      System.out.print("Masukkan banyak kolom: ");
      int cols = sc.nextInt();

      mat = new Matrix(rows, cols);

      System.out.println("Masukkan matriks: ");
      mat.readMatrix();
    } else if (prompt == 0) {
      mat.readMatrixFromFile();
    }
    if (mat.matrixSize() > 0) {
      System.out.println("Solusi dari persamaan augmented yang Anda masukkan (dengan Gauss): ");
      mat.solveByGauss();
    }
  }

  static void hitungSPLGaussJordan() {
    System.out.print("Masukan teks (0) atau input (1)? (0/1): ");
    int prompt = sc.nextInt();
    Matrix mat = new Matrix(0, 0);
    if (prompt == 1) {
      System.out.print("Masukkan banyak baris: ");
      int rows = sc.nextInt();
      System.out.print("Masukkan banyak kolom: ");
      int cols = sc.nextInt();

      mat = new Matrix(rows, cols);

      System.out.println("Masukkan matriks: ");
      mat.readMatrix();
    } else if (prompt == 0) {
      mat.readMatrixFromFile();
    }
    if (mat.matrixSize() > 0) {
      String[] res = mat.solveByGaussJordan();
      if (res[0] == "false") {
        System.out.println("Solusi persamaan tersebut tidak ada.");
      } else {
        System.out.println("Solusi dari persamaan augmented yang Anda masukkan (dengan Gauss-Jordan): ");
        for (int i = 0; i < res.length; i++) {
          System.out.println("x" + (i + 1) + " = " + res[i]);
        }
      }

      System.out.print("Simpan hasil di dalam file? (0: tidak, 1: ya): ");
      prompt = sc.nextInt();
      if (prompt == 1) {
        String namaFile;
        System.out.print("Masukkan nama file (contoh: a.txt): ");
        namaFile = sc.next();
        String output = "";
        if (res.length > 0) {
          output += "Solusi dari persamaan augmented yang Anda masukkan (dengan Gauss-Jordan): \n";
          for (int i = 0; i < res.length; i++) {
            output += "x" + (i + 1) + " = " + res[i] + "\n";
          }
        } else {
          output += "Solusi persamaan tersebut tidak ada.\n";
        }
        mat.writeInputMatrix(namaFile, output);
      }
    }
  }

  // ==========
  // MENU PERHITUNGAN INVERS
  // ==========

  static void cariInversAdjoin() {
    System.out.print("Masukan teks (0) atau input (1)? (0/1): ");
    int prompt = sc.nextInt();
    Matrix mat = new Matrix(0, 0);
    if (prompt == 1) {
      System.out.print("Masukkan ordo matriks: ");
      int rows = sc.nextInt();

      mat = new Matrix(rows, rows);

      System.out.println("Masukkan matriks: ");
      mat.readMatrix();
    } else if (prompt == 0) {
      mat.readMatrixFromFile();
    }
    if (mat.matrixSize() > 0) {
      Matrix inverse = mat.createInverseMatrix();

      if (inverse.matrixSize() > 0) {
        System.out.println("Inversnya adalah: ");
        mat.createInverseMatrix().displayMatrix();
      } else {
        System.out.println("Matriks tersebut tidak memiliki invers.");
      }

      System.out.print("Simpan hasil di dalam file? (0: tidak, 1: ya): ");
      prompt = sc.nextInt();
      if (prompt == 1) {
        String namaFile;
        System.out.print("Masukkan nama file (contoh: a.txt): ");
        namaFile = sc.next();
        String output = "";
        if (inverse.matrixSize() > 0) {
          output += "Inversnya adalah: \n";
          for (int i = 0; i < inverse.rows; i++) {
            for (int j = 0; j < inverse.cols; j++) {
              // Menghindari signed zero :-(
              if (inverse.contents[i][j] == 0) {
                // System.out.print(0.0 + " ");
                output += 0.0 + " ";
              } else {
                // System.out.print(inverse.contents[i][j] + " ");
                output += inverse.contents[i][j] + " ";
              }
            }
            output += "\n";
          }
        } else {
          output += "Matriks tersebut tidak memiliki invers.\n";
        }
        mat.writeInputMatrix(namaFile, output);
      }
    }
  }

  static void cariInversOBE() {
    System.out.print("Masukan teks (0) atau input (1)? (0/1): ");
    int prompt = sc.nextInt();
    Matrix mat = new Matrix(0, 0);
    if (prompt == 1) {
      System.out.print("Masukkan ordo matriks: ");
      int rows = sc.nextInt();

      mat = new Matrix(rows, rows);

      System.out.println("Masukkan matriks: ");
      mat.readMatrix();
    } else if (prompt == 0) {
      mat.readMatrixFromFile();
    }
    if (mat.matrixSize() > 0) {
      System.out.println("Inversnya adalah: ");
      mat.inverseByOBE();
    }
  }

  // ==========
  // MENU INTERPOLASI
  // ==========

  static void interpolasi() {
    System.out.print("Masukan teks (0) atau input (1)? (0/1): ");
    int prompt = sc.nextInt();
    
    if (prompt == 1) {
      System.out.print("Masukkan orde polinomial: ");
      int n = sc.nextInt();

      Matrix mat = new Matrix(n + 1, 2);
      System.out.println("Masukkan " + (n + 1) + " pasangan x y:");
      mat.readMatrix();

      System.out.print("Masukkan nilai x yang ingin ditaksir: ");
      double x = sc.nextDouble();

      mat.solveInterpolation(x);
    } else {

    }
  }

  // ==========
  // REGRESI LINEAR BERGANDA
  // ==========

  static void regresiBerganda() {
    System.out.print("Masukan teks (0) atau input (1)? (0/1): ");
    int prompt = sc.nextInt();
    
    System.out.println(prompt);
    if (prompt == 1) {
      System.out.print("Masukkan nilai n (banyak variabel): ");
      int n = sc.nextInt();

      System.out.print("Masukkan nilai m (banyak sampel): ");
      int m = sc.nextInt();

      Matrix mat = new Matrix(m, n + 1);
      System.out.println("Masukkan nilai x1i x2i ... xni yi: ");
      mat.readMatrix();

      double[] xTaksiran;
      xTaksiran = new double[n];
      System.out.println("Masukkan nilai x1 x2 ... xn (yang ingin ditaksir): ");

      for (int i = 0; i < n; i++) {
        xTaksiran[i] = sc.nextDouble();
      }

      mat.solveRegression(xTaksiran);
    } else {

    }
  }

  // ==========
  // MENU UTAMA
  // ==========

  static void menu() {
    System.out.println("MENU");
    System.out.println("1. Sistem Persamaan Linier");
    System.out.println("2. Determinan");
    System.out.println("3. Matriks balikan");
    System.out.println("4. Interpolasi polinom");
    System.out.println("5. Regresi linier berganda");
    System.out.println("6. Keluar");
    System.out.print("Input Anda: ");
    int op = sc.nextInt();
    switch (op) {
      case 1:
        menuSPL();
        break;
      case 2:
        menuDeterminan();
        break;
      case 3:
        menuInvers();
        break;
      case 4:
        interpolasi();
        break;
      case 5:
        regresiBerganda();
        break;
      default:
        isProgramRunning = false;
    }
  }

  static void menuSPL() {
    System.out.println("1. Metode eliminasi Gauss");
    System.out.println("2. Metode eliminasi Gauss-Jordan");
    System.out.println("3. Metode matriks balikan");
    System.out.println("4. Kaidah Cramer");
    int op = sc.nextInt();
    switch (op) {
      case 1:
        hitungSPLGauss();
        break;
      case 2:
        hitungSPLGaussJordan();
        break;
      case 3:
        hitungSPLInvers();
        break;
      case 4:
        hitungSPLCramer();
        break;
      default:
        menu();
    }
  }

  static void menuDeterminan() {
    System.out.println("1. Metode reduksi baris");
    System.out.println("2. Metode ekspansi kofaktor");
    int op = sc.nextInt();
    switch (op) {
      case 1:
        hitungDeterminantOBE();
        break;
      case 2:
        hitungDeterminantCofactor();
        break;
      default:
        menu();
    }
  }

  static void menuInvers() {
    System.out.println("1. Metode reduksi baris");
    System.out.println("2. Metode matriks adjoin");
    int op = sc.nextInt();
    switch (op) {
      case 1:
        cariInversOBE();
        break;
      case 2:
        cariInversAdjoin();
        break;
      default:
        menu();
    }
  }

  public static void main(String[] args) {
    while (isProgramRunning) {
      menu();
    }
  }
}
