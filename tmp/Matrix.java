import java.util.Scanner;

public class Matrix {
  static double valUndef = -999.0;
  private double[][] contents;
  private int rows;
  private int cols;

  static Scanner sc = new Scanner(System.in);

  Matrix(int rows, int cols) {
    this.contents = new double[rows][cols];
    this.rows = rows;
    this.cols = cols;
  }

  public void readMatrix() {
    for (int i = 0; i < this.rows; i ++) {
      for (int j = 0; j < this.cols; j ++) {
        this.contents[i][j] = sc.nextDouble();
      }
    }
  }

  public void displayMatrix() {
    for (int i = 0; i < this.rows; i ++) {
      for (int j = 0; j < this.cols; j ++) {
        System.out.print(this.contents[i][j] + " ");
      }
      System.out.println("");
    }
  }

  public Matrix copyMatrix() {
    Matrix mOut = new Matrix(this.rows, this.cols);
    for (int i = 0; i < this.rows; i ++) {
      for (int j = 0; j < this.cols; j ++) {
        mOut.contents[i][j] = this.contents[i][j];
      }
    }
    return mOut;
  }

  public Matrix copyMatrixWithoutLC() {
    Matrix mOut = new Matrix(this.rows, this.cols - 1);
    for (int i = 0; i < this.rows; i ++) {
      for (int j = 0; j < this.cols - 1; j ++) {
        mOut.contents[i][j] = this.contents[i][j];
      }
    }
    return mOut;
  }

  public double getDeterminantByCofactor() {
    if (this.rows == 1) {
      return this.contents[0][0];
    } else if (this.rows == 2) {
      return (this.contents[0][0] * this.contents[1][1]) - (this.contents[0][1] * this.contents[1][0]);
    } else {
      double determinant = 0;
      int sign = 1;
      // Akan digunakan ekspansi kofaktor pada baris pertama.
      for (int j = 0; j < this.cols; j ++) { // Proses baris pertama matriks.
        Matrix minor = new Matrix(rows - 1, cols - 1);
        // Buat minor matriks.
        for (int row = 1; row < this.rows; row ++) { // Karena menggunakan ekspansi baris pertama, mulai dari baris kedua.
          int minorColIdx = 0;
          for (int col = 0; col < this.cols; col ++) {
            if (col != j) {
              minor.contents[row - 1][minorColIdx] = this.contents[row][col];
              minorColIdx ++;
            }
          }
        }
        determinant += this.contents[0][j] * minor.getDeterminantByCofactor() * sign;
        sign *= - 1;
      }
      return determinant;
    }
  }

  public double[] solveByCramerRule() {
    double[] retArray;
    retArray = new double[this.cols - 1];
    if (this.cols - 1 == this.rows) { // Hanya bisa dilakukan untuk matriks persegi
      Matrix squareMatrix = this.copyMatrixWithoutLC();
      double det = squareMatrix.getDeterminantByCofactor();
      if (det != 0) { // Apabila det = 0, tidak bisa diselesaikan dengan aturan Cramer
        for (int col = 0; col < this.cols - 1; col ++) {
          Matrix cramerMatrix = this.copyMatrixWithoutLC();
          for (int row = 0; row < this.rows; row ++) {
            cramerMatrix.contents[row][col] = this.contents[row][this.cols - 1];
          }
          double cramerDet = cramerMatrix.getDeterminantByCofactor();
          retArray[col] = cramerDet / det;
        }
      }
    }
    return retArray;
  }
}